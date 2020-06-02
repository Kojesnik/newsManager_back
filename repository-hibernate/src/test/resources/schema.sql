CREATE TABLE author (
    id bigserial NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name CHARACTER VARYING(30) NOT NULL,
    surname CHARACTER VARYING(30) NOT NULL
);

CREATE TABLE news (
    id bigserial NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title CHARACTER VARYING(30) NOT NULL,
    short_text CHARACTER VARYING(100) NOT NULL,
    full_text CHARACTER VARYING(2000) NOT NULL,
    creation_date DATE NOT NULL,
    modification_date DATE NOT NULL
);

CREATE TABLE users (
    id bigserial NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name CHARACTER VARYING(20) NOT NULL,
    surname CHARACTER VARYING(20) NOT NULL,
    login CHARACTER VARYING(30) NOT NULL,
    password CHARACTER VARYING(30) NOT NULL
);

CREATE TABLE tag (
    id bigserial NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name CHARACTER VARYING(30) NOT NULL
);

CREATE TABLE roles (
    user_id bigint NOT NULL,
    role_name CHARACTER VARYING(30) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE news_author (
    author_id bigint NOT NULL,
    news_id bigint NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE,
    FOREIGN KEY (news_id) REFERENCES news(id) ON DELETE CASCADE
);

CREATE TABLE news_tag (
    tag_id bigint NOT NULL,
    news_id bigint NOT NULL,
    FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE,
    FOREIGN KEY (news_id) REFERENCES news(id) ON DELETE CASCADE
);



