package com.henry.luz.receptor.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

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
