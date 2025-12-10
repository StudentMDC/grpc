package org.xproce.mediaclient.dto;

import lombok.Data;

@Data
public class VideoDto {
    private String id;
    private String title;
    private String description;
    private String url;
    private int duration_seconds;
    private CreatorDto creator;
}
