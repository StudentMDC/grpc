package org.xproce.mediaserver.mapper;


import org.xproce.lab.Creator;
import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;
import org.xproce.mediaserver.dao.entities.CreatorDao;
import org.xproce.mediaserver.dao.entities.VideoDao;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VideoMapper {

    public VideoDao fromUploadVideoRequestToVideoDao(UploadVideoRequest request) {
        VideoDao videoDao = new VideoDao();
        videoDao.setId(UUID.randomUUID().toString());
        videoDao.setTitle(request.getTitle());
        videoDao.setDescription(request.getDescription());
        videoDao.setUrl(request.getUrl());
        videoDao.setDuration_seconds(request.getDurationSeconds());

        if (request.hasCreator()) {
            Creator creator = request.getCreator();
            CreatorDao creatorDao = new CreatorDao();
            creatorDao.setId(creator.getId().isEmpty() ? UUID.randomUUID().toString() : creator.getId());
            creatorDao.setName(creator.getName());
            creatorDao.setEmail(creator.getEmail());
            videoDao.setCreator(creatorDao);
        }

        return videoDao;
    }

    public Video fromVideoDaoToVideo(VideoDao videoDao) {
        Video.Builder builder = Video.newBuilder()
                .setId(videoDao.getId())
                .setTitle(videoDao.getTitle())
                .setDescription(videoDao.getDescription())
                .setUrl(videoDao.getUrl())
                .setDurationSeconds(videoDao.getDuration_seconds());

        if (videoDao.getCreator() != null) {
            CreatorDao creatorDao = videoDao.getCreator();
            Creator creator = Creator.newBuilder()
                    .setId(creatorDao.getId())
                    .setName(creatorDao.getName())
                    .setEmail(creatorDao.getEmail())
                    .build();
            builder.setCreator(creator);
        }

        return builder.build();
    }
}

