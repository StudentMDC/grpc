package org.xproce.mediaserver.mapper;

import org.springframework.stereotype.Component;


import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;

import java.util.UUID;

@Component
public class VideoMapper {

    public Video fromUploadVideoRequestToVideo(UploadVideoRequest uploadVideoRequest) {
        if (uploadVideoRequest == null) return null;

        return Video.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setTitle(uploadVideoRequest.getTitle())
                .setDescription(uploadVideoRequest.getDescription())
                .setUrl(uploadVideoRequest.getUrl())
                .setDurationSeconds(uploadVideoRequest.getDurationSeconds())
                .setCreator(uploadVideoRequest.getCreator())
                .build();
    }
}

