package com.proyecto.proyecto.service.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.UserDTO;
import com.proyecto.proyecto.model.Users;
import com.proyecto.proyecto.repository.UsersRepository;
import com.proyecto.proyecto.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public Optional<UserDTO> findByEmail(String email) throws ResourceNotFoundException {
        var user = usersRepository.findByEmail(email);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("El usuario no se encuentra registrado");
        }else{
           var userReturn = new UserDTO();
           userReturn.setId(user.get().getId());
           userReturn.setFirst_name(user.get().getFirst_name());
           userReturn.setLast_name(user.get().getLast_name());
           userReturn.setEmail(user.get().getEmail());
           return Optional.of(userReturn);
        }
    }
}
