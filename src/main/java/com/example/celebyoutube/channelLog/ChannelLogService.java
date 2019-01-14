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

    final long LOG_COUNT = 7;

    public List<ChannelLogResponseDto> getChannelLog_Hour(String id) {
        return toChannelLogResponseDtoList(channelLogRepository.findHourUnit(id, LOG_COUNT));
    }

    public List<ChannelLogResponseDto> getChannelLog_Day(String id) {
        final Long HOUR = 0L;
        return toChannelLogResponseDtoList(channelLogRepository.findDayUnit(id, HOUR, LOG_COUNT));
    }

    public List<ChannelLogResponseDto> getChannelLog_Week(String id) {
        final Long HOUR = 0L;
        final Long DAY_OF_THE_WEEK = 7L; //일요일
        return toChannelLogResponseDtoList(channelLogRepository.findWeekUnit(id, HOUR, DAY_OF_THE_WEEK, LOG_COUNT));
    }

    private List<ChannelLogResponseDto> toChannelLogResponseDtoList(List<ChannelLog> list) {
        return list.stream()
                .map(ChannelLogResponseDto::new)
                .collect(Collectors.toList());
    }
}
