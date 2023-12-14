package com.example.sbpro.controller.request.game;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class GameInfoDTO {
    LocalDateTime beginTime;
    String GameCode;
    String RecordId;
    String SpaceId;
    String VenueId;
    List<UserInfo> UserInfos;
}
