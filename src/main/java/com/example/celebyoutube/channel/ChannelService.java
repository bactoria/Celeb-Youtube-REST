package com.example.celebyoutube.channel;

import com.example.celebyoutube.channel.dto.ChannelListResponseDto;
import com.example.celebyoutube.channel.dto.ChannelSaveRequestDto;
import com.example.celebyoutube.channel.dto.ChannelSaveResponseDto;
import com.example.celebyoutube.exceptions.ChannelNotFoundException;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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
        String connUrl = "https://www.youtube.com/channel/" + id + "/about";
        System.out.println("1");
        Document doc = Jsoup.connect(connUrl).get();

        System.out.println("2");
        Elements elements = doc.select("meta[property]");
        String title = elements.select("meta[property=og:title]").attr("content");
        System.out.println("3");
        String content = elements.select("meta[property=og:description]").attr("content");
        System.out.println("4");
        String image = elements.select("meta[property=og:image]").attr("content");

        System.out.println("5");
        Long subscriber = Long.valueOf(doc.select("span.subscribed").text().replace(",", ""));
        System.out.println("6");

        List<String> elementList = doc.select("span.about-stat").eachText();
        System.out.println("7");
        Long views = Long.valueOf(elementList.get(1).replaceAll("[^0-9]", ""));
        System.out.println("8");
        String[] joinDateArray = elementList.get(2).replaceAll("[^0-9.]", "").split("\\.");
        System.out.println("9");
        LocalDate joinDate = LocalDate.of(Integer.valueOf(joinDateArray[0]), Integer.valueOf(joinDateArray[1]), Integer.valueOf(joinDateArray[2]));
        System.out.println("10");

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
