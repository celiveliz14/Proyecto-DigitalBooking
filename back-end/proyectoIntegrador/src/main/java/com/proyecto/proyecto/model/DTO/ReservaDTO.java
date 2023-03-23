package com.proyecto.proyecto.model.DTO;
import com.proyecto.proyecto.model.Users;
import com.proyecto.proyecto.model.Producto;
import lombok.Data;
import org.joda.time.LocalDate;

import java.util.Date;

@Data
public class ReservaDTO {
    private Long id;
    private String horaReserva;
    private Date fechaInicio;
    private Date fechaFinal;
    private Producto producto;
    private Users user;
}
