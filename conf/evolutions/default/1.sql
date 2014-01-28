# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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

create table account (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_account primary key (email))
;

create sequence eat_seq;

create sequence account_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists eat;

drop table if exists account;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists eat_seq;

drop sequence if exists account_seq;

