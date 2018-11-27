package com.example.celebyoutube.channel.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
public class ChannelSaveResponseDto {

    private String id;
    private String name;
    private String introVideoUrl;
    private String title;
    private String content;
    private String image;
    private Long subscriber;
    private LocalDate joinDate;
    private Long views;
    private String updatedTime;

    @Builder
    public ChannelSaveResponseDto(String id, String name, String introVideoUrl, String title, String content, String image, Long subscriber, LocalDate joinDate, Long views, String updatedTime) {
        this.id = id;
        this.name = name;
        this.introVideoUrl = introVideoUrl;
        this.title = title;
        this.content = content;
        this.image = image;
        this.subscriber = subscriber;
        this.joinDate = joinDate;
        this.views = views;
        this.updatedTime = updatedTime;
    }
}
