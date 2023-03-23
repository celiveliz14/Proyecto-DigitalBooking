package com.proyecto.proyecto.service;

import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ProductoDTO;
import com.proyecto.proyecto.model.Producto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    ProductoDTO agregarProducto(ProductoDTO productoDTO) throws BadRequestException;

    ProductoDTO actualizarProducto(ProductoDTO productoDTO) throws BadRequestException;
    Optional<ProductoDTO> buscarProducto(Long id) throws ResourceNotFoundException;
    List<ProductoDTO> listaProducto() throws ResourceNotFoundException;
    void eliminarProducto(Long id) throws ResourceNotFoundException;

    Optional<List<ProductoDTO>> findAllByCiudadId(Long id) throws ResourceNotFoundException;
    Optional<List<ProductoDTO>> findAllByCategoriaId(Long id)throws ResourceNotFoundException;
    Optional<List<ProductoDTO>> getRandomProducts() throws ResourceNotFoundException;

    Optional<List<ProductoDTO>> findAllByRangeDate(Date check_in_date, Date check_out_date) throws ResourceNotFoundException;

    Optional<List<ProductoDTO>> findAllByRangeDateAndCity(Long city_id, Date check_in_date, Date check_out_date) throws ResourceNotFoundException;

}