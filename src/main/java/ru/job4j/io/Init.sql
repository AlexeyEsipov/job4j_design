create database task379856;
create table author (
    id serial primary key,
    name varchar(2000)
);
create table compose (
    id serial primary key,
    name varchar(2000),
    author_id int references author(id)
);
create table music_lover (
    id serial primary key,
    name varchar(2000)
);
create table music_lover_compose (
    id serial primary key,
    music_lover_id int references music_lover(id),
    compose_id int references compose(id)
);