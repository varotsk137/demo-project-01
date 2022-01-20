CREATE TABLE tb_developer (
    dev_id   INTEGER      NOT NULL AUTO_INCREMENT,
    dev_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (dev_id)
);

CREATE TABLE tb_publisher (
    pub_id   INTEGER      NOT NULL AUTO_INCREMENT,
    pub_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (pub_id)
);

CREATE TABLE tb_tag (
    tag_id   INTEGER      NOT NULL AUTO_INCREMENT,
    tag_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (tag_id)
);

CREATE TABLE tb_game (
    game_id     INTEGER         NOT NULL AUTO_INCREMENT,
    title       VARCHAR(100)    NOT NULL,
    description VARCHAR(1000),
    price       DECIMAL(10,2)   NOT NULL,
    release_date TIMESTAMP       ,
    discount    INTEGER         NOT NULL,
    publisher   INTEGER         NOT NULL,
    developer   INTEGER         NOT NULL,
    PRIMARY KEY (game_id)
);