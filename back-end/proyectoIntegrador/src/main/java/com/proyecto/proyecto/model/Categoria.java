package com.proyecto.proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private String descripcion;

    @Column
    private String urlImagen;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Producto> productos = new HashSet<>();

}
