package com.henry.luz.receptor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //private Cliente cliente;
    //private Domicilio domicilio;
    private Integer numeroDeMedidor;
    private Float medicionInicial, medicionFinal, totalPagar;
    private String consumoTotal;
    private Date dateMedicionInicial, dateMedicionFinal;
    private String tipoDeTarifa;
}
