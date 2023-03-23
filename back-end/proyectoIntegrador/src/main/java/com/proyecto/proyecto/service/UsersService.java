package com.proyecto.proyecto.service;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.UserDTO;
import com.proyecto.proyecto.model.DTO.UsersRequest;
import com.proyecto.proyecto.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    Optional<UserDTO> findByEmail(String email) throws ResourceNotFoundException;
}
