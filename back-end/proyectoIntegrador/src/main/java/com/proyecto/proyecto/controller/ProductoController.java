package com.proyecto.proyecto.controller;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ProductoDTO;
import com.proyecto.proyecto.service.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductoController {

    private ProductoServiceImpl productoService;

    @Autowired
    public ProductoController(ProductoServiceImpl productoService) {
        this.productoService = productoService;
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> registrarProducto (@RequestBody ProductoDTO productoDTO) throws BadRequestException {
        ProductoDTO producto = productoService.agregarProducto(productoDTO);
        return new ResponseEntity<>("Producto guardado con ID: " + producto.getId(), null, HttpStatus.CREATED);
    }


    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<String> actualizarProducto (@RequestBody ProductoDTO productoDTO) throws BadRequestException, ResourceNotFoundException {
        productoService.actualizarProducto(productoDTO);
        return new ResponseEntity<>("Producto actualizado con titulo: " + productoDTO.getTitulo(),null, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() throws ResourceNotFoundException {
        List<ProductoDTO> productoList = productoService.listaProducto();
        return new ResponseEntity<>(productoList,null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarProductoPorId (@PathVariable Long id) throws BadRequestException, ResourceNotFoundException {
        ProductoDTO productoDTO = productoService.buscarProducto(id).get();
        return new ResponseEntity<>(productoDTO,null, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) throws ResourceNotFoundException,BadRequestException {
        productoService.eliminarProducto(id);
        return new ResponseEntity<>("Se elimino el producto con ID: " + id, null, HttpStatus.OK);
    }

    @GetMapping("/ciudad/{id}")
    public ResponseEntity<List<ProductoDTO>> findAllByCiudadId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productoService.findAllByCiudadId(id).get());
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProductoDTO>> findAllByCategoriaId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productoService.findAllByCategoriaId(id).get());
    }

    @GetMapping("/random")
    public ResponseEntity<Optional<List<ProductoDTO>>> getRandomProducts() throws ResourceNotFoundException{
        return ResponseEntity.ok(productoService.getRandomProducts());
    }

    @GetMapping("/date")
    public ResponseEntity<Optional<List<ProductoDTO>>> findAllByRangeDate(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
            @RequestParam("fechaFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFinal)
            throws ResourceNotFoundException{
        Optional<List<ProductoDTO>> list = (productoService.findAllByRangeDate(fechaInicio, fechaFinal));
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista se encuentra vacia");
        }else{
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/ciudadDate/{startDate}/{endDate}/{cityId}")
    public ResponseEntity<Optional<List<ProductoDTO>>> findAllByRangeDateAndCity
            (@PathVariable Long cityId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
            throws ResourceNotFoundException{
        Optional<List<ProductoDTO>> list = (productoService.findAllByRangeDateAndCity(cityId, startDate, endDate));
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista se encuentra vacia");
        }else{
            return ResponseEntity.ok(list);
        }
    }



}