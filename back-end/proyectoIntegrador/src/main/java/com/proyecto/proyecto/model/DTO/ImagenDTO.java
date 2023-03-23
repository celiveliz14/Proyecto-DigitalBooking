package com.proyecto.proyecto.model.DTO;
import com.proyecto.proyecto.model.Producto;
import lombok.Data;
@Data
public class ImagenDTO {

    private Long id;
    private String titulo;
    private String url;
    private Producto producto;
}
