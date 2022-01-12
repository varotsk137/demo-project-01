package com.playground.demo.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.playground.demo.model.GameDto;
import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.Game;
import com.playground.demo.model.entity.Publisher;
import com.playground.demo.model.entity.Tag;
import com.playground.demo.util.ReadInputUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMapperInterfaceTest extends ReadInputUtil {

    @InjectMocks
    private GameMapperInterfaceTest gameMapperInterfaceTest;


    private GameDto gameDto;
    private Developer developer;
    private Publisher publisher;
    private List<Tag> tagList;
    private Game game;

    @BeforeEach
    public void setup() throws IOException {

        this.gameDto = super.loadClassFromJson(GameDto.class, "/json/gameDto.json");
        this.developer = super.loadClassFromJson(Developer.class, "/json/developer.json");
        this.publisher = super.loadClassFromJson(Publisher.class, "/json/publisher.json");
        this.tagList = super.loadClassListFromJson(new TypeReference<List<Tag>>() {}, "/json/tagList.json");
        this.game = super.loadClassFromJson(Game.class, "/json/gameTest.json");

    }

    @Test
    void gameDtoToGame() {

        Game actualGame = GameMapperInterface.INSTANCE.gameDtoToGame(this.gameDto, this.publisher, this.developer, this.tagList);

        Assertions.assertThat(this.game).isEqualToComparingFieldByField(actualGame);

    }

    @Test
    void gameToGameDto() {

        GameDto actualGameDto = GameMapperInterface.INSTANCE.gameToGameDto(this.game);

        Assertions.assertThat(this.gameDto).isEqualToComparingFieldByField(actualGameDto);

    }

    @Test
    void gameToPublisher() {

        Publisher actualPublisher = GameMapperInterface.INSTANCE.gameToPublisher(this.game);

        Assertions.assertThat(this.publisher).isEqualToComparingFieldByField(actualPublisher);

    }

    @Test
    void gameToDeveloper() {

        Developer actualDeveloper = GameMapperInterface.INSTANCE.gameToDeveloper(this.game);

        Assertions.assertThat(this.developer).isEqualToComparingFieldByField(actualDeveloper);

    }
}