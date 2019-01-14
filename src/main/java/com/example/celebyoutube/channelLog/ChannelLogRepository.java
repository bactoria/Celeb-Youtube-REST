package com.example.celebyoutube.channelLog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelLogRepository extends JpaRepository<ChannelLog, String> {

    @Query (value = "select * from channel_log where channel_log.id=:id order by date desc, hour desc LIMIT :LOG_COUNT", nativeQuery = true)
    List<ChannelLog> findHourUnit(@Param("id") String id, @Param("LOG_COUNT") Long LOG_COUNT);

    @Query (value = "select * from channel_log where channel_log.id=:id and channel_log.hour=:HOUR order by date desc, hour desc LIMIT :LOG_COUNT", nativeQuery = true)
    List<ChannelLog> findDayUnit(@Param("id") String id, @Param("HOUR") Long HOUR, @Param("LOG_COUNT") Long LOG_COUNT);

    @Query (value = "select * from channel_log where channel_log.id=:id and channel_log.hour=:HOUR and extract(isodow from channel_log.date)=:DAY_OF_THE_WEEK order by date desc, hour desc LIMIT :LOG_COUNT", nativeQuery = true)
    List<ChannelLog> findWeekUnit(@Param("id") String id, @Param("HOUR") Long HOUR,@Param("DAY_OF_THE_WEEK") Long DAY_OF_THE_WEEK, @Param("LOG_COUNT") Long LOG_COUNT);

}

