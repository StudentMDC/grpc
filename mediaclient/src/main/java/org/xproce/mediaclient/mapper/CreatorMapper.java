package org.xproce.mediaclient.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;
import org.xproce.mediaclient.dto.CreatorDto;

@Component
public class CreatorMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public CreatorDto fromCreatorProtoToCreatorDto(Creator creator) {
        return modelMapper.map(creator, CreatorDto.class);
    }
}

