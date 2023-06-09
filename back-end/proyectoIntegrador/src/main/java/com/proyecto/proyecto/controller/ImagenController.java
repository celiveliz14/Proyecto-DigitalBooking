package com.proyecto.proyecto.controller;

import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ImagenDTO;
import com.proyecto.proyecto.service.impl.ImagenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin("*")
public class ImagenController {

    private ImagenServiceImpl imagenService;

    @Autowired
    public ImagenController(ImagenServiceImpl imagenService) {
        this.imagenService = imagenService;
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> registrarImagen (@RequestBody ImagenDTO imagenDTO) throws BadRequestException {
        ImagenDTO imagen = imagenService.agregarImagen(imagenDTO);
        return new ResponseEntity<>("Imagen guardada con ID: " + imagen.getId(), null, HttpStatus.CREATED);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<String> actualizarImagen (@RequestBody ImagenDTO imagenDTO) throws BadRequestException {
        imagenService.actualizarImagen(imagenDTO);
        return new ResponseEntity<>("Imagen actualizada con ID: " + imagenDTO.getId(),null, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<ImagenDTO>> listarImagen() throws ResourceNotFoundException {
        List<ImagenDTO> imagenList = imagenService.listaImagen();
        return new ResponseEntity<>(imagenList,null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenDTO> buscarImagenPorId (@PathVariable Long id) throws BadRequestException, ResourceNotFoundException {


        ImagenDTO imagenDTO = imagenService.buscarImagen(id).get();
        return new ResponseEntity<>(imagenDTO,null, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImagen(@PathVariable Long id) throws ResourceNotFoundException,BadRequestException {

        imagenService.eliminarImagen(id);
        return new ResponseEntity<>("Se elimino la imagen con ID: " + id, null, HttpStatus.OK);
    }
}
