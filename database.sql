create database cinema;
use cinema;

create table category(
    id int not null PRIMARY KEY AUTO_INCREMENT,
    name varchar(30) not null
);
create table movie(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title varchar(30) not null,
    description TEXT NOT NULL,
    synopsis TEXT NOT NULL,
    rating int not null,
    registered_date DATETIME NOT NULL,
    updated_date DATETIME NOT NULL,
    state TINYINT not null,
    category int NOT NULL,
    CONSTRAINT movie_fk_category FOREIGN KEY (category) REFERENCES category (id)
);