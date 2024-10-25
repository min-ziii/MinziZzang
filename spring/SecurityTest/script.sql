--1025

create table users (
    username varchar2(50) not null primary key,     --아이디
    password varchar2(50) not null,                 --암호
    enabled char(1) default '1'                     --상태

);

create table authorities (
    username varchar2(50) not null,
    authority varchar2(50) not null,
    
    constraint fk_authorities_users
        foreign key(username) references users(username)
        
);

create unique index ix_auth_username on authorities (username, authority);


insert into users (username, password) values ('dog', '1111');
insert into users (username, password) values ('cat', '1111');
insert into users (username, password) values ('tiger', '1111');

insert into authorities (username, authority) values ('dog', 'ROLE_MEMBER');
insert into authorities (username, authority) values ('cat', 'ROLE_MEMBER');
insert into authorities (username, authority) values ('tiger', 'ROLE_MEMBER');
insert into authorities (username, authority) values ('tiger', 'ROLE_ADMIN');
















