show user;

-- ToyProject > script.sql

create user toy IDENTIFIED by java1234;
grant connect, resource, dba to toy;


-- 유저 테이블
create table tblUser (
    id varchar2(50) primary key,
    pw varchar2(50) not null,
    name varchar2(50) not null,
    email varchar2(100) not null,
    lv number(1) not null,                              -- 등급(1.일반, 2.관리자)
    pic varchar2(100) default 'pic.png' not null,
    intro varchar2(500) null,
    regdate date default sysdate not null,
    ing number(1) default 1 not null                    -- 탈퇴(1.활동, 0.탈퇴)  
);

-- 회원 데이터
insert into tblUser (id, pw, name, email, lv, pic, intro, regdate, ing)
    values ('hong', '1111', '홍길동', 'hong@gmail.com', 1, default, '안녕하세요. 길동이입니다.', default, default);
    
insert into tblUser (id, pw, name, email, lv, pic, intro, regdate, ing)
    values ('dog', '1111', '강아지', 'dog@gmail.com', 1, default, '뭉뭉이!', default, default);
    
insert into tblUser (id, pw, name, email, lv, pic, intro, regdate, ing)
    values ('cat', '1111', '고양이', 'cat@gmail.com', 2, default, '관리자냥이', default, default);
    
select * from tblUser;


commit;

-- 로그인
select * from tblUser where id = ? and pw = ? ;


/*
- "com.test.toy": 메인 패키지
		- "Index.jajva": 시작 페이지
		- "Template.java": 템플릿 페이지
		
	- "com.test.toy.user": 회원
		- "Register.java": 회원 가입
		- "Unregister.java": 회원 탈퇴
		- "Login.java": 로그인
		- "Logout.java": 로그아웃
	
	- "com.test.toy.user.repository": DB(DAO)
		- "UserDAO.java"
		
	- "com.test.toy.user.model": DB(DTO)
		- "UserDTO.java"
        
	- 뷰(webapp > WEB-INF)
		- "views"
			- "index.jsp"
			- "template.jsp"
		- views > "user"
			- "register.jsp"
			- "unregister.jsp"
			- "login.jsp"
			- "logout.jsp"
            
	- 공통 요소 & 리소스
		- views > "inc"
					- "asset.jsp"
					- "header.jsp"
		- webapp > "asset"
						> "css"
							- "main.css"
						> "js"
							- "main.js"
						>" pic"
                        
    *** lombok.jar 다운로드 > dev 파일로 넣은 후
    방법 1. 터미널 열기
            > java -jar lombok.jar
            > 이클립스.exe install
            > 끝! 이클립스 폴더 들어가서 파일 들어가 있나 확인하고
            > ini 메모장으로 열어서 마지막에 "~ --add-modules=ALL-SYSTEM
-javaagent:C:\class\dev\eclipse\lombok.jar" 이거 있나 확인!
    
    방법 2. 
            > 그냥 이클립스 파일에 복붙하고
            > > ini 메모장으로 열어서 마지막에 "~ --add-modules=ALL-SYSTEM
-javaagent:C:\class\dev\eclipse\lombok.jar" 이거 넣기!
                        
*/


--1010

-- 게시판 테이블
create table tblBoard (
    seq number primary key,
    subject varchar2(300) not null,
    content varchar2(4000) not null,
    regdate date default sysdate not null,
    readcount number default 0 not null,
    id varchar2(50) not null references tblUser(id) --아이디(FK)
);

create sequence seqBoard;


select * from tblBoard;

-- 작성 날짜 or 작성 시간 함수
--1. 조회 시각이 오늘과 같은 날짜면 > 시간 반환
--2. 조회 시각이 오늘과 다른 날짜면 > 날짜 반환
create or replace function fnRegdate (
    regdate date
) return varchar2
is

begin
    
    if to_char(sysdate, 'yyyy-mm-dd') = to_char(regdate, 'yyyy-mm-dd') then
        return to_char(regdate, 'hh24:mi:ss');
    else
        return to_char(regdate, 'yyyy-mm-dd');
    end if;
    
end fnRegdate;
/


