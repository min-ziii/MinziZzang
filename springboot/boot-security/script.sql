-- boot-security > script.sql

create table member (
    seq number primary key,
    username varchar2(50) not null unique,
    password varchar2(100) not null,
    role varchar2(50) not null
);

create sequence seqMember;


select * from member;

delete from member where seq=1;