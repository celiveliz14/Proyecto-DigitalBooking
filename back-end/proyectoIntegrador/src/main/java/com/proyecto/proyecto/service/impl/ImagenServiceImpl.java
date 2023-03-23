package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ImagenDTO;
import com.proyecto.proyecto.model.Imagen;
import com.proyecto.proyecto.repository.ImagenRepository;
import com.proyecto.proyecto.service.ImagenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenServiceImpl implements ImagenService {

    private ImagenRepository imagenRepository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(ImagenServiceImpl.class);

    public ImagenServiceImpl(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    @Override
    public ImagenDTO agregarImagen(ImagenDTO imagenDTO) throws BadRequestException {
        LOGGER.info("Se inicio una operacion de guardado de la imagen con titulo " + imagenDTO.getTitulo());
        Imagen imagen = mapper.convertValue(imagenDTO, Imagen.class );
        if (imagen == null){
            throw new BadRequestException("Revise la informacion enviada por favor");
        }
        imagenRepository.save(imagen);
        ImagenDTO imagenReturn = mapper.convertValue(imagen,ImagenDTO.class);
        return imagenReturn;
    }
    @Override
    public ImagenDTO actualizarImagen(ImagenDTO imagenDTO) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de actualizado de imagen con ID= " + imagenDTO.getId());
        Imagen imagen = mapper.convertValue(imagenDTO, Imagen.class);
        Optional<Imagen> imagenBuscada = imagenRepository.findById(imagen.getId());
        if (imagenBuscada.isPresent()){
            Imagen imagenUpdated = imagenRepository.save(imagen);
            ImagenDTO imagenReturn = mapper.convertValue(imagenUpdated,ImagenDTO.class);
            return imagenReturn;
        } else {
            throw new BadRequestException("Revise la informacion enviada por favor");
        }
    }
    @Override
    public Optional<ImagenDTO> buscarImagen(Long id) throws  ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de busqueda de imagen con ID " + id);
        Optional<Imagen> imagenBuscada = imagenRepository.findById(id);
        if (imagenBuscada.isPresent()){
            ImagenDTO imagenDTO= mapper.convertValue(imagenBuscada.get(),ImagenDTO.class);
            return Optional.of(imagenDTO);
        }else{
            throw new ResourceNotFoundException("No se pudo encontrar la imagen con ID: " + id);
        }
    }
    @Override
    public List<ImagenDTO> listaImagen() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de imagenes ");
        List<Imagen> imagenList = imagenRepository.findAll();
        List<ImagenDTO> listReturn = new ArrayList<>();
        if (imagenList.isEmpty()){
            throw new ResourceNotFoundException("La lista de imagenes se encuentra vacia");
        }
        for (Imagen i:imagenList) {
            ImagenDTO imagenDTO = mapper.convertValue(i, ImagenDTO.class);
            listReturn.add(imagenDTO);
        }
        return listReturn;
    }

    @Override
    public void eliminarImagen(Long id) throws ResourceNotFoundException {
        Optional<ImagenDTO> imagenAEliminar = buscarImagen(id);
        if (imagenAEliminar.isPresent()){
            imagenRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de imagen con id " + id);
        }
        else {
            throw new ResourceNotFoundException("La imagen a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito el id= "+id);
        }
    }
}
