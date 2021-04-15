package com.henry.luz.receptor.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "FacturaAntiguedad")
public class FacturaAntiguedad extends Factura{

    @Override
    public FacturaEnum facturaEnum(){
        return FacturaEnum.COMUN;
    }
}
