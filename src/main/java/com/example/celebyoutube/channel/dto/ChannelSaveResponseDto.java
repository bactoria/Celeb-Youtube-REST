package com.example.celebyoutube.channel.dto;

import com.example.celebyoutube.channel.Channel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime updatedTime;

    public ChannelSaveResponseDto(Channel channel) {
        this.id = channel.getId();
        this.name = channel.getName();
        this.introVideoUrl = channel.getIntroVideoUrl();
        this.title = channel.getTitle();
        this.content = channel.getContent();
        this.image = channel.getImage();
        this.subscriber = channel.getSubscriber();
        this.joinDate = channel.getJoinDate();
        this.views = channel.getViews();
        this.updatedTime = channel.getUpdatedTime();
    }
}
