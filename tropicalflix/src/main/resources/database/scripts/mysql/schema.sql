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



