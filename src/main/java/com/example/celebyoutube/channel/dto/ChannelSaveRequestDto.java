package com.example.celebyoutube.channel.dto;

import com.example.celebyoutube.channel.Channel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ChannelSaveRequestDto {

    private String id;
    private String name;
    private String introVideoUrl;

    @Builder
    public ChannelSaveRequestDto(String id, String name, String introVideoUrl) {
        this.id = id;
        this.name = name;
        this.introVideoUrl = introVideoUrl;
    }

    public Channel toEntity() {
        return Channel.builder()
                .id(this.id)
                .name(this.name)
                .introVideoUrl(this.introVideoUrl)
                .build();
    }

}
