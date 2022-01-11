package com.playground.demo.mapper;

import com.playground.demo.model.WishListGame;
import com.playground.demo.model.Developer;
import com.playground.demo.model.Game;
import com.playground.demo.model.Publisher;
import com.playground.demo.model.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WishListGameMapperInterfaceTest {

    @InjectMocks WishListGameMapperInterface wishListGameMapperInterface;

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
                .price(0.00)
                .developer(developer)
                .publisher(publisher)
                .releaseDate(ZonedDateTime.of(2020, 11, 5, 0, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .tags(tagList)
                .build();

        WishListGame wlg = WishListGameMapperInterface.INSTANCE.GameToWishlistGame( game );

        assertEquals(game.getTitle(), wlg.getGameTitle());
        assertEquals(game.getPrice(), wlg.getCurrentPrice());
        assertEquals(game.getDeveloper(), wlg.getDeveloper());
        assertEquals(game.getPublisher(), wlg.getPublisher());
        assertEquals(game.getTags(), wlg.getTagList());
        assertEquals(ZonedDateTime.now().isAfter(wlg.getWishlistTime()), true);

    }
}