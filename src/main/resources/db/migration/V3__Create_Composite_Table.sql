CREATE TABLE tb_game_tag (
    game_id     INTEGER     NOT NULL,
    tag_id      INTEGER     NOT NULL,
    PRIMARY KEY (game_id, tag_id),
    FOREIGN KEY (game_id) REFERENCES tb_game(game_id),
    FOREIGN KEY (tag_id) REFERENCES tb_tag(tag_id)
);