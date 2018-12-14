package com.example.celebyoutube.channel;

import com.example.celebyoutube.channel.dto.ChannelListResponseDto;
import com.example.celebyoutube.channel.dto.ChannelSaveRequestDto;
import com.example.celebyoutube.channel.dto.ChannelSaveResponseDto;
import com.example.celebyoutube.exceptions.ChannelNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    public void updateChannel(String id) throws IOException {
        //TODO :: 채널 크롤링
        String title = "유튜튜 테스트";
        String content = "백엔드 테스트용 채널입니다.";
        String image = "https://yt3.ggpht.com/a-/AN66SAx-2HMivOo50k0DBE7PmMGPLKxG3H4CLuXOCA=s900-mo-c-c0xffffffff-rj-k-no";

        Long subscriber = 0L;

        Long views = 1L;
        LocalDate joinDate = LocalDate.of(2018, 12, 13);

        LocalDateTime updatedTime = LocalDateTime.now();

        this.channelRepository.updateChannel(id, title, content, image, subscriber, joinDate, views, updatedTime);
    }

    public ChannelSaveResponseDto saveChannel(ChannelSaveRequestDto dto) throws IOException {

        Channel requestChannel = dto.toEntity();
        Channel responseChannel = channelRepository.save(requestChannel);

        updateChannel(dto.getId());

        ChannelSaveResponseDto channelSaveResponseDto = new ChannelSaveResponseDto(responseChannel);

        return channelSaveResponseDto;
    }

    public void deleteChannel(String id) {
        channelRepository.deleteById(id);
    }

    public List<ChannelListResponseDto> getChannelList() {
        List<Channel> channelList = channelRepository.findAll(new Sort(Sort.Direction.DESC, "subscriber"));
        return channelList.stream().map(ChannelListResponseDto::toEntity).collect(toList());
    }

    public Channel getChannel(String id) {
        return channelRepository.findById(id).orElseThrow(ChannelNotFoundException::new);
    }
}
