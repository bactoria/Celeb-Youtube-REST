package com.example.celebyoutube.channel;

import com.example.celebyoutube.channel.dto.ChannelSaveRequestDto;
import com.example.celebyoutube.channel.dto.ChannelSaveResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ChannelServiceTest {
    private ChannelRepository channelRepositoryMock;
    private ChannelService channelService;

    @Before
    public void setup() {
        this.channelRepositoryMock = Mockito.mock(ChannelRepository.class);
        this.channelService = new ChannelService(channelRepositoryMock);
    }

    @Test
    public void Mocking이_되었다() {
        assertThat(channelRepositoryMock).isNotNull();
        assertThat(channelService).isNotNull();
    }
    
}