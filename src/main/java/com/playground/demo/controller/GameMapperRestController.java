package com.playground.demo.controller;

import com.playground.demo.model.GameDto;
import com.playground.demo.model.WishListGame;
import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.Game;
import com.playground.demo.model.entity.Publisher;
import com.playground.demo.model.entity.Tag;
import com.playground.demo.service.GameMapperService;
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
public class GameMapperRestController {

    @Autowired
    GameMapperService gameMapperService;

    @PostMapping(value = "/game2wl")
    public WishListGame wishListGameMapping(@Validated @RequestBody Game game){
        return gameMapperService.wishListGameMapping(game);
    }

    @PostMapping(value = "/gameDto2game")
    public Game addGameTesting(){
        return gameMapperService.addGameTesting();
    }

    @PostMapping(value = "/game2dev")
    public Developer gameToDev(@Validated @RequestBody Game game ){
        return gameMapperService.gameToDeveloper(game);
    }

    @PostMapping(value = "/game2gameDto")
    public GameDto gameToGameDto(@Validated @RequestBody Game game ){
        return gameMapperService.gameToGameDto(game);
    }

    @PostMapping(value = "/game2pub")
    public Publisher gameToPublisher(@Validated @RequestBody Game game ){
        return gameMapperService.gameToPublisher(game);
    }

}
