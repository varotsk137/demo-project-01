package com.playground.demo.service;

import com.playground.demo.mapper.GameRequestMapperInterface;
import com.playground.demo.model.entity.*;
import com.playground.demo.model.entity.id.GameTagId;
import com.playground.demo.model.request.DeveloperRequest;
import com.playground.demo.model.request.GameDtoRequest;
import com.playground.demo.model.request.PublisherRequest;
import com.playground.demo.repository.DeveloperJpaRepository;
import com.playground.demo.repository.GameJpaRepository;
import com.playground.demo.repository.PublisherJpaRepository;
import com.playground.demo.repository.TagJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GameService {

    @Autowired
    private GameJpaRepository gameJpaRepository;

    @Autowired
    private PublisherJpaRepository publisherJpaRepository;

    @Autowired
    private DeveloperJpaRepository developerJpaRepository;

    @Autowired
    private TagJpaRepository tagJpaRepository;


    public List<Game> getAllGames() {
        return gameJpaRepository.findAll();
    }

    public Game getGameFromGameId(Integer gameId) {
        return gameJpaRepository.findById(gameId).orElse(null);
    }

    public Game addNewGame(GameDtoRequest game) {

        Game dbGame = gameJpaRepository.findByTitle(game.getGameRequest().getTitle());
        if(dbGame != null){
            String msg = "Already have game with this title in database.";
            log.error("Error occurred: {}", msg);
            throw new RuntimeException(msg);
        }

        Developer dev = developerJpaRepository.findByName(game.getDeveloperRequest().getName());
        if(dev == null){
            dev = this.addNewDeveloper(game.getDeveloperRequest());
        }

        Publisher pub = publisherJpaRepository.findByName(game.getPublisherRequest().getName());
        if(pub == null){
            pub = this.addNewPublisher(game.getPublisherRequest());
        }


        Game newGame = GameRequestMapperInterface.INSTANCE.makeGameFrom(game.getGameRequest(), pub, dev);

        newGame.setGid(0);

        List<GameTag> gameTagList = new ArrayList<>();
        if(!game.getTagList().isEmpty()){
            gameTagList = this.addTags(game.getTagList(), newGame);
        }

        newGame.setTags(gameTagList);

        return gameJpaRepository.save(newGame);
    }

    private List<GameTag> addTags(List<String> tagList, Game newGame) {

        List<GameTag> gameTagList = new ArrayList<>();

        for (String tag : tagList) {

            Tag tempTag = tagJpaRepository.findByTagName(tag);

            if (tempTag == null) {
                tempTag = addNewTag(tag);
            }

            GameTagId gameTagId = GameTagId.builder().game_id(0)
                    .tag_id(tempTag.getTagId())
                    .build();

            gameTagList.add( GameTag.builder()
                            .id(gameTagId)
                            .tag(tempTag)
                            .game(newGame)
                            .build());

        }

        return gameTagList;
    }

    private Tag addNewTag(String tag) {
        Tag newTag = Tag.builder()
                .tagId(0)
                .tagName(tag)
                .build();

        return tagJpaRepository.save(newTag);
    }

    private Publisher addNewPublisher(PublisherRequest publisherRequest) {
        Publisher newPub = Publisher.builder()
                .name(publisherRequest.getName())
                .pubId(0)
                .build();

        return publisherJpaRepository.save(newPub);
    }

    private Developer addNewDeveloper(DeveloperRequest developerRequest) {
        Developer newDev = Developer.builder()
                .name(developerRequest.getName())
                .devId(0)
                .build();

        return developerJpaRepository.save(newDev);
    }
}
