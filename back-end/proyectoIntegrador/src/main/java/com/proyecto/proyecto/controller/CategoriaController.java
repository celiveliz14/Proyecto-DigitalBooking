package com.proyecto.proyecto.controller;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CategoriaDTO;
import com.proyecto.proyecto.service.impl.CategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {

    private CategoriaServiceImpl categoriaService;

    @Autowired
    public CategoriaController(CategoriaServiceImpl categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> registrarCategoria (@RequestBody CategoriaDTO categoriaDTO) throws BadRequestException {
        CategoriaDTO categoria = categoriaService.agregarCategoria(categoriaDTO);
        return new ResponseEntity<>("Categoria guardada con ID: " + categoria.getId() ,null, HttpStatus.CREATED);
    }


    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<String> actualizarCategoria (@RequestBody CategoriaDTO categoria) throws BadRequestException, ResourceNotFoundException {
        categoriaService.actualizarCategoria(categoria);
        return new ResponseEntity<>("Categoria actualizada con ID: " + categoria.getId(),null, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() throws ResourceNotFoundException {
        List<CategoriaDTO> categoriaList = categoriaService.listaCategoria();
        return new ResponseEntity<>(categoriaList,null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarCategoriaPorId (@PathVariable Long id) throws BadRequestException, ResourceNotFoundException {
        CategoriaDTO categoria = categoriaService.buscarCategoria(id).get();
        return new ResponseEntity<>(categoria,null, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) throws ResourceNotFoundException,BadRequestException {
        categoriaService.eliminarCategoria(id);
        return new ResponseEntity<>("Se elimino la categoria con ID: " + id,null, HttpStatus.OK);
    }
}
