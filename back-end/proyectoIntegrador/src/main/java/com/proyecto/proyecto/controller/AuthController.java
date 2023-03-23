package com.proyecto.proyecto.controller;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.UserDTO;
import com.proyecto.proyecto.security.UserSecurity.dao.JpaUserDetailsService;
import com.proyecto.proyecto.security.UserSecurity.model.UserSecurity;
import com.proyecto.proyecto.model.DTO.AuthenticationRequest;
import com.proyecto.proyecto.service.AuthService;
import com.proyecto.proyecto.security.config.JwtUtils;
import com.proyecto.proyecto.model.DTO.UsersRequest;
import com.proyecto.proyecto.service.impl.UsersServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JpaUserDetailsService jpaUserDetailsService;

    private final AuthService authService;

    private final JwtUtils jwtUtils;

    private final UsersServiceImpl usersService;

    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) throws ResourceNotFoundException {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(),
                            new ArrayList<>()));
            final UserDetails user = jpaUserDetailsService.loadUserByUsername(request.getEmail());
            if (user != null) {
                String jwt = jwtUtils.generateToken(user);
                Cookie cookie = new Cookie("jwt", jwt);
                cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
                cookie.setHttpOnly(true);
                cookie.setPath("/"); // Global
                response.addCookie(cookie);
                var userSearched = usersService.findByEmail(request.getEmail()).get();
                userSearched.setToken(jwt);
                return ResponseEntity.ok(userSearched);
            }else{
            throw new ResourceNotFoundException("Usuario invalido");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsersRequest user) throws Exception {
        authService.addUser(user).map(UserSecurity::new).orElseThrow(() -> new Exception("Unknown"));
        return new ResponseEntity<>("Usuario guardado: " + user.getEmail() ,null, HttpStatus.CREATED);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody UsersRequest user) throws Exception {
        authService.addAdmin(user).map(UserSecurity::new).orElseThrow(() -> new Exception("Unknown"));
        return new ResponseEntity<>("Admin guardado: " + user.getEmail() ,null, HttpStatus.CREATED);
    }


}
