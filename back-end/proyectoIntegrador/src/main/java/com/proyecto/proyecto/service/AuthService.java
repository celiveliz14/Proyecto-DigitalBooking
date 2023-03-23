package com.proyecto.proyecto.service;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.UsersRequest;
import com.proyecto.proyecto.model.Users;

import java.util.Optional;

public interface AuthService {
    public Optional<Users> addUser(UsersRequest user);
    public Optional<Users> addAdmin(UsersRequest user);

}
