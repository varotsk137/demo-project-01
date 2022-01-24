package com.playground.demo.controller;

import com.playground.demo.model.GameDto;
import com.playground.demo.model.WishListGame;
import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.Game;
import com.playground.demo.model.entity.Publisher;
import com.playground.demo.model.entity.Tag;
import com.playground.demo.model.request.GameDtoRequest;
import com.playground.demo.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class GameRestController {

    @Autowired
    private GameService gameService;

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/game/{gameId}")
    public Game getGameFromGameId(@PathVariable(name = "gameId") Integer gameId){
        return gameService.getGameFromGameId(gameId);
    }

    @PostMapping("/game")
    public Game addGame(@Validated @RequestBody GameDtoRequest game) {
        return gameService.addNewGame(game);
    }

    @PutMapping("/game/{gameId}")
    public Game editGame(@Validated @RequestBody GameDtoRequest game, @PathVariable(name = "gameId") Integer gameId) {
        return gameService.editGameWithGameId(gameId, game);
    }

}
