drop table if exists player,bookings, ranking, selected;

create table player(
	id serial not null primary key,
	first_name text not null,
	last_name text not null,
	email text not null,
	age int not null,
	pos text not null,
	weight decimal not null,
	height decimal not null
);

create table bookings(
    id serial not null primary key,
    player_id int not null,
    card_id int not null
);

create table cards(
    id serial not null primary key,
    card_name text not null
);

create table selected(
    id serial not null primary key,
    player_id int not null,
    first_name text not null,
    last_name text not null
);

create table matches(
    id serial not null primary key,
    team1 text not null,
    team2 text not null,
    venue text not null,
    match_date date not null,
    match_time date not null
);

insert into player (first_name, last_name, email, age, pos, weight, height)
values ('Lindani', 'Pani', 'lindani@email.com', 21, 'GK', 52.5, 1.59);

insert into bookings (player_id, card_id) values (3, 2);

insert into cards (card_name) values ('Yellow');
insert into cards (card_name) values ('Red');

insert into selected (player_id, first_name, last_name) values (1, 'Lindani', 'Pani');

