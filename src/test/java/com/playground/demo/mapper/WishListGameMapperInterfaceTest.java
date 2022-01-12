package com.playground.demo.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.playground.demo.model.WishListGame;
import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.Game;
import com.playground.demo.model.entity.Publisher;
import com.playground.demo.model.entity.Tag;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WishListGameMapperInterfaceTest {

    @InjectMocks
    WishListGameMapperInterface wishListGameMapperInterface;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }


    @Test
    public void gameToWishlistGame() {

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
                .price(BigDecimal.valueOf(1000.00))
                .developer(developer)
                .publisher(publisher)
                .releaseDate(ZonedDateTime.of(2020, 11, 5, 0, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .tags(tagList)
                .discount(5)
                .build();

        WishListGame wlg = wishListGameMapperInterface.INSTANCE.gameToWishlistGame( game );

        BigDecimal discountedPrice = game.getPrice()
                .multiply((BigDecimal.valueOf(100).subtract(BigDecimal.valueOf(game.getDiscount()))).divide(BigDecimal.valueOf(100)));

        assertEquals(game.getTitle(), wlg.getGameTitle());
        assertEquals(discountedPrice, wlg.getCurrentPrice());
        assertEquals(game.getDeveloper(), wlg.getDeveloper());
        assertEquals(game.getPublisher(), wlg.getPublisher());
        assertEquals(game.getTags(), wlg.getTagList());
        assertEquals(ZonedDateTime.now().isAfter(wlg.getWishlistTime()), true);

    }

    @Test
    public void gameToWishlistGameWithJson() {

        Game game = null;
        try {
            game = objectMapper.readValue(new File("src/test/resources/json/game1.json"), Game.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WishListGame wlg = wishListGameMapperInterface.INSTANCE.gameToWishlistGame( game );

        BigDecimal discountedPrice = game.getPrice()
                .multiply((BigDecimal.valueOf(100).subtract(BigDecimal.valueOf(game.getDiscount()))).divide(BigDecimal.valueOf(100)));

        assertEquals(game.getTitle(), wlg.getGameTitle());
        assertEquals(discountedPrice, wlg.getCurrentPrice());
        assertEquals(game.getDeveloper(), wlg.getDeveloper());
        assertEquals(game.getPublisher(), wlg.getPublisher());
        assertEquals(game.getTags(), wlg.getTagList());
        assertEquals(ZonedDateTime.now().isAfter(wlg.getWishlistTime()), true);

    }

    @Test
    public void gameToWishlistGameWithEasyRandom() {

        EasyRandom easyRandom = new EasyRandom();
        Game game = easyRandom.nextObject(Game.class);

        WishListGame wlg = wishListGameMapperInterface.INSTANCE.gameToWishlistGame( game );

        BigDecimal discountedPrice = game.getPrice()
                .multiply((BigDecimal.valueOf(100).subtract(BigDecimal.valueOf(game.getDiscount()))).divide(BigDecimal.valueOf(100)));

        assertEquals(game.getTitle(), wlg.getGameTitle());
        assertEquals(discountedPrice, wlg.getCurrentPrice());
        assertEquals(game.getDeveloper(), wlg.getDeveloper());
        assertEquals(game.getPublisher(), wlg.getPublisher());
        assertEquals(game.getTags(), wlg.getTagList());
        assertEquals(ZonedDateTime.now().isAfter(wlg.getWishlistTime()), true);

    }



}