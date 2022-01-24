package com.playground.demo.mapper;

import com.playground.demo.model.GameDto;
import com.playground.demo.model.entity.*;
import com.playground.demo.model.request.GameDtoRequest;
import com.playground.demo.model.request.GameRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import springfox.documentation.service.Tags;

import java.util.List;

@Mapper
public interface GameRequestMapperInterface {

    GameRequestMapperInterface INSTANCE = Mappers.getMapper(GameRequestMapperInterface.class);

    @Mapping(target = "gid", ignore = true)
    @Mapping(target = "title", source = "gameRequest.title", defaultValue = "No Title")
    @Mapping(target = "description", source = "gameRequest.description", defaultValue = "No Description")
    @Mapping(target = "price", source = "gameRequest.price")
    @Mapping(target = "releaseDate", source = "gameRequest.releaseDate")
    @Mapping(target = "discount", source = "gameRequest.discount")
    @Mapping(target = "publisher", source = "pub")
    @Mapping(target = "developer", source = "dev")
    @Mapping(target = "tags", ignore = true)
    Game makeGameFrom(GameRequest gameRequest, Publisher pub, Developer dev);

    void updateGameFrom(@MappingTarget Game game, GameRequest gameRequest, Publisher
            publisher, Developer developer, List<GameTag> tags);

}
