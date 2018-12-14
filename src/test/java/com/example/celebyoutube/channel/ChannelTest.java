package com.example.celebyoutube.channel;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ChannelTest {

    @Test
    public void channel_도메인_생성하고_불러오기() {

        final String ID = "UCCljTtZh6Rt1uxFH_NRkIEw";
        final String NAME = "NAME";
        final String INTRO_VIDEO_URL = "INTRO_VIDEO_URL";
        final String TITLE = "TITLE";
        final String CONTENT = "CONTENT";
        final String IMAGE = "IMAGE";
        final Long SUBSCRIBER = 0L;
        final LocalDate JOIN_DATE = LocalDate.now();
        final Long VIEWS = 1L;
        final LocalDateTime UPDATED_TIME = LocalDateTime.now();

        Channel channel = Channel.builder()
                .id(ID)
                .name(NAME)
                .introVideoUrl(INTRO_VIDEO_URL)
                .title(TITLE)
                .content(CONTENT)
                .image(IMAGE)
                .subscriber(SUBSCRIBER)
                .joinDate(JOIN_DATE)
                .views(VIEWS)
                .updatedTime(UPDATED_TIME)
                .build();

        assertThat(channel.getId()).isEqualTo(ID);
        assertThat(channel.getName()).isEqualTo(NAME);
        assertThat(channel.getIntroVideoUrl()).isEqualTo(INTRO_VIDEO_URL);
        assertThat(channel.getTitle()).isEqualTo(TITLE);
        assertThat(channel.getContent()).isEqualTo(CONTENT);
        assertThat(channel.getImage()).isEqualTo(IMAGE);
        assertThat(channel.getSubscriber()).isEqualTo(SUBSCRIBER);
        assertThat(channel.getJoinDate()).isEqualTo(JOIN_DATE);
        assertThat(channel.getViews()).isEqualTo(VIEWS);
        assertThat(channel.getUpdatedTime()).isEqualTo(UPDATED_TIME);

    }
}