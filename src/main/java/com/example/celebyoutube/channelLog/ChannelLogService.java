package com.example.celebyoutube.channelLog;

import com.example.celebyoutube.channelLog.dto.ChannelLogResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ChannelLogService {

    private final ChannelLogRepository channelLogRepository;

    public List<ChannelLogResponseDto> getChannelLog_Hour(String id) {
        final long LOG_COUNT = 7;
        return toChannelLogResponseDtoList(channelLogRepository.findHourUnit(id, LOG_COUNT));
    }

    public List<ChannelLogResponseDto> getChannelLog_Day(String id) {
        final Long HOUR = 0L;
        final long LOG_COUNT = 7;
        return toChannelLogResponseDtoList(channelLogRepository.findDayUnit(id, HOUR, LOG_COUNT));
    }

    private List<ChannelLogResponseDto> toChannelLogResponseDtoList(List<ChannelLog> list) {
        return list.stream()
                .map(ChannelLogResponseDto::new)
                .collect(Collectors.toList());
    }
}
