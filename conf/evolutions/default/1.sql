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
  waypoint_one                  varchar(255),
  waypoint_two                  varchar(255),
  waypoint_three                varchar(255),
  waypoint_four                 varchar(255),
  waypoint_five                 varchar(255),
  constraint pk_trip_info primary key (trip_id)
);

create table trip_rating (
  rating_id                     bigserial not null,
  trip_rating                   integer not null,
  trip_id                       bigint not null,
  constraint pk_trip_rating primary key (rating_id)
);

create table user_account (
  id                            bigserial not null,
  username                      varchar(255),
  location                      varchar(255),
  constraint uq_user_account_username unique (username),
  constraint pk_user_account primary key (id)
);


# --- !Downs

drop table if exists database_test cascade;

drop table if exists trip_info cascade;

drop table if exists trip_rating cascade;

drop table if exists user_account cascade;

