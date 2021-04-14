package com.henry.luz.receptor.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Cliente {

    @Id
    private String id;

    @NotNull
    private String nombre, dni;

    private String apellido;

    private Domicilio domicilio;
}
