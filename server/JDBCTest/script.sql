-- JDBCTest/script.sql
create user server identified by java1234;
grant connect, resource, dba to server;


-- 주소록 테이블
create table tblAddress (
    seq number,                             --번호(PK)
    name varchar2(30) not null,             --이름
    age number(3) not null,                 --나이
    gender char(1) not null,                --성별(m,f)
    tel varchar2(15) not null,              --전화번호
    address varchar2(300) not null,         --주소
    regdate date default sysdate not null   --등록일
);

alter table tblAddress
    add constraint address_seq_pk primary key(seq);
    
alter table tblAddress
    add constraint address_age_ck check (age between 0 and 150);
    
alter table tblAddress
    add constraint address_gender_ck check (gender in ('m', 'f'));
    
create sequence seqAddress;


-- CRUD
insert into tblAddress (seq, name, age, gender, tel, address, regdate)
    values (seqAddress.nextVal, '홍길동', 20, 'm', '010-1234-5678'
            , '서울시 강남구 역삼동', default);

select * from tblAddress;

update tblAddress set age = age + 1 where seq = 1;

delete from tblAddress where seq = 1;
 
commit;

create table tblJava (
    seq number primary key,
    data varchar2(100) not null
);

select * from tblJava;

select * from tblINSA;


select * from tblInsa;
select * from tblBonus;

describe tblBonus;

delete from tblBonus;
commit;



select * from tblAddress where seq =3;

show user;

-- Ex06.CallableStatement.java
--m1. 인자값X, 반환값X
create or replace procedure procM1
is
begin
    insert into tblAddress (seq, name, age, gender, tel, address, regdate)
    values (seqAddress.nextVal, '이타조', 20, 'm', '010-1234-5678'
            , '서울시 강남구 역삼동', default);
end procM1;
/

--m2. 인자값O, 반환값X
create or replace procedure procM2 (
    pname varchar2,
    page number,
    pgender varchar2,
    ptel varchar2,
    paddress varchar2
)
is
begin
 insert into tblAddress (seq, name, age, gender, tel, address, regdate)
    values (seqAddress.nextVal, pname, page, pgender, ptel, paddress, default);
end procM2;
/


--m3. 인자값X, 반환값O
create or replace procedure procM3 (
    pcnt out number
)
is
begin
    select count(*) into pcnt from tblAddress;
end procM3;
/


select name from tblAddress;

select * from tblAddress;

--m4. 인자값O, 반환값O
-- 직원번호 > 이름, 부서, 직위
create or replace procedure procM4 (
    pnum in number,
    pname out varchar2,
    pbuseo out varchar2,
    pjikwi out varchar2
)
is
begin
    select name, buseo, jikwi into pname, pbuseo, pjikwi from tblInsa where num = pnum;
end procM4;
/

--m5.커서 반환
create or replace procedure procM5 (
    pbuseo in varchar2,
    pcursor out sys_refcursor
)
is
begin
   open pcursor
   for
   select * from tblInsa where buseo = pbuseo;
end procM5;
/

select name from tblAddress;

select * from tblAddress;








