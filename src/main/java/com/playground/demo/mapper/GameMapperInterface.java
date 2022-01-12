package com.playground.demo.mapper;

import com.playground.demo.model.GameDto;
import com.playground.demo.model.WishListGame;
import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.Game;
import com.playground.demo.model.entity.Publisher;
import com.playground.demo.model.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface GameMapperInterface {

    GameMapperInterface INSTANCE = Mappers.getMapper(GameMapperInterface.class);

    @Mapping(target = "gid", source = "game.gid")
    @Mapping(target = "title", source = "game.title", defaultValue = "No Title")
    @Mapping(target = "description", source = "game.description", defaultValue = "No Description")
    @Mapping(target = "price", source = "game.price")
    @Mapping(target = "releaseDate", source = "game.releaseDate")
    @Mapping(target = "discount", source = "game.discount")
    @Mapping(target = "publisher", source = "pub")
    @Mapping(target = "developer", source = "dev")
    @Mapping(target = "tags", source = "tagList")
    Game gameDtoToGame(GameDto game, Publisher pub, Developer dev, List<Tag> tagList);

    @Mapping(target = "gid", source = "gid")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description", defaultValue = "No Description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "releaseDate", source = "releaseDate")
    @Mapping(target = "discount", source = "discount")
    GameDto gameToGameDto(Game game);

    @Mapping(target = ".", source = "publisher")
    Publisher gameToPublisher(Game game);

    @Mapping(target = ".", source = "developer")
    Developer gameToDeveloper(Game game);

//    @Mapping(target = ".", source = "tags")
//    List<Tag> gameToTagList(Game game);

}
