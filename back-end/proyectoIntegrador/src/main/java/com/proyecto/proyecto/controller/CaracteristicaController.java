package com.proyecto.proyecto.controller;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CaracteristicaDTO;
import com.proyecto.proyecto.service.impl.CaracteristicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caracteristicas")
@CrossOrigin("*")
public class CaracteristicaController {

    private CaracteristicaServiceImpl caracteristicaService;

    @Autowired
    public CaracteristicaController(CaracteristicaServiceImpl caracteristicaService) {
        this.caracteristicaService = caracteristicaService;
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> agregarCaracteristica (@RequestBody CaracteristicaDTO caracteristicaDTO) throws BadRequestException {
        CaracteristicaDTO caracteristica = caracteristicaService.agregarCaracteristica(caracteristicaDTO);
        return new ResponseEntity<>("Caracteristica guardada con ID: " + caracteristica.getId() ,null, HttpStatus.CREATED);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<String> actualizarCaracteristica (@RequestBody CaracteristicaDTO caracteristica) throws BadRequestException {
        caracteristicaService.actualizarCaracteristica(caracteristica);
        return new ResponseEntity<>("Caracteristica actualizada con ID: " + caracteristica.getId(),null, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaracteristicaDTO> buscarCaracteristicaPorId (@PathVariable Long id) throws ResourceNotFoundException {
        CaracteristicaDTO caracteristica = caracteristicaService.buscarCaracteristica(id).get();
        return new ResponseEntity<>(caracteristica,null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CaracteristicaDTO>> listarCaracteristica()throws ResourceNotFoundException{
        List<CaracteristicaDTO> caracteristicaList = caracteristicaService.listaCaracteristica();
        return new ResponseEntity<>(caracteristicaList,null, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCaracteristica(@PathVariable Long id) throws ResourceNotFoundException {
        caracteristicaService.eliminarCaracteristica(id);
        return new ResponseEntity<>("Se elimino la caracteristica con ID: " + id,null, HttpStatus.OK);
    }
}
