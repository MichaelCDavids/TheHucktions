drop table if exists player,bookings, cards, selected,matches;

create table player(
	id serial not null primary key,
	first_name text not null,
	last_name text not null,
	email text not null,
	age int not null,
	field_position text not null,
	weight decimal not null,
	height decimal not null,
	yellow_cards int not null,
	red_cards int not null,
	goals int not null,
	assists int not null,
	clean_sheets int not null
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
    player_id int not null
);

create table matches(
    id serial not null primary key,
    team1 text not null,
    team2 text not null,
    venue text not null,
    match_date date not null
);

insert into player (first_name, last_name, email, age, field_position, weight, height, yellow_cards, red_cards,goals,assists,clean_sheets)
values ('Lindani', 'Pani', 'lindani@email.com', 21, 'GK', 52.5, 1.59,0,1,2,3,4);

insert into bookings (player_id, card_id) values (3, 2);

insert into cards (card_name) values ('Yellow');
insert into cards (card_name) values ('Red');

insert into selected (player_id) values (1);

