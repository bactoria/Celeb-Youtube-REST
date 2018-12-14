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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    public void updateChannel(String id) throws IOException {
        //TODO :: 채널 크롤링
        String connUrl = "https://www.youtube.com/channel/" + id + "/about";
        Document doc = Jsoup.connect(connUrl).get();

        Elements elements = doc.select("meta[property]");
        String title = elements.select("meta[property=og:title]").attr("content");
        String content = elements.select("meta[property=og:description]").attr("content");
        String image = elements.select("meta[property=og:image]").attr("content");

        Long subscriber = Long.valueOf(doc.select("span.subscribed").text().replace(",", ""));

        List<String> elementList = doc.select("span.about-stat").eachText();
        Long views = Long.valueOf(elementList.get(1).replaceAll("[^0-9]", ""));
        LocalDate joinDate = toLocalDate(elementList.get(2));

        LocalDateTime updatedTime = LocalDateTime.now();

        this.channelRepository.updateChannel(id, title, content, image, subscriber, joinDate, views, updatedTime);
    }

    private LocalDate toLocalDate(String s) {
        final String ENG = "Joined";
        final String KR = "가입일:";

        LocalDate result = null;

        String searchCase = s.split(" ")[0];

        switch (searchCase) {
            case ENG:
                String date = s.substring(7);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
                result = LocalDate.parse(date, formatter);
                break;

            case KR:
                date = s.substring(5);
                formatter = DateTimeFormatter.ofPattern("yyyy. MM. d.", Locale.ENGLISH);
                result = LocalDate.parse(date, formatter);
                break;
        }
        return result;
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
