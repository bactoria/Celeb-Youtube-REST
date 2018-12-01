package com.example.celebyoutube.channelLog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelLogRepository extends JpaRepository<ChannelLog, ChannelLogPk> {
    List<ChannelLog> findTop7ByChannelLogPk_Id(String id);

    List<ChannelLog> findTop7ByChannelLogPk_IdAndChannelLogPk_Hour(String id, Long hour);
}

