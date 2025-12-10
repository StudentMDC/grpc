package org.xproce.mediaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Creator;

import org.xproce.mediaclient.service.VideoServiceClient;
import org.xproce.mediaclient.dto.VideoDto;


@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoServiceClient videoService;

    @PostMapping("/addVideo")
    public VideoDto uploadVideo() {
        Creator creator = Creator.newBuilder()
                .setName("mariam")
                .setEmail("https://github.com/StudentMDC")
                .setId("2")
                .build();

        UploadVideoRequest request = UploadVideoRequest.newBuilder()
                .setTitle("grpc")
                .setDescription("desc")
                .setUrl("https://github.com/StudentMDC")
                .setDurationSeconds(380)
                .setCreator(creator)
                .build();

        VideoDto videoDto = videoService.uploadVideo(request);
        System.out.println(videoDto);
        return videoDto;
    }

    @GetMapping("/{id}")
    public VideoDto getVideo(@PathVariable String id) {
        return videoService.getVideo(id);
    }
}

