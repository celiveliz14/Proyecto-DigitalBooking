package com.proyecto.proyecto.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String token;
}
