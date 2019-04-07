# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups


create table database_test (
  id                            bigserial not null,
  database_message              varchar(255),
  constraint pk_database_test primary key (id)
);


create table trip_info (
  trip_id                       bigserial not null,
  start_location                varchar(255),
  end_location                  varchar(255),
  min_stop_count                integer not null,
  max_stop_count                integer not null,
  stop_count                    integer not null,
  constraint pk_trip_info primary key (trip_id)
);

create table trip_rating (
  trip_rating_id                bigserial not null,
  trip_id                       bigint not null,
  min_rate_value                integer not null,
  max_rate_value                integer not null,
  rating                        integer not null,
  constraint pk_trip_rating primary key (trip_rating_id)
);



create table user (
  id                            bigserial not null,
  name                          varchar(255),
  location                      varchar(255),
  password                      varchar(255),
  constraint pk_user primary key (id)
);




drop table if exists database_test cascade;

drop table if exists person cascade;

drop table if exists trip_info cascade;

drop table if exists trip_rating cascade;

drop table if exists user cascade;

