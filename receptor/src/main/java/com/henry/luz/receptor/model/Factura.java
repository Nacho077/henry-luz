package com.henry.luz.receptor.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "FacturaEnum")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FacturaComun.class, name = "COMUN"),
        @JsonSubTypes.Type(value = FacturaAntiguedad.class, name = "ANTIGUEDAD"),
        @JsonSubTypes.Type(value = FacturaMayores.class, name = "MAYORES")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @AccessType(AccessType.Type.PROPERTY)
    public abstract FacturaEnum facturaEnum();
    //private Cliente cliente;
    //private Domicilio domicilio;
    private Integer numeroDeMedidor;
    private Float medicionInicial, medicionFinal, totalPagar;
    private String consumoTotal;
    private Date dateMedicionInicial, dateMedicionFinal;
    private String tipoDeTarifa;
}
