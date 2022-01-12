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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMapperInterfaceTest {

    @InjectMocks
    private GameMapperInterfaceTest gameMapperInterfaceTest;

    private ObjectMapper objectMapper;
    private GameDto gameDto;
    private Developer developer;
    private Publisher publisher;
    private List<Tag> tagList;
    private Game game;

    @BeforeEach
    public void setup() throws IOException {
        objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();

        this.gameDto = objectMapper.readValue(new File("src/test/resources/json/gameDto.json"), GameDto.class);
        this.developer = objectMapper.readValue(new File("src/test/resources/json/developer.json"), Developer.class);
        this.publisher = objectMapper.readValue(new File("src/test/resources/json/publisher.json"), Publisher.class);
        this.tagList = objectMapper.readValue(new File("src/test/resources/json/tagList.json"), new TypeReference<List<Tag>>() {});
        this.game = objectMapper.readValue(new File("src/test/resources/json/gameTest.json"), Game.class);
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