package com.playground.demo.service;

import com.playground.demo.mapper.GameMapperInterface;
import com.playground.demo.mapper.WishListGameMapperInterface;
import com.playground.demo.model.GameDto;
import com.playground.demo.model.WishListGame;
import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.Game;
import com.playground.demo.model.entity.Publisher;
import com.playground.demo.model.entity.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Slf4j
public class GameMapperService {

    public WishListGame wishListGameMapping(Game game){
        return WishListGameMapperInterface.INSTANCE.gameToWishlistGame( game );
    }

    public Game addGameTesting() {

        GameDto gameDto = GameDto.builder()
                .description("test")
                .gid(123)
                .discount(5)
                .price(BigDecimal.valueOf(1023.4))
                .releaseDate(ZonedDateTime.now())
                .title("ABCDE")
                .build();

        Publisher publisher = Publisher.builder()
                .pubId(1321)
                .name("WOW")
                .build();

        Developer developer = Developer.builder()
                .devId(3310)
                .name("ASDASDASD")
                .build();

        Tag tag1 = Tag.builder().tagId(1).tagName("Free to Play").build();
        Tag tag2 = Tag.builder().tagId(2).tagName("Battle Royale").build();
        Tag tag3 = Tag.builder().tagId(3).tagName("Shooter").build();
        Tag tag4 = Tag.builder().tagId(4).tagName("Multiplayer").build();
        List<Tag> tagList = List.of(tag1, tag2, tag3, tag4);

        return GameMapperInterface.INSTANCE.gameDtoToGame(gameDto, publisher, developer, tagList);
    }

    public Developer gameToDeveloper( Game game ) {
        return GameMapperInterface.INSTANCE.gameToDeveloper( game );
    }

    public Publisher gameToPublisher( Game game ) {
        return GameMapperInterface.INSTANCE.gameToPublisher( game );
    }

    public GameDto gameToGameDto( Game game ) {
        return GameMapperInterface.INSTANCE.gameToGameDto( game );
    }

}
