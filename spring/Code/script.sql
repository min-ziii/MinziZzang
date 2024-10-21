--1021

drop table tblCode;
--코드 테이블
create table tblCode(
    seq number primary key,
    subject varchar2(300) not null,
    code varchar2(2000) not null,
    regdate date default sysdate not null,
    language varchar2(30) not null
);

create sequence seqCode;



















