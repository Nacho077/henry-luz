package com.henry.luz.receptor.converter;

import com.henry.luz.receptor.model.MedicionDTO;
import com.henry.luz.receptor.model.Mediciones;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicionListToDTO implements Converter<List<Mediciones>, List<MedicionDTO>> {

    private final ModelMapper modelMapper;

    public MedicionListToDTO(final ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MedicionDTO> convert(List<Mediciones> mediciones) {
        return modelMapper.map(mediciones, new TypeToken<List<MedicionDTO>>(){}.getType());
    }
}
