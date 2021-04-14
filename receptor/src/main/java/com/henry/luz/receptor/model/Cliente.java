package com.henry.luz.receptor.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nombre, dni;

    private String apellido;

    @ManyToOne
    @JoinColumn(name = "direccion")
    private Domicilio domicilio;
}
