drop table exchanges if exists;
drop table users if exists;

create table if not exists exchanges (
    exchange_id integer auto_increment  primary key,
    currency_origin varchar(50) not null,
    currency_target  varchar(50) not null,
    amount decimal(10,5) not null,
    exchange_amount decimal(10,5) not null,
    exchange_rate decimal(10,5),
    created_at  timestamp not null,
    modified_at timestamp,
    created_by  varchar(50) not null,
    modified_by varchar(50)
);

create table if not exists users(
    id integer auto_increment primary key,
    username varchar(20) not null,
    password varchar(100) not null,
    rol varchar(20) not null
);

INSERT INTO users(id, username, password, rol) VALUES(1, 'rcastillo', '$2a$10$1AKUvEGctgFl4pLPA7vAMeu5CrqOWt.NEye.f2xbpLMw9NZwbFrx6', 'admin');


