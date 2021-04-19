package com.henry.luz.receptor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MedicionDTO {
    private Integer id;
    private Date fecha;
    private Float medicion;
}
