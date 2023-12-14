package com.example.sbpro.service.game;

import com.example.sbpro.controller.request.game.GameInfoDTO;
import com.example.sbpro.controller.request.game.UserInfo;
import com.example.sbpro.repository.GameRepository;
import com.example.sbpro.repository.entity.Game.GameRecord;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public void uploadGameRecord(GameInfoDTO gameInfoDTO) {

        List<UserInfo> userInfos = gameInfoDTO.getUserInfos();
        for (UserInfo userinfo : userInfos) {
            GameRecord gameRecord = new GameRecord();
//            fixme: didnt set beginTime
            gameRecord.setGameCode(gameInfoDTO.getGameCode());
            gameRecord.setRecordId(gameInfoDTO.getRecordId());
            gameRecord.setUserId(userinfo.getUserId());
            gameRecord.setSerial(userinfo.getSerial());
            gameRecord.setSpaceId(gameInfoDTO.getSpaceId());
            gameRecord.setVenueId(gameInfoDTO.getVenueId());
            gameRepository.save(gameRecord);
        }
    }
}
