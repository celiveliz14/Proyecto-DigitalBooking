//package com.proyecto.proyecto.controller;
//
//import com.proyecto.proyecto.exception.ResourceNotFoundException;
//import com.proyecto.proyecto.model.DTO.CaracteristicaDTO;
//import com.proyecto.proyecto.model.DTO.ReservaDTO;
//import com.proyecto.proyecto.model.DTO.UserDTO;
//import com.proyecto.proyecto.service.impl.UsersServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/users")
//@RequiredArgsConstructor
//@CrossOrigin("*")
//public class UsersController {
//
//    private final UsersServiceImpl usersService;
//
//    @GetMapping("/{email}")
//    public ResponseEntity<UserDTO> buscarUserPorEmail(@PathVariable String email) throws ResourceNotFoundException{
//        UserDTO user = usersService.findByEmail(email).get();
//        return new ResponseEntity<>(user,null,HttpStatus.OK);
//    }
//
//
//
//}
