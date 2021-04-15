package com.henry.luz.receptor.model;

public enum FacturaEnum {

    COMUN(100),
    ANTIGUEDAD(80),
    MAYORES(60);

    private Integer precio;

    FacturaEnum(Integer precio){
        this.precio = precio;
    }

    public Integer getPrecio(){
        return precio;
    }

    public static FacturaEnum find(Integer precio){
        for(FacturaEnum f: values()){
            if(f.equals(precio)){
                return f;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid Factura type: %s", precio));
    }
}
