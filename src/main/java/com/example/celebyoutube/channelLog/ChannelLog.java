package com.example.celebyoutube.channelLog;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChannelLog {

    @EmbeddedId
    private ChannelLogPk channelLogPk;

    @Column
    private Long subscriber;

}
