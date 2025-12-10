package org.xproce.mediaclient.controller;

import org.xproce.mediaclient.dto.CreatorDto;
import org.xproce.mediaclient.dto.VideoStreamDto;
import org.xproce.mediaclient.service.CreatorServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creators")
public class CreatorController {

    @Autowired
    private CreatorServiceClient creatorService;

    @GetMapping("/{id}")
    public CreatorDto getCreator(@PathVariable String id) {
        return creatorService.getCreator(id);
    }

    @GetMapping("/{id}/videos")
    public VideoStreamDto getCreatorVideos(@PathVariable String id) {
        return creatorService.getCreatorVideos(id);
    }
}

