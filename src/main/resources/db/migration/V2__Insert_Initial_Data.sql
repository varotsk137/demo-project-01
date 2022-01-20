INSERT INTO `tb_tag` (tag_name) VALUES ('Cute');
INSERT INTO `tb_tag` (tag_name) VALUES ('Exploration');
INSERT INTO `tb_tag` (tag_name) VALUES ('Adventure');
INSERT INTO `tb_tag` (tag_name) VALUES ('Singleplayer');
INSERT INTO `tb_tag` (tag_name) VALUES ('Open World');
INSERT INTO `tb_tag` (tag_name) VALUES ('First-Person');

INSERT INTO `tb_publisher` (pub_name) VALUES ('Monomi Park');

INSERT INTO `tb_developer` (dev_name) VALUES ('Monomi Park');

INSERT INTO `tb_game` (title, description, price, release_date, discount, publisher, developer)
    VALUES ('Slime Rancher',
            'Slime Rancher is the tale of Beatrix LeBeau, a plucky, young rancher who sets out for a life a thousand light years away from Earth on the ‘Far, Far Range’ where she tries her hand at making a living wrangling slimes.',
            369.00, parsedatetime('01-08-2017 16:00:00.00', 'dd-MM-yyyy hh:mm:ss.SS'), 0, 1, 1);