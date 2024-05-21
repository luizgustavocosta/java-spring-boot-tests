DROP SCHEMA Tropicalflix;
CREATE SCHEMA Tropicalflix;
USE Tropicalflix;
create table Tropicalflix.actors
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table Tropicalflix.genres
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table Tropicalflix.movies
(
    id            bigint auto_increment
        primary key,
    director      varchar(255)  null,
    minutes       int           not null,
    overview      varchar(1000) null,
    rating        double        not null,
    released_year int           not null,
    title         varchar(255)  null
);
create table Tropicalflix.movies_actors
(
    movie_id bigint not null,
    actor_id bigint not null,
    primary key (movie_id, actor_id),
    constraint FKlexe564x1fi4ssje5wcuii3v2
        foreign key (actor_id) references Tropicalflix.actors (id),
    constraint FKo40lc9yirox8um5poaxhw6ak0
        foreign key (movie_id) references Tropicalflix.movies (id)
);
create table Tropicalflix.movies_genres
(
    movie_id bigint not null,
    genre_id bigint not null,
    primary key (movie_id, genre_id),
    constraint FKk0w7fx0a7flrtafppanyup87d
        foreign key (movie_id) references Tropicalflix.movies (id),
    constraint FKrs5u5iygsuht2f0cag9b9h1ob
        foreign key (genre_id) references Tropicalflix.genres (id)
);

INSERT INTO Tropicalflix.genres(name) VALUES ('Action');
INSERT INTO Tropicalflix.genres(name) VALUES ('Adventure');
INSERT INTO Tropicalflix.genres(name) VALUES ('Animation');
INSERT INTO Tropicalflix.genres(name) VALUES ('Biography');

INSERT INTO Tropicalflix.actors(ID, NAME) VALUES (1,'Tim Robbins');
INSERT INTO Tropicalflix.actors(ID, NAME) VALUES (2,'Morgan Freeman');

INSERT INTO Tropicalflix.movies(ID, DIRECTOR, OVERVIEW, TITLE,MINUTES, RATING, RELEASED_YEAR) VALUES (1,'Frank Darabont','Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.','The Shawshank Redemption',142,9.3,1994);
INSERT INTO Tropicalflix.movies(ID, DIRECTOR, OVERVIEW, TITLE,MINUTES, RATING, RELEASED_YEAR) VALUES (2,'Francis Ford Coppola','An organized crime dynasty''s aging patriarch transfers control of his clandestine empire to his reluctant son.','The Godfather',175,9.2,1972);

INSERT INTO Tropicalflix.movies_actors(ACTOR_ID, MOVIE_ID) VALUES (1,1);
INSERT INTO Tropicalflix.movies_actors(ACTOR_ID, MOVIE_ID) VALUES (1,2);
INSERT INTO Tropicalflix.movies_actors(ACTOR_ID, MOVIE_ID) VALUES (2,1);

INSERT INTO Tropicalflix.movies_genres(MOVIE_ID, GENRE_ID) VALUES (1,2);
INSERT INTO Tropicalflix.movies_genres(MOVIE_ID, GENRE_ID) VALUES (2,1);