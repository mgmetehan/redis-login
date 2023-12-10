CREATE DATABASE redis_login;

\c redis_login;

create table "user" (
    id varchar(255) not null,
    password varchar(255),
    username varchar(255),
    deleted boolean,
    primary key (id)
);
