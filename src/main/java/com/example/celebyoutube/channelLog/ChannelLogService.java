package com.example.celebyoutube.channelLog;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChannelLogService {

    private final ChannelLogRepository channelLogRepository;

    public List<ChannelLog> getChannelLog_Hour(String id) {
        final long LOG_COUNT = 7;
        return channelLogRepository.findHourUnit(id, LOG_COUNT);
    }

    public List<ChannelLog> getChannelLog_Day(String id) {
        final Long HOUR = 0L;
        final long LOG_COUNT = 7;
        return channelLogRepository.findDayUnit(id, HOUR, LOG_COUNT);
    }
}
