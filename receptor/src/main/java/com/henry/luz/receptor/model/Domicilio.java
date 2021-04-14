package com.henry.luz.receptor.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Domicilio {

    @Id
    private String id;

    @NotNull
    private String direccion;

    private Medidor medidor;
}
