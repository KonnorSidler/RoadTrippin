# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table person (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  location                      varchar(255),
  constraint pk_person primary key (id)
);

create table trip (
  trip_id                       bigint auto_increment not null,
  start_location                varchar(255),
  end_location                  varchar(255),
  stop_count                    integer not null,
  constraint pk_trip primary key (trip_id)
);


# --- !Downs

drop table if exists person;

drop table if exists trip;

