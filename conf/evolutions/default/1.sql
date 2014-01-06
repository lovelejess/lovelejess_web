# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table asana (
  id                        varchar(255) not null,
  url                       varchar(255),
  fullname                  varchar(255),
  submitted_on              timestamp,
  title                     varchar(255),
  text                      varchar(255),
  image                     varchar(255),
  constraint pk_asana primary key (id))
;

create table eat (
  id                        varchar(255) not null,
  url                       varchar(255),
  fullname                  varchar(255),
  submitted_on              timestamp,
  title                     varchar(255),
  text                      varchar(255),
  image                     varchar(255),
  constraint pk_eat primary key (id))
;

create sequence asana_seq;

create sequence eat_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists asana;

drop table if exists eat;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists asana_seq;

drop sequence if exists eat_seq;

