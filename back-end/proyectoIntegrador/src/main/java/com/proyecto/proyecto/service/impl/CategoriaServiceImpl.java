package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Categoria;
import com.proyecto.proyecto.model.DTO.CategoriaDTO;
import com.proyecto.proyecto.repository.CategoriaRepository;
import com.proyecto.proyecto.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(CategoriaServiceImpl.class);

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO) throws BadRequestException {
        LOGGER.info("Se inicio una operacion de guardado de la categoria con titulo " + categoriaDTO.getTitulo());
        Categoria categoria = mapper.convertValue(categoriaDTO, Categoria.class );
        if (categoria == null){
            throw new BadRequestException("Revise la informacion enviada por favor");
        }
        categoriaRepository.save(categoria);
        CategoriaDTO categoriaReturn = mapper.convertValue(categoria,CategoriaDTO.class);
        return categoriaReturn;
    }
    @Override
    public CategoriaDTO actualizarCategoria(CategoriaDTO categoriaDTO) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de actualizado de categoria con ID= " + categoriaDTO.getId());
        Categoria categoria = mapper.convertValue(categoriaDTO, Categoria.class);
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(categoria.getId());
        if (categoriaBuscada.isPresent()){
            Categoria categoriaUpdated = categoriaRepository.save(categoria);
            CategoriaDTO categoriaReturn = mapper.convertValue(categoriaUpdated,CategoriaDTO.class);
            return categoriaReturn;
        } else {
            throw new BadRequestException("Revise la informacion enviada por favor");
        }
    }
    @Override
    public Optional<CategoriaDTO> buscarCategoria(Long id) throws  ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de busqueda de categoria con ID " + id);
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(id);
        if (categoriaBuscada.isPresent()){
            CategoriaDTO categoriaDTO= mapper.convertValue(categoriaBuscada.get(),CategoriaDTO.class);
            return Optional.of(categoriaDTO);
        }else{
            throw new ResourceNotFoundException("No se pudo encontrar la categoria con ID: " + id);
        }
    }
    @Override
    public List<CategoriaDTO> listaCategoria() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de categorias ");
        List<Categoria> categoriaList = categoriaRepository.findAll();
        List<CategoriaDTO> listReturn = new ArrayList<>();
        if (categoriaList.isEmpty()){
            throw new ResourceNotFoundException("La lista de caracteristicas se encuentra vacia");
        }
        for (Categoria c:categoriaList) {
            CategoriaDTO categoriaDTO = mapper.convertValue(c, CategoriaDTO.class);
            listReturn.add(categoriaDTO);
        }
        return listReturn;
    }

    @Override
    public void eliminarCategoria(Long id) throws ResourceNotFoundException {
        Optional<CategoriaDTO> categoriaAEliminar = buscarCategoria(id);
        if (categoriaAEliminar.isPresent()){
            categoriaRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de categoria con id " + id);
        }
        else {
            throw new ResourceNotFoundException("La categoria a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito el id= "+id);
        }
    }
}