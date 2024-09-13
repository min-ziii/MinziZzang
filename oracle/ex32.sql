/*
    ex32.sql
*/

CREATE TABLE tblDate(
    seq number Primary Key,
    state varchar2(30) NOT NULL,
    regdate date NOT NULL
);


insert into tblDate (seq, state, regdate) values ( 1, '정상', '2024-08-01');
insert into tblDate (seq, state, regdate) values ( 2, '정상', '2024-08-02');
-- 08-03: 토요일
-- 08-04: 일요일

insert into tblDate (seq, state, regdate) values ( 3, '지각', '2024-08-05');
insert into tblDate (seq, state, regdate) values ( 4, '정상', '2024-08-06');
insert into tblDate (seq, state, regdate) values ( 5, '정상', '2024-08-07');
insert into tblDate (seq, state, regdate) values ( 6, '조퇴', '2024-08-08');
insert into tblDate (seq, state, regdate) values ( 7, '정상', '2024-08-09');
-- 08-10: 토요일
-- 08-11: 일요일


insert into tblDate (seq, state, regdate) values ( 8, '조퇴', '2024-08-12');
insert into tblDate (seq, state, regdate) values ( 9, '정상', '2024-08-13');
insert into tblDate (seq, state, regdate) values (10, '정상', '2024-08-14');
insert into tblDate (seq, state, regdate) values (11, '정상', '2024-08-16');
-- 08-15: 광복절
-- 08-17: 토
-- 08-18: 일

insert into tblDate (seq, state, regdate) values (12, '정상', '2024-08-19');
insert into tblDate (seq, state, regdate) values (13, '정상', '2024-08-20');
insert into tblDate (seq, state, regdate) values (14, '지각', '2024-08-21');
-- 08-22: 결석
insert into tblDate (seq, state, regdate) values (15, '정상', '2024-08-23');
-- 08-24: 토
-- 08-25: 일

insert into tblDate (seq, state, regdate) values (16, '정상', '2024-08-26');
insert into tblDate (seq, state, regdate) values (17, '지각', '2024-08-27');
insert into tblDate (seq, state, regdate) values (18, '정상', '2024-08-28');
insert into tblDate (seq, state, regdate) values (19, '정상', '2024-08-29');
insert into tblDate (seq, state, regdate) values (20, '정상', '2024-08-30');
-- 08-31: 토

commit;

select to_char(regdate, 'yyyy-mm-dd hh24:mi:ss') from tblDate;

-- 공휴일
CREATE TABLE tblHoliday(
    seq number Primary Key,
    regdate date NOT NULL,
    name varchar2(30) NOT NULL
);

INSERT INTO tblHoliday (seq, regdate, name) VALUES (1, '2024-08-15', '광복절');


-- 요구사항) 근태 조회 -> 8월 전체 근태상황 열람
-- (***) 날짜별 조회 -> 결석일 포함 + 휴일 포함
-- (***) 2024-08-01 ~ 2024-08-31 모든 날짜 열람
-- (***) 빠진 날짜 메우기

-- 1. 표준SQL 방식 (***)
-- 2. PL/SQL
-- 3. Java 연동


SET serveroutput ON;

-- 2. PL/SQL 사용
DECLARE
    vdate date;
    vstate tblDate.state%type;
    vcnt number;
BEGIN
    -- seed(2024-08-01)
    vdate := to_date('2024-08-01', 'yyyy-mm-dd'); -- 00:00:00
    
    for i in 1..31 LOOP
        dbms_output.put_line(vdate);
        
        SELECT Count(*) INTO vcnt FROM tblDate WHERE to_char(regdate, 'yyyy-mm-dd') = to_char(vdate, 'yyyy-mm-dd');
        
        -- 해당 날짜의 근태 기록 -> tblDate에 있음
        IF vcnt > 0 then
            SELECT state INTO vstate FROM tblDate WHERE to_char(regdate, 'yyyy-mm-dd') = to_char(vdate, 'yyyy-mm-dd');
            dbms_output.put_line(vstate);
        ELSE
            --dbms_output.put_line('결석 or 주말 or 공휴일');
            IF to_char(vdate, 'dy') in ('토','일') then
                dbms_output.put_line('주말');
            ELSE
                --dbms_output.put_line('결석 or 공휴일');
                SELECT Count(*) INTO vcnt FROM tblHoliday WHERE to_char(vdate, 'yyyy-mm-dd') = to_char(regdate, 'yyyy-mm-dd');
                
                IF vcnt > 0 then
                    dbms_output.put_line('공휴일');
                ELSE
                    dbms_output.put_line('결석');
                END IF;
            END IF;
        END IF;
        vdate := vdate + 1; -- 하루씩 증가
    END LOOP;
END;
/


-- 1. 표준SQL 사용
-- 계층형 쿼리가 루프 역할을 할 것.

SELECT
    level,
    sysdate + level - 1 -- 오늘부터 10일 뒤까지
FROM dual
    CONNECT BY LEVEL <= 10;
    
-- 원하는 날짜 생성하기 -> 써먹을 데가 많으니 꼭 기억할 것!
CREATE OR REPLACE VIEW vwDate
AS
SELECT
    to_date('2024-08-01', 'yyyy-mm-dd') + level - 1 as regdate
FROM dual
    CONNECT BY level <= to_date('2024-08-31', 'yyyy-mm-dd') - to_date('2024-08-01', 'yyyy-mm-dd') + 1;
    
    
    
SELECT * FROM vwDate; -- 8월 한 달 날짜
SELECT * FROM tblDate; -- 8월 근태 기록

-- 날짜와 근태 한 화면에서 보기
SELECT
    *
FROM vwDate v
LEFT OUTER JOIN tblDate t
ON to_char(v.regdate,'yyyy-mm-dd') = to_char(t.regdate,'yyyy-mm-dd') -- 출석이 시/분/초까지 똑같을 필요는 없으니까 to_char로 연월일만 맞춰준다.
ORDER BY v.regdate ASC;


-- 휴일 테이블도 JOIN
SELECT
    *
FROM vwDate v
LEFT OUTER JOIN tblDate t
ON to_char(v.regdate,'yyyy-mm-dd') = to_char(t.regdate,'yyyy-mm-dd') -- 출석이 시/분/초까지 똑같을 필요는 없으니까 to_char로 연월일만 맞춰준다.
LEFT OUTER JOIN tblHoliday h
ON to_char(v.regdate,'yyyy-mm-dd') = to_char(h.regdate,'yyyy-mm-dd')
ORDER BY v.regdate ASC;


-- state가 NULL인 곳에 이유 채우기 (완성)
SELECT
    v.regdate,
    CASE
        When to_char(v.regdate,'d') = '1' then '일요일'
        When to_char(v.regdate,'d') = '7' then '토요일'
        When h.seq IS NOT NULL then h.name
        When h.seq IS NULL and t.seq IS NULL then '결석'
        Else t.state
    END as state
FROM vwDate v
LEFT OUTER JOIN tblDate t
ON to_char(v.regdate,'yyyy-mm-dd') = to_char(t.regdate,'yyyy-mm-dd') -- 출석이 시/분/초까지 똑같을 필요는 없으니까 to_char로 연월일만 맞춰준다.
LEFT OUTER JOIN tblHoliday h
ON to_char(v.regdate,'yyyy-mm-dd') = to_char(h.regdate,'yyyy-mm-dd')
ORDER BY v.regdate ASC;