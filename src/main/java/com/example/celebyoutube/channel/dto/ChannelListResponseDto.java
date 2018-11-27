package com.example.celebyoutube.channel.dto;

import com.example.celebyoutube.channel.Channel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChannelListResponseDto {

    private String id;
    private String name;
    private String title;
    private String image;
    private String subscriber_string;

    public static ChannelListResponseDto toEntity(Channel channel) {
        return ChannelListResponseDto.builder()
                .id(channel.getId())
                .name(channel.getName())
                .title(channel.getTitle())
                .image(channel.getImage())
                .subscriber_string(subscriber_toString(channel.getSubscriber()))
                .build();
    }

    private static String subscriber_toString(Long subscriber) {

        String sub = String.valueOf(subscriber);
        int a = sub.length();
        String result;

        switch (a) {
            case 1:
                result = sub;
                break;
            case 2:
                result = sub;
                break;
            case 3:
                result = sub;
                break;
            case 4:
                result = sub.substring(0, 1) + '.' + sub.substring(1, 2) + '천';
                break;
            case 5:
                result = sub.substring(0, 1) + '.' + sub.substring(1, 2) + '만';
                break;
            case 6:
                result = sub.substring(0, 2) + '만';
                break;
            case 7:
                result = sub.substring(0, 3) + '만';
                break;
            case 8:
                result = sub.substring(0, 4) + '만';
                break;
            case 9:
                result = sub.substring(0, 1) + '.' + sub.substring(1, 2) + '억';
                break;
            default:
                result = sub;
                break;
        }
        return result;
    }

    @Builder
    private ChannelListResponseDto(String id, String name, String title, String image, String subscriber_string) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.image = image;
        this.subscriber_string = subscriber_string;
    }
}