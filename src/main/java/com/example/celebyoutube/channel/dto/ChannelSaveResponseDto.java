package com.example.celebyoutube.channel.dto;

import com.example.celebyoutube.channel.Channel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ChannelSaveResponseDto {

    private String id;
    private String name;
    private String introVideoUrl;

    public ChannelSaveResponseDto(Channel channel) {
        this.id = channel.getId();
        this.name = channel.getName();
        this.introVideoUrl = channel.getIntroVideoUrl();
    }
}
