package com.example.celebyoutube.channelLog;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
public class ChannelLogRestController {

    private final ChannelLogService channelLogService;

    @GetMapping("/channelLog/{id}/hour")
    public List<ChannelLog> getChannelLog_Hour(@PathVariable String id) {
        log.info("Get :: channelLog/" + id + "/hour");
        return channelLogService.getChannelLog_Hour(id);
    }

    @GetMapping("/channelLog/{id}/day")
    public List<ChannelLog> getChannelLog_Day(@PathVariable String id) {
        log.info("Get :: channelLog/" + id + "/day");
        return channelLogService.getChannelLog_Day(id);
    }

}
