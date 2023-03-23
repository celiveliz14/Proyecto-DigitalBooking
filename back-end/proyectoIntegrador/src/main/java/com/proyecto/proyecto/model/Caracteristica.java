package com.proyecto.proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "caracteristicas")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;

    @Column

    private String icono;
    @JsonIgnore
    @ManyToMany(mappedBy = "caracteristicas", fetch = FetchType.LAZY)
    private Set<Producto> productosSet = new HashSet<>();
    
}
