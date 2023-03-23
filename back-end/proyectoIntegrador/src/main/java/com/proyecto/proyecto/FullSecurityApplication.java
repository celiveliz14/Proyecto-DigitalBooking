package com.proyecto.proyecto;

import com.proyecto.proyecto.model.DTO.UsersRequest;
import com.proyecto.proyecto.model.Rol;
import com.proyecto.proyecto.model.Users;
import com.proyecto.proyecto.repository.RolRepository;
import com.proyecto.proyecto.repository.UsersRepository;
import com.proyecto.proyecto.service.impl.AuthServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FullSecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(FullSecurityApplication.class, args);}
}
