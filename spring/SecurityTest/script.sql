
-- SecurityTest > script.sql

create table users (
    username varchar2(50) not null primary key, --아이디
    password varchar2(50) not null,             --암호
    enabled char(1) default '1'
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
insert into authorities (username, authority) values ('tiger', 'ROLE_ADMIN');
insert into authorities (username, authority) values ('tiger', 'ROLE_MEMBER');

commit;

select * from users;

drop table member;

-- 28. 사용자가 직접 만든 스키마 사용
create table member (
    memberid varchar2(50) primary key,      --아이디(PK)
    memberpw varchar2(100) not null,        --암호
    membername varchar2(50) not null,       --이름
    enabled char(1) default '1',            --활성화
    email varchar2(100) not null,           --이메일
    age number not null,                    --나이
    gender char(1) not null                 --성별
);

create table member_auth (
    memberid varchar2(50) not null,
    auth varchar2(50) not null,
    constraint fk_member_auth 
        foreign key(memberid) references member(memberid)
);

insert into member (username, password) values ('dog', '1111');



select * from member;
select * from member_auth;


select m.memberid, m.memberpw, m.membername, m.enabled, m.email m.age, m.gender, a.auth from 
member m inner join member_auth a on m.memberid = a.memberid where m.memberid = 'dog'





