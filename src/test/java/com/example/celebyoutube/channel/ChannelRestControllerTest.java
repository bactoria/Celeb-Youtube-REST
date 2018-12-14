package com.example.celebyoutube.channel;


import com.example.celebyoutube.channel.dto.ChannelListResponseDto;
import com.example.celebyoutube.channel.dto.ChannelSaveRequestDto;
import com.example.celebyoutube.channel.dto.ChannelSaveResponseDto;
import com.example.celebyoutube.config.SecurityConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({ChannelRestController.class, SecurityConfig.class})
public class ChannelRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChannelService channelServiceMock;

    private String jsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    public void 모든_채널_요청시_모두_반환() throws Exception {
        //given
        ChannelListResponseDto channel1 = ChannelListResponseDto.builder().id("1").build();
        ChannelListResponseDto channel2 = ChannelListResponseDto.builder().id("2").build();
        List<ChannelListResponseDto> channelList = Arrays.asList(channel1, channel2);

        given(channelServiceMock.getChannelList()).willReturn(channelList);

        //when
        mockMvc.perform(get("/channels")
                .contentType(MediaType.APPLICATION_JSON_UTF8))

                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[1].id", is("2")));
    }

    @Test
    public void 특정_채널_요청시_채널_반환() throws Exception {
        //given
        final String ID = "ID";
        Channel channel = Channel.builder().id(ID).build();

        given(channelServiceMock.getChannel(ID)).willReturn(channel);

        //when
        mockMvc.perform(get("/channels/" + ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8))

                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID)));
    }

    @Test
    public void 인증하지않은_사용자가_특정_채널_삭제시_401Unauthorized() throws Exception {
        //given
        final String ID = "ID";

        //when
        mockMvc.perform(delete("/channels/" + ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8))

                //then
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


    @Test
    @WithMockUser
    public void 인증한_사용자가_특정_채널_삭제시_200Ok() throws Exception {
        //given
        final String ID = "ID";

        //when
        mockMvc.perform(delete("/channels/" + ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8))

                //then
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 인증하지않은_사용자가_특정_채널_추가시_401Unauthorized() throws Exception {
        //given
        final String ID = "ID";

        //when
        mockMvc.perform(post("/channels")
                .contentType(MediaType.APPLICATION_JSON_UTF8))

                //then
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void 인증한_사용자가_특정_채널_추가시_정상적으로_추가() throws Exception {
        //given
        final String ID = "ID";
        final String NAME = "NAME";
        final String INTRO_VIDEO_URL = "INTRO_VIDEO_URL";
        ChannelSaveRequestDto channelSaveRequestDto = ChannelSaveRequestDto.builder().id(ID).name(NAME).introVideoUrl(INTRO_VIDEO_URL).build();

        ChannelSaveResponseDto channelSaveResponseDto = new ChannelSaveResponseDto(channelSaveRequestDto.toEntity());

        given(channelServiceMock.saveChannel(any(ChannelSaveRequestDto.class))).willReturn(channelSaveResponseDto);

        //when
        mockMvc.perform(post("/channels")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(channelSaveRequestDto)))

                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID)));
    }

}