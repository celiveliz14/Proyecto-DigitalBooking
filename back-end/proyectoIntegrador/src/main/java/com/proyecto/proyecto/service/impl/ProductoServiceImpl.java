package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ImagenDTO;
import com.proyecto.proyecto.model.DTO.ProductoDTO;
import com.proyecto.proyecto.model.Producto;
import com.proyecto.proyecto.repository.ProductoRepository;
import com.proyecto.proyecto.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository productoRepository;
    private ImagenServiceImpl imagenService;
    private static final Logger LOGGER = Logger.getLogger(ImagenServiceImpl.class);

    @Autowired
    ObjectMapper mapper;


    public ProductoServiceImpl(ProductoRepository productoRepository, ImagenServiceImpl imagenService) {
        this.productoRepository = productoRepository;
        this.imagenService = imagenService;
    }

    @Override
    public ProductoDTO agregarProducto(ProductoDTO productoDTO) throws BadRequestException {
        if ((productoDTO) == null){
            throw new BadRequestException("Revise la informacion enviada por favor");
        }
        List<ImagenDTO> imagenList = productoDTO.getListImagen();
        productoDTO.setListImagen(new ArrayList<>());
        Producto producto = mapper.convertValue(productoDTO, Producto.class);
        Producto productoSaved = productoRepository.save(producto);
        ProductoDTO productoReturned = mapper.convertValue(productoSaved, ProductoDTO.class);
        productoReturned.setListImagen(imagenList.stream().map(imagen -> {
            imagen.setProducto(productoSaved);
            try {
                return imagenService.agregarImagen(imagen);
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList()));
        return productoReturned;

    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de actualizado de producto con titulo = " + productoDTO.getTitulo());
        return agregarProducto(productoDTO);
    }

    @Override
    public Optional<ProductoDTO> buscarProducto(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de busqueda de producto con ID " + id);
        Optional<Producto> productoBuscado = productoRepository.findById(id);
        if (productoBuscado.isPresent()){
            ProductoDTO productoDTO= mapper.convertValue(productoBuscado.get(),ProductoDTO.class);
            return Optional.of(productoDTO);
        }else{
            throw new ResourceNotFoundException("No se pudo encontrar el producto con ID: " + id);
        }
    }
    @Override
    public List<ProductoDTO> listaProducto() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de productos ");
        List<Producto> productoList = productoRepository.findAll();
        List<ProductoDTO> listReturn = new ArrayList<>();
        if (productoList.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        for (Producto p:productoList) {
            ProductoDTO productoDTO = mapper.convertValue(p, ProductoDTO.class);
            listReturn.add(productoDTO);
        }
        return listReturn;
    }

    @Override
    public void eliminarProducto(Long id) throws ResourceNotFoundException{
        Optional<ProductoDTO> productoAEliminar = buscarProducto(id);
        if (productoAEliminar.isPresent()){
            productoRepository.deleteById(id);
        }
        else {

            throw new ResourceNotFoundException("La producto a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito en id= "+id);
        }
    }

    public Optional<List<ProductoDTO>> findAllByCiudadId(Long id)throws ResourceNotFoundException{
        LOGGER.info("Se inicio una operacion de listado de productos x ciudad Id: " + id);
        Optional<List<Producto>> productoList = productoRepository.findAllByCiudadId(id);
        List<ProductoDTO> listReturn = new ArrayList<>();
        if (productoList.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        for (Producto p:productoList.get()) {
            ProductoDTO productoDTO = mapper.convertValue(p, ProductoDTO.class);
            listReturn.add(productoDTO);
        }
        return Optional.of(listReturn);
    }

    public Optional<List<ProductoDTO>> findAllByCategoriaId(Long id) throws ResourceNotFoundException{
        LOGGER.info("Se inicio una operacion de listado de productos x Categoria Id: " + id);
        Optional<List<Producto>> productoList = productoRepository.findAllByCategoriaId(id);
        List<ProductoDTO> listReturn = new ArrayList<>();
        if (productoList.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        for (Producto p:productoList.get()) {
            ProductoDTO productoDTO = mapper.convertValue(p, ProductoDTO.class);
            listReturn.add(productoDTO);
        }
        return Optional.of(listReturn);
    }

    public Optional<List<ProductoDTO>> getRandomProducts() throws ResourceNotFoundException{
        LOGGER.info("Se inicio una operacion de listado de productos random");
        List<Producto> lista = productoRepository.findAll().stream().limit(8).collect(Collectors.toList());
        Collections.shuffle(lista);
        List<ProductoDTO> listaReturn = new ArrayList<>();
        if (lista.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        for (Producto p : lista) {
            ProductoDTO productoDTO = mapper.convertValue(p, ProductoDTO.class);
            listaReturn.add(productoDTO);
        }
        return Optional.of(listaReturn);
    }

    @Override
    public Optional<List<ProductoDTO>> findAllByRangeDate(Date check_in_date, Date check_out_date) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de productos por fecha de inicio y fin");
        List<Producto> productoList = productoRepository.findAllByRangeDate(check_in_date,check_out_date);
        List<ProductoDTO> listReturn = new ArrayList<>();
        if (productoList.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        for (Producto p:productoList) {
            ProductoDTO productoDTO = mapper.convertValue(p, ProductoDTO.class);
            listReturn.add(productoDTO);
        }
        return Optional.of(listReturn);
    }

    @Override
    public Optional<List<ProductoDTO>> findAllByRangeDateAndCity(Long city_id, Date check_in_date, Date check_out_date) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de productos por ciudad, fecha de inicio y fin");
        List<Producto> productoList = productoRepository.findAllByRangeDateAndCity(city_id, check_in_date, check_out_date);
        List<ProductoDTO> listReturn = new ArrayList<>();
        if (productoList.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        for (Producto p:productoList) {
            ProductoDTO productoDTO = mapper.convertValue(p, ProductoDTO.class);
            listReturn.add(productoDTO);
        }
        return Optional.of(listReturn);
    }


}
