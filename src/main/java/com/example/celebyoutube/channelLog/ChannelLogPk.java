package com.example.celebyoutube.channelLog;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChannelLogPk implements Serializable {

    @Column
    private String id;

    @Column
    private LocalDate date;

    @Column
    private Long hour;

}
