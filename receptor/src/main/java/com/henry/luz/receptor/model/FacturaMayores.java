package com.henry.luz.receptor.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "FacturaMayores")
public class FacturaMayores extends Factura{

    @Override
    public FacturaEnum facturaEnum(){
        return FacturaEnum.COMUN;
    }
}
