package com.example.celebyoutube.channelLog;

import com.example.celebyoutube.channelLog.dto.ChannelLogResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/channelLog/{id}")
public class ChannelLogRestController {

    private final ChannelLogService channelLogService;

    @GetMapping("/hour")
    public List<ChannelLogResponseDto> getChannelLog_Hour(@PathVariable String id) {
        log.info("Get :: channelLog/" + id + "/hour");
        return channelLogService.getChannelLog_Hour(id);
    }

    @GetMapping("/day")
    public List<ChannelLogResponseDto> getChannelLog_Day(@PathVariable String id) {
        log.info("Get :: channelLog/" + id + "/day");
        return channelLogService.getChannelLog_Day(id);
    }

    @GetMapping("/week")
    public List<ChannelLogResponseDto> getChannelLog_Week(@PathVariable String id) {
        log.info("Get :: channelLog/" + id + "/week");
        return channelLogService.getChannelLog_Week(id);
    }

}
