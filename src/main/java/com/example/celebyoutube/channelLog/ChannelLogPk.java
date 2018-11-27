package com.example.celebyoutube.channelLog;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
public class ChannelLogPk implements Serializable {

    @Column
    private String id;

    @Column
    private LocalDate date;

    @Column
    private Long hour;

}
