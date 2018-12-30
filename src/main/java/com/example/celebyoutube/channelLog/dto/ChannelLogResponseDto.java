package com.example.celebyoutube.channelLog.dto;

import com.example.celebyoutube.channelLog.ChannelLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
public class ChannelLogResponseDto {

    private String id;
    private LocalDate date;
    private Long hour;
    private Long subscriber;

    public ChannelLogResponseDto(ChannelLog channelLog) {
        this.id = channelLog.getChannelLogPk().getId();
        this.date = channelLog.getChannelLogPk().getDate();
        this.hour = channelLog.getChannelLogPk().getHour();
        this.subscriber = channelLog.getSubscriber();
    }
}
