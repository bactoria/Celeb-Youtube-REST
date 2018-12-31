package com.example.celebyoutube.channelLog;

import org.junit.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ChannelLogTest {

    private ChannelLog channelLog;

    @Test
    public void channelLog_도메인_생성하고_불러오기() throws NoSuchFieldException, IllegalAccessException {

        final String ID = "UCCljTtZh6Rt1uxFH_NRkIEw";
        final LocalDate DATE = LocalDate.of(2018, 12, 31);
        final long HOUR = 13L;
        final long SUBSCRIBER = 100L;

        ChannelLogPk channelLogPk = new ChannelLogPk();
        channelLog = new ChannelLog();

        Field field1 = ChannelLogPk.class.getDeclaredField("id");
        field1.setAccessible(true);
        field1.set(channelLogPk, ID);

        Field field2 = ChannelLogPk.class.getDeclaredField("date");
        field2.setAccessible(true);
        field2.set(channelLogPk, DATE);

        Field field3 = ChannelLogPk.class.getDeclaredField("hour");
        field3.setAccessible(true);
        field3.set(channelLogPk, HOUR);

        Field field4 = ChannelLog.class.getDeclaredField("channelLogPk");
        field4.setAccessible(true);
        field4.set(channelLog, channelLogPk);

        Field field = ChannelLog.class.getDeclaredField("subscriber");
        field.setAccessible(true);
        field.set(channelLog, SUBSCRIBER);


        assertThat(channelLog.getChannelLogPk().getId()).isEqualTo(ID);
        assertThat(channelLog.getChannelLogPk().getDate()).isEqualTo(DATE);
        assertThat(channelLog.getChannelLogPk().getHour()).isEqualTo(HOUR);
        assertThat(channelLog.getSubscriber()).isEqualTo(SUBSCRIBER);

    }
}