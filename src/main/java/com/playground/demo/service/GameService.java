package com.playground.demo.service;

import com.playground.demo.mapper.GameRequestMapperInterfaceImpl;
import com.playground.demo.model.entity.*;
import com.playground.demo.model.entity.id.GameTagId;
import com.playground.demo.model.request.DeveloperRequest;
import com.playground.demo.model.request.GameDtoRequest;
import com.playground.demo.model.request.PublisherRequest;
import com.playground.demo.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    private GameTagJpaRepository gameTagJpaRepository;


    public List<Game> getAllGames() {
        return gameJpaRepository.findAll();
    }

    public Game getGameFromGameId(Integer gameId) {
        return gameJpaRepository.findById(gameId).orElse(null);
    }

    @Transactional
    public Game addNewGame(GameDtoRequest game) {

        Game dbGame = gameJpaRepository.findByTitle(game.getGameRequest().getTitle());
        if(dbGame != null){
            String msg = "Already have game with this title in database.";
            log.error("Error occurred: {}", msg);
            throw new RuntimeException(msg);
        }

        Developer dev = getOrAddDeveloper(game);

        Publisher pub = getOrAddPublisher(game);

        Game newGame = GameRequestMapperInterfaceImpl.INSTANCE.makeGameFrom(game.getGameRequest(), pub, dev);

        newGame.setGid(0);

        List<GameTag> gameTagList = makeGameTag(game, newGame);

        newGame.setTags(gameTagList);

        return gameJpaRepository.save(newGame);
    }

    @Transactional
    public Game editGameWithGameId(Integer gameId, GameDtoRequest gameDtoRequest) {

        Game game = gameJpaRepository.findById(gameId).orElseThrow(() ->
                new RuntimeException("The specify game id doesn't exists in database."));

        Developer dev = getOrAddDeveloper(gameDtoRequest);

        Publisher pub = getOrAddPublisher(gameDtoRequest);

        removeOldTagsFromGame(gameId);

        List<GameTag> gameTagList = makeGameTag(gameDtoRequest, game);

        GameRequestMapperInterfaceImpl.INSTANCE.updateGameFrom(game, gameDtoRequest.getGameRequest(), pub, dev, gameTagList);


        return gameJpaRepository.save(game);
    }

    private void removeOldTagsFromGame(Integer gameId) {
        gameTagJpaRepository.deleteByIdGameId(gameId);
        gameTagJpaRepository.flush();
    }

    private List<GameTag> makeGameTag(GameDtoRequest game, Game newGame) {
        List<GameTag> gameTagList = new ArrayList<>();
        if(!game.getTagList().isEmpty()){
            gameTagList = this.addTags(game.getTagList(), newGame);
        }
        return gameTagList;
    }

    private Publisher getOrAddPublisher(GameDtoRequest game) {
        Publisher pub = publisherJpaRepository.findByName(game.getPublisherRequest().getName());
        if(pub == null){
            pub = this.addNewPublisher(game.getPublisherRequest());
        }
        return pub;
    }

    private Developer getOrAddDeveloper(GameDtoRequest game) {
        Developer dev = developerJpaRepository.findByName(game.getDeveloperRequest().getName());
        if(dev == null){
            dev = this.addNewDeveloper(game.getDeveloperRequest());
        }
        return dev;
    }

    private List<GameTag> addTags(List<String> tagList, Game newGame) {

        List<GameTag> gameTagList = new ArrayList<>();

        for (String tag : tagList) {

            Tag tempTag = tagJpaRepository.findByTagName(tag);

            if (tempTag == null) {
                tempTag = addNewTag(tag);
            }

            GameTagId gameTagId = GameTagId.builder().gameId(0)
                    .tagId(tempTag.getTagId())
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