select fnRegdate(regdate) from tblBoard order by seq desc;

--오류날때!
select tablespace_name, file_name, bytes from dba_data_files;
select username, default_tablespace from dba_user;

--system으로 실행
alter user toy default tablespace users;

select * from tblBoard order by seq desc;

update tblBoard set regdate = regdate - 1 where seq = 23;
update tblBoard set regdate = regdate - 2.5 where seq = 21;
update tblBoard set regdate = regdate - 5 where seq = 22;

commit;


select tblBoard.*, fnRegdate(regdate) as regtime, (select name from tblUser where id = tblBoard.id) as name from tblBoard order by seq desc;


create or replace view vwBoard
as
select 
    tblBoard.*, fnRegdate(regdate) as regtime, 
    (select name from tblUser where id = tblBoard.id) as name,
    (sysdate - regdate) as isnew,
    (select count(*) from tblComment where bseq = tblBoard.seq) as commentCount,
    (select count(*) from tblBoard b
    inner join tblTagging t
        on b.seq = t.bseq
            inner join tblHashtag h
                on h.seq = t.hseq
                    where b.seq = tblBoard.seq)as istag
from tblBoard 
    order by thread desc;
    
    

select * from vwBoard;
select * from tblBoard;

select * from (select a.*, rownum as rnum from vwBoard a)
    where rnum between 1 and 10;



--댓글 테이블
create table tblComment (
    seq number primary key,                         --번호(PK)
    content varchar2(200) not null,                 --댓글
    regdate date default sysdate not null,          --날짜
    id varchar2(50) not null references tblUser(id),--유저(FK)
    bseq number not null references tblBoard(seq)   --부모글(FK)
);

create sequence seqComment;



select * from tblBoard order by seq desc;



drop table tblComment;
drop table tblBoard;


-- 게시판 테이블(+답변 > 계층형)
create table tblBoard (
    seq number primary key,                             --글번호(PK)
    subject varchar2(300) not null,                     --제목
    content varchar2(4000) not null,                    --내용
    regdate date default sysdate not null,              --날짜
    readcount number default 0 not null,                --조회수
    id varchar2(50) not null references tblUser(id),    --아이디(FK)
    thread number not null,                             --답변형(그룹+정렬)
    depth number not null,                              --답변형(들여쓰기)
    ing number(1) default 1 not null                    --삭제 유무
);



select max(thread) as thread from tblBoard;





-- 게시판 테이블(+첨부파일)
create table tblBoard (
    seq number primary key,                             --글번호(PK)
    subject varchar2(300) not null,                     --제목
    content varchar2(4000) not null,                    --내용
    regdate date default sysdate not null,              --날짜
    readcount number default 0 not null,                --조회수
    id varchar2(50) not null references tblUser(id),    --아이디(FK)
    thread number not null,                             --답변형(그룹+정렬)
    depth number not null,                              --답변형(들여쓰기)
    ing number(1) default 1 not null,                   --삭제 유무
    attach varchar2(300) null                           --첨부파일
);


--추천(좋아요, 싫어요)
create table tblGoodBad (
    seq number primary key,                               --PK
    id varchar2(50) not null references tblUser(id),      --아이디(FK)
    bseq number not null references tblBoard(seq),        --게시물(FK)
    state number(1) not null                              --1(좋아요), 0(싫어요)                  

);

create sequence seqGoodBad;

select * from tblGoodBad;

select state,count(*) as cnt from tblGoodBad where bseq = 309 group by state order by state asc;





--해시태그
create table tblHashtag(
    seq number primary key,
    tag varchar2(100) unique not null
);
create sequence seqHashtag;

--연결(관계)
create table tblTagging (
    seq number primary key,
    bseq number not null references tblBoard(seq),
    hseq number not null references tblHashtag(seq)
);
create sequence seqTagging;


--unique 제약! 하나밖에 안들어옴 값이
select * from tblHashtag;

select * from tblTagging;

select * from tblBoard order by seq desc;


select h.tag from tblBoard b
    inner join tblTagging t
        on b.seq = t.bseq
            inner join tblHashtag h
                on h.seq = t.hseq
                    where b.seq = 322;



