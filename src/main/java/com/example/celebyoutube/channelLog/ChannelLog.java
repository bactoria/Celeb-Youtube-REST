package com.example.celebyoutube.channelLog;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChannelLog {

    @EmbeddedId
    private ChannelLogPk ChannelLogPk;

    @Column
    private Long subscriber;

}
