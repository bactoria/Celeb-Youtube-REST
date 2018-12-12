package com.example.celebyoutube.channel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ChannelRepositoryTest {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void 특정한_채널을_정상적으로_불러온다() {
        //given
        final String ID = "qqqqqqqqq";
        Channel channel = Channel.builder().id(ID).name("연예인").build();
        testEntityManager.persist(channel);

        //when
        Optional<Channel> result = channelRepository.findById(ID);

        //then
        assertThat(result).isNotNull();
        assertThat(result.orElseThrow(RuntimeException::new)).isEqualTo(channel);
    }

    @Test
    public void 특정한_채널의_title_content_image_subscriber_joinDate_views_updatedTime을_정상적으로_갱신한다() {
        //given
        final String ID = "qqqqqqqqq";

        final String NEW_TITLE = "NEW_TITLE";
        final String NEW_CONTENT = "NEW_CONTENT";
        final String NEW_IMAGE = "NEW_IMAGE";
        final Long NEW_SUBSCRIBER = 123L;
        final LocalDate NEW_JOINDATE = LocalDate.now();
        final Long NEW_VIEWS = 456L;
        final LocalDateTime NEW_UPDATEDTIME = LocalDateTime.now();

        Channel channel = Channel.builder().id(ID).build();
        testEntityManager.persist(channel);

        //when
        channelRepository.updateChannel(ID, NEW_TITLE, NEW_CONTENT, NEW_IMAGE, NEW_SUBSCRIBER, NEW_JOINDATE, NEW_VIEWS, NEW_UPDATEDTIME);
        Channel result = channelRepository.findById(ID).orElseThrow(RuntimeException::new);

        //then
        assertThat(result.getTitle()).isEqualTo(NEW_TITLE);
        assertThat(result.getContent()).isEqualTo(NEW_CONTENT);
        assertThat(result.getImage()).isEqualTo(NEW_IMAGE);
        assertThat(result.getSubscriber()).isEqualTo(NEW_SUBSCRIBER);
        assertThat(result.getJoinDate()).isEqualTo(NEW_JOINDATE);
        assertThat(result.getViews()).isEqualTo(NEW_VIEWS);
        assertThat(result.getUpdatedTime()).isEqualTo(NEW_UPDATEDTIME);
    }

}