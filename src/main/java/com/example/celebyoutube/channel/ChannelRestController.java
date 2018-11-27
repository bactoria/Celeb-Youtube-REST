package com.example.celebyoutube.channel;

import com.example.celebyoutube.channel.dto.ChannelListResponseDto;
import com.example.celebyoutube.channel.dto.ChannelSaveRequestDto;
import com.example.celebyoutube.channel.dto.ChannelSaveResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/channels")
public class ChannelRestController {

    private final ChannelService channelService;

    @GetMapping
    public List<ChannelListResponseDto> getChannelList() {
        log.info("Get :: channels");
        return this.channelService.getChannelList();
    }

    @GetMapping("/{id}")
    public Channel getChannel(@PathVariable String id) {
        log.info("Get :: channels/" + id);
        return this.channelService.getChannel(id);
    }

    @PostMapping
    public ChannelSaveResponseDto saveChannel(@RequestBody ChannelSaveRequestDto dto) throws IOException {
        log.info("Post :: channels");
        return this.channelService.saveChannel(dto);
    }

    @PutMapping("/{id}")
    public void updateChannel(@PathVariable String id) throws IOException {
        log.info("Put :: channels/" + id);
        this.channelService.updateChannel(id);
    }

    @DeleteMapping("/{id}")
    public void deleteChannel(@PathVariable String id) {
        log.info("Delete :: channels/" + id);
        this.channelService.deleteChannel(id);
    }

}