select * from (select a.*, rownum as rnum from vwBoard a %s)b
 inner join tblTagging t
        on b.seq = t.bseq
            inner join tblHashtag h
                on h.seq = t.hseq
                    where h.tag = '안녕';
                    
                    
--접속 기록! 남기기
create table tblLog(
    seq number primary key,
    id varchar2(50) not null references tblUser(id),
    regdate date default sysdate not null
);

create sequence seqLog;

select * from tblLog;


-- 테스트용 데이터
delete from tblGoodBad;
delete from tblComment;
delete from tblTagging;
delete from tblBoard;
delete from tblLog;

commit;

--로그인 기록(cat)
insert into tblLog values (seqLog.nextVal, 'cat', sysdate); --1번
insert into tblLog values (seqLog.nextVal, 'cat', sysdate -2);
insert into tblLog values (seqLog.nextVal, 'cat', sysdate -3);
insert into tblLog values (seqLog.nextVal, 'cat', sysdate -7); --1번
insert into tblLog values (seqLog.nextVal, 'cat', sysdate -10); --2번
insert into tblLog values (seqLog.nextVal, 'cat', sysdate -20); --1번
insert into tblLog values (seqLog.nextVal, 'cat', sysdate -24);

--글 쓰기 기록
insert into tblBoard 
    values (seqBoard.nextVal, '테스트 중...', '내용 테스트', sysdate -20, default, 'cat', 1000, 0, default, ''); 
insert into tblBoard 
    values (seqBoard.nextVal, '테스트 중...', '내용 테스트', sysdate -10, default, 'cat', 2000, 0, default, ''); 
insert into tblBoard 
    values (seqBoard.nextVal, '테스트 중...', '내용 테스트', sysdate -10, default, 'cat', 3000, 0, default, ''); 
insert into tblBoard 
    values (seqBoard.nextVal, '테스트 중...', '내용 테스트', sysdate -7, default, 'cat', 4000, 0, default, ''); 
insert into tblBoard 
    values (seqBoard.nextVal, '테스트 중...', '내용 테스트', sysdate, default, 'cat', 5000, 0, default, ''); 

insert into tblBoard 
    values (seqBoard.nextVal, '테스트 중...', '내용 테스트', sysdate -4, default, 'kkkk', 6000, 0, default, ''); 

insert into tblBoard 
    values (seqBoard.nextVal, '테스트 중...', '내용 테스트', sysdate -10, default, 'kkkk', 7000, 0, default, ''); 

-- 댓글('cat')
insert into tblComment values (seqComment.nextVal, '댓글_1!',  sysdate, 'cat', 334);
insert into tblComment values (seqComment.nextVal, '댓글_2!',  sysdate, 'cat', 334);

insert into tblComment values (seqComment.nextVal, '댓글_1!',  sysdate -4, 'cat', 337);

insert into tblComment values (seqComment.nextVal, '댓글_1!',  sysdate -20, 'cat', 330);



select * from tblBoard order by seq desc;



--2024년 10월 무슨 활동?
--글 몇 번 ? 댓글 몇 번 ? 로그인 언제 ? 태그 언제 ? 등등 ...

select
    count(*), 
    to_char(regdate, 'yyyy-mm-dd') as regdate,
    (select count(*) from tblBoard where to_char(regdate, 'yyyy-mm-dd') = to_char(l.regdate, 'yyyy-mm-dd')and id = ?) as bcnt,
    (select count(*) from tblComment where to_char(regdate, 'yyyy-mm-dd') = to_char(l.regdate, 'yyyy-mm-dd')and id = ?) as ccnt
from tblLog l
    where to_char(l.regdate, 'yyyy-mm') = '2024-10' and l.id = 'cat'
        group by to_char(regdate, 'yyyy-mm-dd');


select * from tblComment;
select tblComment.*, (select name from tblUser where id = tblComment.id) as name from tblComment;

select tblComment.*, (select name from tblUser where id = tblComment.id) as name from tblComment where bseq = 337 order by seq desc;

















