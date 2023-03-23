package com.proyecto.proyecto.service;

import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CiudadDTO;

import java.util.List;
import java.util.Optional;

public interface CiudadService {
    CiudadDTO agregarCiudad(CiudadDTO ciudadDTO) throws BadRequestException;

    CiudadDTO actualizarCiudad(CiudadDTO ciudadDTO) throws BadRequestException;
    Optional<CiudadDTO> buscarCiudad(Long id) throws ResourceNotFoundException;
    List<CiudadDTO> listaCiudad() throws ResourceNotFoundException;
    void eliminarCiudad(Long id) throws ResourceNotFoundException;
}
