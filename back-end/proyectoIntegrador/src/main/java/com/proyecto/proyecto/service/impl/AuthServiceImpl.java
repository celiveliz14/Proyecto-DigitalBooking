package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.UsersRequest;
import com.proyecto.proyecto.model.Rol;
import com.proyecto.proyecto.model.Users;
import com.proyecto.proyecto.repository.RolRepository;
import com.proyecto.proyecto.repository.UsersRepository;
import com.proyecto.proyecto.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UsersRepository usersRepository;
    private final RolRepository rolRepository;

    private final EmailSenderServiceImpl emailSenderService;

    public Optional<Users> addUser(UsersRequest user) {
        var rolUser = rolRepository.findById(1L);
        var rolesList = new ArrayList<Rol>();
        rolesList.add(rolUser.get());
        Users newUser = new Users();
        newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setRoles(rolesList);
        var userSaved =  Optional.of(usersRepository.save(newUser));
        emailSenderService.sendEmail(user.getEmail(),
                "Usuario registrado exitosamente",
                "Bienvenido " + user.getFirst_name() + " " + user.getLast_name() +  " a Digital Booking!!");
        return userSaved;
    }

    public Optional<Users> addAdmin(UsersRequest user) {
        var rolUser = rolRepository.findById(1L);
        var rolAdmin = rolRepository.findById(2L);
        var rolesList = new ArrayList<Rol>();
        rolesList.add(rolUser.get());
        rolesList.add(rolAdmin.get());
        Users newUser = new Users();
        newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setRoles(rolesList);
        var adminSaved = Optional.of(usersRepository.save(newUser));
        emailSenderService.sendEmail(user.getEmail(),
                "Administrador registrado exitosamente",
                "Bienvenido " + user.getFirst_name() + " " + user.getLast_name() +  "!! sos parte de los administradores de Digital Booking!!");
        return adminSaved;
    }


}
