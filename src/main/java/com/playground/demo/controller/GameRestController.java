package com.playground.demo.controller;

import com.playground.demo.model.GameDto;
import com.playground.demo.model.WishListGame;
import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.Game;
import com.playground.demo.model.entity.Publisher;
import com.playground.demo.model.entity.Tag;
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
    GameService gameService;

    @PostMapping(value = "/game2wl")
    public WishListGame wishListGameMapping(@Validated @RequestBody Game game){
        return gameService.wishListGameMapping(game);
    }

    @PostMapping(value = "/gameDto2game")
    public Game addGameTesting(){
        return gameService.addGameTesting();
    }

    @PostMapping(value = "/game2dev")
    public Developer gameToDev(@Validated @RequestBody Game game ){
        return gameService.gameToDeveloper(game);
    }

    @PostMapping(value = "/game2gameDto")
    public GameDto gameToGameDto(@Validated @RequestBody Game game ){
        return gameService.gameToGameDto(game);
    }

    @PostMapping(value = "/game2pub")
    public Publisher gameToPublisher(@Validated @RequestBody Game game ){
        return gameService.gameToPublisher(game);
    }

    @GetMapping(value = "/demo")
    public Game getDemoGame(){
        Developer developer = Developer.builder()
                .devId(1)
                .name("Respawn Entertainment")
                .build();

        Publisher publisher = Publisher.builder()
                .pubId(1)
                .name("Electronic Arts")
                .build();

        Tag tag1 = Tag.builder().tagId(1).tagName("Free to Play").build();
        Tag tag2 = Tag.builder().tagId(1).tagName("Battle Royale").build();
        Tag tag3 = Tag.builder().tagId(1).tagName("Shooter").build();
        Tag tag4 = Tag.builder().tagId(1).tagName("Multiplayer").build();
        List<Tag> tagList = List.of(tag1, tag2, tag3, tag4);

        Game game = Game.builder()
                .gid(1)
                .title("Apex Legend")
                .description("award-winning free-to-play Hero Shooter")
                .price(BigDecimal.valueOf(0))
                .developer(developer)
                .publisher(publisher)
                .releaseDate(ZonedDateTime.of(2020, 11, 5, 0, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .tags(tagList)
                .build();

        return game;
    }

}
