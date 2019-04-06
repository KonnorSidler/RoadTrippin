# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table person (
  id                            bigserial not null,
  name                          varchar(255),
  constraint pk_person primary key (id)
);

create table trip_info (
  trip_id                       bigserial not null,
  start_location                varchar(255),
  end_location                  varchar(255),
  stop_count                    integer not null,
  constraint pk_trip_info primary key (trip_id)
);


# --- !Downs

drop table if exists person cascade;

drop table if exists trip_info cascade;

