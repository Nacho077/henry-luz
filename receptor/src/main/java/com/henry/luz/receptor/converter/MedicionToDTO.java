package com.henry.luz.receptor.converter;

import com.henry.luz.receptor.model.MedicionDTO;
import com.henry.luz.receptor.model.Mediciones;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MedicionToDTO implements Converter<Mediciones, MedicionDTO> {

    private final ModelMapper modelMapper;

    public MedicionToDTO(final ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicionDTO convert(Mediciones mediciones) {
        return modelMapper.map(mediciones, MedicionDTO.class);
    }
}
