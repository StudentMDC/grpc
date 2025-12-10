package org.xproce.mediaclient.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xproce.lab.Video;
import org.xproce.mediaclient.dto.VideoDto;

@Component
public class VideoMapper {
    private ModelMapper mapper = new ModelMapper();
    public VideoDto fromVideoProtoTovideoDto(Video videoproto){
        return mapper.map(videoproto, VideoDto.class);
    }
}
