INSERT INTO author (name, surname) VALUES ('Misha', 'Kolesnik');
INSERT INTO author (name, surname) VALUES ('Ilya', 'Belevich');
INSERT INTO author (name, surname) VALUES ('Vlad', 'Makar');
INSERT INTO author (name, surname) VALUES ('Zhenya', 'Leon');
INSERT INTO author (name, surname) VALUES ('Vika', 'Hariton');
INSERT INTO author (name, surname) VALUES ('Manka', 'Manaenko');
INSERT INTO author (name, surname) VALUES ('Egor', 'Dubrovskiy');
INSERT INTO author (name, surname) VALUES ('Roman', 'Kreed');
INSERT INTO author (name, surname) VALUES ('Egor', 'Zhavaranok');
INSERT INTO author (name, surname) VALUES ('Ivan', 'Ivanov');
INSERT INTO author (name, surname) VALUES ('Ilya', 'Verbilo');
INSERT INTO author (name, surname) VALUES ('Dima', 'Bogachenko');
INSERT INTO author (name, surname) VALUES ('Nikita', 'Koruzin');
INSERT INTO author (name, surname) VALUES ('Nikita', 'Kozhemyako');
INSERT INTO author (name, surname) VALUES ('Nikita', 'Kolos');
INSERT INTO author (name, surname) VALUES ('Lera', 'Chernitckaya');
INSERT INTO author (name, surname) VALUES ('Maxim', 'Kureichik');
INSERT INTO author (name, surname) VALUES ('Artem', 'Kuzmik');
INSERT INTO author (name, surname) VALUES ('Grisha', 'Pischuk');
INSERT INTO author (name, surname) VALUES ('Vanya', 'Pupkin');

INSERT INTO tag (name) VALUES ('auto');
INSERT INTO tag (name) VALUES ('epam');
INSERT INTO tag (name) VALUES ('food');
INSERT INTO tag (name) VALUES ('policy');
INSERT INTO tag (name) VALUES ('nature');
INSERT INTO tag (name) VALUES ('summer');
INSERT INTO tag (name) VALUES ('family');
INSERT INTO tag (name) VALUES ('work');
INSERT INTO tag (name) VALUES ('computer');
INSERT INTO tag (name) VALUES ('home');
INSERT INTO tag (name) VALUES ('java');
INSERT INTO tag (name) VALUES ('technic');
INSERT INTO tag (name) VALUES ('web');
INSERT INTO tag (name) VALUES ('vacation');
INSERT INTO tag (name) VALUES ('kids');
INSERT INTO tag (name) VALUES ('books');
INSERT INTO tag (name) VALUES ('films');
INSERT INTO tag (name) VALUES ('cooking');
INSERT INTO tag (name) VALUES ('gifts');
INSERT INTO tag (name) VALUES ('jewelry');

INSERT INTO users (name, surname, login, password) VALUES ('Misha', 'Kolesnik', 'admin', 'admin');
INSERT INTO roles (user_id, role_name) VALUES (1, 'admin');

INSERT INTO users (name, surname, login, password) VALUES ('Skylar', 'Gray', 'user', 'user');
INSERT INTO roles (user_id, role_name) VALUES (2, 'user');

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title1','short_text1','full_text1', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (1, 1);
INSERT INTO news_tag (news_id, tag_id) VALUES (1, 1);
INSERT INTO news_tag (news_id, tag_id) VALUES (1, 3);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title2','short_text2','full_text2', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (2, 1);
INSERT INTO news_tag (news_id, tag_id) VALUES (2, 1);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title3','short_text3','full_text3', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (3, 2);
INSERT INTO news_tag (news_id, tag_id) VALUES (3, 16);
INSERT INTO news_tag (news_id, tag_id) VALUES (3, 17);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title4','short_text4','full_text4', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (4, 3);
INSERT INTO news_tag (news_id, tag_id) VALUES (4, 2);
INSERT INTO news_tag (news_id, tag_id) VALUES (4, 3);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title5','short_text5','full_text5', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (5, 4);
INSERT INTO news_tag (news_id, tag_id) VALUES (5, 7);
INSERT INTO news_tag (news_id, tag_id) VALUES (5, 8);
INSERT INTO news_tag (news_id, tag_id) VALUES (5, 11);
INSERT INTO news_tag (news_id, tag_id) VALUES (5, 12);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title6','short_text6','full_text6', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (6, 5);
INSERT INTO news_tag (news_id, tag_id) VALUES (6, 14);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title7','short_text7','full_text7', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (7, 5);
INSERT INTO news_tag (news_id, tag_id) VALUES (7, 13);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title8','short_text8','full_text8', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (8, 6);
INSERT INTO news_tag (news_id, tag_id) VALUES (8, 10);
INSERT INTO news_tag (news_id, tag_id) VALUES (8, 11);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title9','short_text9','full_text9', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (9, 6);
INSERT INTO news_tag (news_id, tag_id) VALUES (9, 10);
INSERT INTO news_tag (news_id, tag_id) VALUES (9, 11);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title10','short_text10','full_text10', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (10, 7);
INSERT INTO news_tag (news_id, tag_id) VALUES (10, 2);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title11','short_text11','full_text11', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (11, 8);
INSERT INTO news_tag (news_id, tag_id) VALUES (11, 4);
INSERT INTO news_tag (news_id, tag_id) VALUES (11, 10);
INSERT INTO news_tag (news_id, tag_id) VALUES (11, 1);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title12','short_text12','full_text12', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (12, 9);
INSERT INTO news_tag (news_id, tag_id) VALUES (12, 5);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title13','short_text13','full_text13', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (13, 10);
INSERT INTO news_tag (news_id, tag_id) VALUES (13, 1);
INSERT INTO news_tag (news_id, tag_id) VALUES (13, 4);
INSERT INTO news_tag (news_id, tag_id) VALUES (13, 19);
INSERT INTO news_tag (news_id, tag_id) VALUES (13, 11);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title14','short_text14','full_text14', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (14, 10);
INSERT INTO news_tag (news_id, tag_id) VALUES (14, 1);
INSERT INTO news_tag (news_id, tag_id) VALUES (14, 4);
INSERT INTO news_tag (news_id, tag_id) VALUES (14, 19);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title15','short_text15','full_text15', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (15, 10);
INSERT INTO news_tag (news_id, tag_id) VALUES (15, 9);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title16','short_text16','full_text16', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (16, 11);
INSERT INTO news_tag (news_id, tag_id) VALUES (16, 7);
INSERT INTO news_tag (news_id, tag_id) VALUES (16, 8);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title17','short_text17','full_text17', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (17, 11);
INSERT INTO news_tag (news_id, tag_id) VALUES (17, 7);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title18','short_text18','full_text18', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (18, 12);
INSERT INTO news_tag (news_id, tag_id) VALUES (18, 10);
INSERT INTO news_tag (news_id, tag_id) VALUES (18, 11);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title19','short_text19','full_text19', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (19, 13);
INSERT INTO news_tag (news_id, tag_id) VALUES (19, 7);
INSERT INTO news_tag (news_id, tag_id) VALUES (19, 4);
INSERT INTO news_tag (news_id, tag_id) VALUES (19, 1);
INSERT INTO news_tag (news_id, tag_id) VALUES (19, 20);
INSERT INTO news_tag (news_id, tag_id) VALUES (19, 18);

INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('title20','short_text20','full_text20', '2020-10-11', '2020-10-11');
INSERT INTO news_author (news_id, author_id) VALUES (20, 14);
INSERT INTO news_tag (news_id, tag_id) VALUES (20, 2);


