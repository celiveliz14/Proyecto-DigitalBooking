package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Ciudad;
import com.proyecto.proyecto.model.DTO.CiudadDTO;
import com.proyecto.proyecto.repository.CiudadRepository;
import com.proyecto.proyecto.service.CiudadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CiudadServiceImpl implements CiudadService {
    private CiudadRepository ciudadRepository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(CiudadServiceImpl.class);

    public CiudadServiceImpl(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    @Override
    public CiudadDTO agregarCiudad(CiudadDTO ciudadDTO) throws BadRequestException {
        LOGGER.info("Se inicio una operacion de guardado de la ciudad con titulo " + ciudadDTO.getNombre_ciudad());
        Ciudad ciudad = mapper.convertValue(ciudadDTO, Ciudad.class );
        if (ciudad == null){
            throw new BadRequestException("Revise la informacion enviada por favor");
        }
        ciudadRepository.save(ciudad);
        CiudadDTO ciudadReturned = mapper.convertValue(ciudad, CiudadDTO.class);
        return ciudadReturned;
    }
    @Override
    public CiudadDTO actualizarCiudad(CiudadDTO ciudadDTO) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de actualizado de ciudad con ID= " + ciudadDTO.getId());
        Ciudad ciudad = mapper.convertValue(ciudadDTO, Ciudad.class);
        Optional<Ciudad> ciudadBuscada = ciudadRepository.findById(ciudad.getId());
        if (ciudadBuscada.isPresent()){
            Ciudad cityUpdated = ciudadRepository.save(ciudad);
            CiudadDTO ciudadDTOReturned = mapper.convertValue(cityUpdated, CiudadDTO.class);
            return ciudadDTOReturned;
        } else {
            throw new BadRequestException("Revise la informacion enviada por favor");
        }
    }
    @Override

    public Optional<CiudadDTO> buscarCiudad(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de busqueda de ciudad con ID " + id);
        Optional<Ciudad> ciudadBuscada = ciudadRepository.findById(id);
        if (ciudadBuscada.isPresent()){
            CiudadDTO ciudadDTO = mapper.convertValue(ciudadBuscada.get(), CiudadDTO.class);
            return Optional.of(ciudadDTO);
        }else{
            throw new ResourceNotFoundException("No se pudo encontrar la ciudad con ID: " + id);
        }
    }
    @Override
    public List<CiudadDTO> listaCiudad() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de ciudades ");
        List<Ciudad> ciudadList = ciudadRepository.findAll();
        List<CiudadDTO> ciudadListDto = new ArrayList<>();
        if (ciudadList.isEmpty()){
            throw new ResourceNotFoundException("La lista de ciudades se encuentra vacia");
        }
        for (Ciudad c:ciudadList) {
            CiudadDTO ciudadDTO = mapper.convertValue(c, CiudadDTO.class);
            ciudadListDto.add(ciudadDTO);
        }
        return ciudadListDto;
    }
    @Override
    public void eliminarCiudad(Long id) throws ResourceNotFoundException{
        Optional<CiudadDTO> ciudadAEliminar = buscarCiudad(id);
        if (ciudadAEliminar.isPresent()){
            ciudadRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de ciudad con id " + id);
        }
        else {

            throw new ResourceNotFoundException("La ciudad a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito en id= "+id);
        }

    }
}
