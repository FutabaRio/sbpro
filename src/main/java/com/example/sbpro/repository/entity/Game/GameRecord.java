package com.example.sbpro.repository.entity.Game;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "game_record")
@Data
public class GameRecord {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String recordId;
    private String userId;
    private String nickName;
    private Integer completeDuration;
    private String gameName;
    private String gameCode;
    private String venueId;
    private String spaceId;
    private int serial;
    private LocalDateTime finishTime;
    private Float score;
    private LocalDateTime beginTime;
    private String gameRecord;
    private LocalDateTime endTime;
    private Integer duration;
    private Integer playerDuration;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Integer isDeleted;
    private String isCompensate;
    private Integer ranking;
    private LocalDateTime leaveTime;
}
