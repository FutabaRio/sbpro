package com.example.sbpro.controller;


import com.example.sbpro.controller.request.game.GameInfoDTO;
import com.example.sbpro.controller.response.Result;
import com.example.sbpro.service.game.GameService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Data
@Slf4j
public class GameController {
    private final GameService gameService;

    @PostMapping("/gameInfo")
    private Result<Void> gameInfo(@RequestBody GameInfoDTO gameInfoDTO) {
        gameService.uploadGameRecord(gameInfoDTO);
        return Result.success();
    }
}
