package com.example.celebyoutube.channel;

import com.example.celebyoutube.channel.dto.ChannelSaveRequestDto;
import com.example.celebyoutube.channel.dto.ChannelSaveResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    @Test
    public void 한국어로_크롤링된_JoinedDate의_타입을_LocalDate형으로_변환한다() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String date = "Joined Dec 13, 2018";
        LocalDate localDate = LocalDate.of(2018, 12, 13);

        //call private Method ( using reflection.)
        Method method = channelService.getClass().getDeclaredMethod("toLocalDate", String.class);
        method.setAccessible(true);
        LocalDate result = (LocalDate) method.invoke(channelService, date);

        assertThat(result).isEqualTo(localDate);
    }

    @Test
    public void 영어로_크롤링된_JoinedDate의_타입을_LocalDate형으로_변환한다() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String date = "가입일: 2018. 12. 13.";
        LocalDate localDate = LocalDate.of(2018, 12, 13);

        //call private Method ( using reflection.)
        Method method = channelService.getClass().getDeclaredMethod("toLocalDate", String.class);
        method.setAccessible(true);
        LocalDate result = (LocalDate) method.invoke(channelService, date);

        assertThat(result).isEqualTo(localDate);
    }

    @Test
    public void channel을_정상적으로_크롤링한다() throws IOException {
        //given
        final String ID = "UCCljTtZh6Rt1uxFH_NRkIEw";

        final String TITLE = "테스트유튜튜";
        final String CONTENT = "백엔드 테스트용 채널입니다.";
        final String IMAGE = "https://yt3.ggpht.com/a-/AAuE7mDiqJn0nb4wJs3FhHIKo6zW549jcGzapzmzkg=s900-mo-c-c0xffffffff-rj-k-no";
        final LocalDate JOIN_DATE = LocalDate.of(2018, 12, 13);

        //when
        this.channelService.updateChannel(ID);

        //then
        verify(channelRepositoryMock).updateChannel(eq(ID), eq(TITLE), eq(CONTENT), eq(IMAGE), anyLong(), eq(JOIN_DATE), anyLong(), any());
    }

    @Test
    public void channel을_정상적으로_추가한다() throws IOException {
        //given
        final String ID = "UCCljTtZh6Rt1uxFH_NRkIEw";
        final String NAME = "NAME";
        final String INTRO_VIDEO_URL = "INTRO_VIDEO_URL";

        Channel channel = ChannelSaveRequestDto.builder().id(ID).name(NAME)
                .introVideoUrl(INTRO_VIDEO_URL).build()
                .toEntity();

        given(channelRepositoryMock.save(any(Channel.class))).willReturn(channel);

        ChannelSaveRequestDto requestDto = ChannelSaveRequestDto.builder()
                .id(ID)
                .name(NAME)
                .introVideoUrl(INTRO_VIDEO_URL)
                .build();

        //when
        ChannelSaveResponseDto result = this.channelService.saveChannel(requestDto);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(ID);
        assertThat(result.getName()).isEqualTo(NAME);
        assertThat(result.getIntroVideoUrl()).isEqualTo(INTRO_VIDEO_URL);
    }

    @Test
    public void channel을_추가할때_관련정보를_크롤링하여_갱신한다() throws IOException {
        //given
        final String ID = "UCCljTtZh6Rt1uxFH_NRkIEw";
        final String NAME = "NAME";
        final String INTRO_VIDEO_URL = "INTRO_VIDEO_URL";

        Channel channel = ChannelSaveRequestDto.builder().id(ID).name(NAME)
                .introVideoUrl(INTRO_VIDEO_URL).build()
                .toEntity();

        given(channelRepositoryMock.save(any(Channel.class))).willReturn(channel);

        ChannelSaveRequestDto requestDto = ChannelSaveRequestDto.builder()
                .id(ID)
                .name(NAME)
                .introVideoUrl(INTRO_VIDEO_URL)
                .build();

        //when
        this.channelService.saveChannel(requestDto);

        //then
        verify(channelRepositoryMock).updateChannel(eq(ID), any(), any(), any(), any(), any(), any(), any());
    }

}