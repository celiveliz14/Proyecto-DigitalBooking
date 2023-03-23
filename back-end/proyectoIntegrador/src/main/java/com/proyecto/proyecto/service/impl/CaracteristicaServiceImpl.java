package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Caracteristica;
import com.proyecto.proyecto.model.DTO.CaracteristicaDTO;
import com.proyecto.proyecto.repository.CaracteristicaRepository;
import com.proyecto.proyecto.service.CaracteristicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CaracteristicaServiceImpl implements CaracteristicaService{

    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(CaracteristicaServiceImpl.class);

    public CaracteristicaServiceImpl(CaracteristicaRepository caracteristicaRepository) {
        this.caracteristicaRepository = caracteristicaRepository;
    }


    @Override
    public CaracteristicaDTO agregarCaracteristica(CaracteristicaDTO caracteristicaDTO) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de guardado de la caracteristica con nombre: " + caracteristicaDTO.getNombre());
        Caracteristica caracteristica = mapper.convertValue(caracteristicaDTO, Caracteristica.class );
        if (caracteristica == null){
            throw new BadRequestException("Revise la informacion enviada por favor");
        }else{
            caracteristicaRepository.save(caracteristica);
            CaracteristicaDTO caracteristicaReturn = mapper.convertValue(caracteristica,CaracteristicaDTO.class);
            return caracteristicaReturn;
        }

    }

    @Override
    public CaracteristicaDTO actualizarCaracteristica(CaracteristicaDTO caracteristicaDTO) throws BadRequestException {
        LOGGER.info("Se inicio una operacion de actualizado de caracteristica con ID= " + caracteristicaDTO.getId());
        Caracteristica caracteristica = mapper.convertValue(caracteristicaDTO, Caracteristica.class);
        Optional<Caracteristica> caracteristicaBuscada= caracteristicaRepository.findById(caracteristica.getId());
        if (caracteristicaBuscada.isPresent()){
            Caracteristica caracteristicaUpdated = caracteristicaRepository.save(caracteristica);
            CaracteristicaDTO caracteristicaDTOReturned= mapper.convertValue(caracteristicaUpdated, CaracteristicaDTO.class);
            return caracteristicaDTOReturned;
        }else {
            throw new BadRequestException("Revise la informacion enviada.");
        }


    }

    @Override
    public Optional<CaracteristicaDTO> buscarCaracteristica(Long id) throws ResourceNotFoundException{
        LOGGER.info("Se inicio una operacion de busqueda de caracteristica con ID " + id);
        Optional<Caracteristica> caracteristicaBuscada = caracteristicaRepository.findById(id);
        if (caracteristicaBuscada.isPresent()){
            CaracteristicaDTO caracteristicaDTOReturned = mapper.convertValue(caracteristicaBuscada.get(), CaracteristicaDTO.class);
            return Optional.of(caracteristicaDTOReturned);
        }else{
            throw new ResourceNotFoundException("No se pudo encontrar la caracteristica con ID: " + id);
        }
    }

    @Override
    public List<CaracteristicaDTO> listaCaracteristica() throws ResourceNotFoundException{
        LOGGER.info("Se inicio una operacion de listado de caracteristicas ");
        List<Caracteristica> caracteristicaList = caracteristicaRepository.findAll();
        List<CaracteristicaDTO> caracteristicaDTOList = new ArrayList<>();
        if (caracteristicaList.isEmpty()){
            throw new ResourceNotFoundException("La lista de caracteristicas se encuentra vacia");
        }
        for (Caracteristica c:caracteristicaList) {
            CaracteristicaDTO caracteristicaDTO = mapper.convertValue(c, CaracteristicaDTO.class);
            caracteristicaDTOList.add(caracteristicaDTO);
        }
        return caracteristicaDTOList;

    }

    @Override
    public void eliminarCaracteristica(Long id) throws ResourceNotFoundException{
        Optional<CaracteristicaDTO> caracteristicaAEliminar = buscarCaracteristica(id);
        if (caracteristicaAEliminar.isPresent()){
            caracteristicaRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de caracteristica con id " + id);
        }
        else {
            throw new ResourceNotFoundException("No se pudo eliminar la caracteristica con ID: " + id);
        }
    }
}
