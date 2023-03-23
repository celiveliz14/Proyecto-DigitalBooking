package com.proyecto.proyecto.model.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersRequest {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
}
