package com.example.celebyoutube.channel.dto;

import com.example.celebyoutube.channel.Channel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
public class ChannelSaveDto {

    private String id;
    private String name;
    private String introVideoUrl;
    private String title;
    private String content;
    private String image;
    private Long subscriber;
    private String subscriber_summary;
    private LocalDate joinDate;
    private Long views;

    @Builder
    public ChannelSaveDto(String id, String name, String introVideoUrl, String title, String content, String image, Long subscriber, String subscriber_summary, LocalDate joinDate, Long views) {
        this.id = id;
        this.name = name;
        this.introVideoUrl = introVideoUrl;
        this.title = title;
        this.content = content;
        this.image = image;
        this.subscriber = subscriber;
        this.subscriber_summary = subscriber_summary;
        this.joinDate = joinDate;
        this.views = views;
    }

    public Channel toEntity() {
        return Channel.builder()
                .id(this.id)
                .name(this.name)
                .introVideoUrl(this.introVideoUrl)
                .title(this.title)
                .content(this.content)
                .image(this.image)
                .subscriber(this.subscriber)
                .joinDate(joinDate)
                .views(views)
                .build();
    }
}
