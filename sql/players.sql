drop table if exists player,bookings, ranking, selected;

create table player(
	id serial not null primary key,
	first_name text not null,
	last_name text not null,
	email text not null,
	age int not null,
	pos text not null,
	weight decimal not null,
	height decimal not null,
	selected boolean null
);

create table bookings(
    id serial not null primary key,
    player_id int not null,
    card_id int not null
);

create table ranking(
    id serial not null primary key,
    card_name text not null
);

create table selected(
    id serial not null primary key,
    player_id int not null,
    first_name text not null,
    last_name text not null
);

insert into player (first_name, last_name, email, age, pos, weight, height)
values ('Lindani', 'Pani', 'lindani@email.com', 21, 'GK', 52.5, 1.59);
insert into player (first_name, last_name, email, age, pos, weight, height)
values ('Sihle', 'Feni', 'sfeni@email.com', 25, 'GK', 62.5, 1.63);
insert into player (first_name, last_name, email, age, pos, weight, height)
values ('Phumlani', 'Wapi', 'wapi@email.com', 31, 'GK', 60.5, 1.7);