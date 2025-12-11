package org.xproce.mediaclient.mapper;

import org.xproce.lab.Video;
import org.xproce.lab.VideoStream;
import org.xproce.mediaclient.dto.CreatorDto;
import org.xproce.mediaclient.dto.VideoDto;
import org.xproce.mediaclient.dto.VideoStreamDto;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VideoMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public VideoDto fromVideoProtoToVideoDto(Video video) {
        VideoDto videoDto = new VideoDto();

        modelMapper.map(video, videoDto);

        if (video.hasCreator()) {
            CreatorDto creatorDto = modelMapper.map(video.getCreator(), CreatorDto.class);
            try {
                java.lang.reflect.Field creatorField = VideoDto.class.getDeclaredField("creator");
                creatorField.setAccessible(true);
                creatorField.set(videoDto, creatorDto);
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors du mapping du creator", e);
            }
        }

        return videoDto;
    }

    public VideoStreamDto fromVideoStreamProtoToVideoStreamDto(VideoStream videoStream) {
        List<VideoDto> videoDtos = videoStream.getVideosList().stream()
                .map(this::fromVideoProtoToVideoDto)
                .collect(Collectors.toList());
        VideoStreamDto videoStreamDto = new VideoStreamDto();
        try {
            java.lang.reflect.Field videosField = VideoStreamDto.class.getDeclaredField("videos");
            videosField.setAccessible(true);
            videosField.set(videoStreamDto, videoDtos);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du mapping des videos", e);
        }
        return videoStreamDto;
    }
}

