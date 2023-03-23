package com.proyecto.proyecto.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@Data
public class ProductoDTO{

    private Long id;
    private String titulo;
    private CategoriaDTO categoria;
    private CiudadDTO ciudad;
    private List<CaracteristicaDTO> caracteristicas;
    @JsonIgnoreProperties(value = {"producto"})
    private List<ImagenDTO> listImagen;
    private String review;
    private Integer puntuacion;
    private Integer estrellas;
    private String tituloDescripcion;
    private String descripcion;
    private String politicaLugar;
    private String politicaSaludSeguridad;
    private String politicaCancelacion;
    private Double latitud;
    private Double longitud;
    private Double altura;
    private String distanciaCentro;
}
