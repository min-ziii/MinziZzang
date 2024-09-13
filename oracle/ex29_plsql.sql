/*
    ex29_plsql.sql
    
    PL/SQL
    - Procedural Language for SQL
    - Procedural Language extension for SQL
    - 표준 SQL
    - 표준 SQL에 절차지향 언어(변수, 제어 흐름, 객체 정의 등)의 기능을 추가한 SQL
    
    PL/SQL의 단위: 프로시저
    
    프로시저
    -메서드, 함수 등... 같은 말임.
    - 순서가 있는 명령어들의 집합
    - 모든 PL/SQL 구문은 프로시저 내에서만 작성/동작 가능
    - 표준 SQL 영역
    
    
    1. 익명 프로시저
        - 1회용 코드 작성
    
    2. 저장 프로시저
        - 저장
        - 재사용
        - 데이터베이스 객체(CREATE/DROP)
    
    
    
    PL/SQL 프로시저 구조
        - DECLARE
        - BEGIN
        - EXCEPTION
        - END

        
    1. DECLARE
        - 선언부
        - 프로시저 내에서 사용할 변수, 객체 등을 선언하는 영역
        - 선언할 게 없으면 생략 가능
        
    2. BEGIN({) ~ END (})
        - 구현부
        - 구현된 코드를 가지는 영역(Method의 body 영역)
        - 생략 불가능
        - 구현된 코드란 PL/SQL(연산, 제어 등)과 표준SQL(DDL, DML)을 의미
    
    3. EXCEPTION
        - 예외 처리부
        - Java의 catch절의 역할과 같다. (try절은 BEGIN~END)
        - 평소엔 실행되지 않고 에러가 발생했을 때 실행된다.
        - 생략 가능
    
    
    구문의 형태
    
    [DECLARE
        변수 선언;
        객체 선언;]
    BEGIN
        업무 코드(예-변수선언);
        업무 코드(예-제어문);
        업무 코드(SQL);
    [EXCEPTION
        예외 처리 코드;]
    END;
    
    
    Java...SQL...PL/SQL 식으로 언어를 배우는 중...
    
    PL/SQL의 자료형
    - 표준SQL과 동일
    
    변수 선언하는 법
    - 변수명 자료형(길이) NOT NULL [DEFAULT 값];
    
    
*/

-- 여태 수업했던 이 영역(지금 보는 이 스크립트 파일 화면)은 표준SQL 영역이다.
-- PL/SQL을 그냥 선언할 수 없음

-- dbms_output.put_line()
-- 출력 함수
-- 평상시에는 결과가 눈에 보이지 않게 설정되어 있다.
-- 보이게 바꾸는 법은?
SET serveroutput on;
-- 다시 안 보이게 설정하려면?
SET serverout on;
SET serveroutput off;
-- 계정의 현재 상태에 귀속된 설정이기 때문에 계정 접속이 끊기거나 프로그램을 종료하면 다시 설정해줘야 한다.

-- SQL Developer는 PL/SQL 기준 인텔리센스가 좋지 않은 편이라 도구-환경설정-코드 편집기-코드템플리트에서 출력함수를 syso처럼 자동완성되게 만들어 두는 것도 좋다.

-- 익명 프로시저(내용물 없음)
BEGIN
END;
/


DECLARE
    num number;
BEGIN
    num := 10; -- PL/SQL식 대입 연산자.
    dbms_output.put_line(num); -- PL/SQL식 출력 함수
END;
/ -- 프로시저 만들 때 /를 이렇게 끝에 붙여두면 표준SQL처럼 프로그램이 알아서 블럭을 나누는 기준이 된다.

DECLARE
    num number;
    name varchar2(30);
    today date;
BEGIN
    num := 10;
    dbms_output.put_line(num);
    
    name := '홍길동';
    dbms_output.put_line(name);
    
    today := sysdate;
    today := to_date('2024-08-02', 'yyyy-mm-dd');
    dbms_output.put_line(today);
END;
/

DECLARE
    num1 number;
    num2 number;
    num3 number := 30; -- Java처럼 선언과 동시에 초기화도 가능.
    num4 number DEFAULT 40;
    num5 number NOT NULL := 50; -- NOT NULL 선언과 동시에 초기화
BEGIN
    dbms_output.put_line('@' || num1 || '@'); -- 변수가 NULL인 상태에서도 출력이 된다.
    
    num2 := 10;
    dbms_output.put_line(num2);
    
    dbms_output.put_line(num3);
    
    num4 := 400;
    dbms_output.put_line(num4);
    
    -- num5 := NULL; -- 이미 NOT NULL로 선언되었기 때문에 실행 시 오류 발생
    dbms_output.put_line(num5);
END;
/


/*
    PL/SQL에서 변수는 주로 어떤 용도로 사용하는가?
    - 일반적인 값을 저장하는 용도보다는 SELECT문의 결과를 담는 용도로 더 많이 사용한다.
    
    SELECT 결과를 PL/SQL 변수에 대입
    - SELECT INTO절(PL/SQL 전용 문법)
*/

DECLARE
    vbuseo varchar2(15); -- Variable-buseo라는 뜻. 컬럼이름하고 겹치면 헷갈리니까 변수라고 따로 표기해주는 것
BEGIN
    SELECT buseo INTO vbuseo FROM tblInsa WHERE name = '홍길동'; -- 표준SQL의 buseo를 PL/SQL의 vbuseo로 집어넣는다.
    dbms_output.put_line(vbuseo); -- INTO 뒤의 buseo를 가져옴.
    
    INSERT INTO tblTodo
    VALUES ((SELECT max(seq) + 1 FROM tblTodo), vbuseo || '에 택배 보내기', sysdate, NULL);
END;
/
select * FROM tblTodo;


-- tblInsa
-- 성과급을 받는 직원(개발부 부장)은 누구인가?
CREATE TABLE tblBonus (
    name varchar(15)
);

-- 1. 개발부 부장을 SELECT하고 name을 가져온다.
-- 2. tblBonus에 name을 INSERT한다.

DECLARE
    vname varchar(15);
BEGIN
     SELECT name INTO vname FROM tblInsa WHERE buseo = '개발부' and jikwi = '부장'; -- 1
     INSERT INTO tblBonus (name) VALUES (vname);
END;
/

-- Subquery로 대신해보기
INSERT INTO tblBonus (name) VALUES ((SELECT name FROM tblInsa WHERE buseo = '개발부' and jikwi = '부장'));

SELECT * FROM tblBonus;

DECLARE
    vname varchar2(15);
    vbuseo varchar2(15);
    vjikwi varchar2(15);
    vbasicpay number;
BEGIN
    -- SELECT INTO절이 여러개의 변수값을 가지면?
    SELECT name, buseo, jikwi, basicpay INTO vname, vbuseo, vjikwi, vbasicpay FROM tblInsa WHERE num = 1001;
    
    dbms_output.put_line(vname);
    dbms_output.put_line(vbuseo);
    dbms_output.put_line(vjikwi);
    dbms_output.put_line(vbasicpay);
END;
/

/*
    타입 참조
    
    %type
    - 컬럼 참조
    - 사용하는 테이블의 특정 컬럼의 스키마를 알아내서 변수에 적용
    - 복사되는 정보
        a. 자료형
        b. 길이
        
        c. NOT NULL OR 제약사항은 복사할 수 없다.
        
    %rowtype
    - 행 참조
    - =모든 컬럼을 한꺼번에 참조
*/

DESC tblInsa;

DECLARE
    -- vbuseo varchar2(15);
    vbuseo tblInsa.buseo%type; -- 위와 결과가 같음
BEGIN
    SELECT buseo INTO vbuseo FROM tblInsal WHERE name = '홍길동';
    dbms_output.put_line(vbuseo);
END;
/

DECLARE
    vname       tblInsa.name%type;
    vbuseo      tblInsa.buseo%type;
    vjikwi      tblInsa.jikwi%type;
    vbasicpay   tblInsa.basicpay%type;
BEGIN
    SELECT name, buseo, jikwi, basicpay INTO vname, vbuseo, vjikwi, vbasicpay
    FROM tblInsa
    WHERE num = 1001;
    
    dbms_output.put_line(vname);
    dbms_output.put_line(vbuseo);
    dbms_output.put_line(vjikwi);
    dbms_output.put_line(vbasicpay);
END;
/

-- 서울 사는 영업부 부장한테 보너스를 지급하는 예제

DROP TABLE tblBonus;

CREATE TABLE tblBonus (
    seq number PRIMARY KEY,
    num number(5) NOT NULL REFERENCES tblInsa(num), -- 직원번호(FK)
    bonus number NOT NULL
);

CREATE Sequence seqBonus;


DECLARE
    vnum        tblInsa.num%type;
    vbasicpay   tblInsa.basicpay%type;
BEGIN
    SELECT
        num, basicpay INTO vnum, vbasicpay
    FROM tblInsa
    WHERE city = '서울' and jikwi = '부장' and buseo = '영업부';
    
    INSERT INTO tblBonus VALUES (seqBonus.nextVal, vnum, vbasicpay * 1.5);
END;
/

SELECT * FROM tblBonus;

--누가 뭘 받았는지 잘 모르겠으니 보너스 지급 내역 페이지를 만들어본다.
SELECT
    *
FROM tblInsa i
INNER JOIN tblBonus b
ON i.num = b.num;


SELECT * FROM tblMen;
SELECT * FROM tblWomen;

-- 무명씨가 성전환수술을 해서 tblMen에서 tblWomen으로 옮겨야 한다. -> 프로시저 1개 선언
-- 1. 무명씨의 정보를 tblMen에서 SELECT
-- 2. tblWomen에 INSERT
-- 3. tblMen에서 DELETE

-- %type 사용 버전
DECLARE
    vname   tblMen.name%type;
    vage    tblMen.age%type;
    vheight tblMen.height%type;
    vweight tblMen.weight%type;
    vcouple tblMen.couple%type;
BEGIN
    -- 1.
    SELECT name, age, height, weight, couple INTO vname, vage, vheight, vweight, vcouple
    FROM tblMen
    WHERE name = '하하하';
    
    --dbms_output.put_line(vname);
    --dbms_output.put_line(vage);
    
    -- 2.
    INSERT INTO tblWomen (name, age, height, weight, couple)
        VALUES (vname, vage, vheight, vweight, vcouple);
    -- 3.
    DELETE FROM tblMen WHERE name = vname;
END;
/

-- %rowtype 사용버전
DECLARE
    vrow tblMen%rowtype;
BEGIN
    -- 1.
    SELECT * INTO vrow
    FROM tblMen
    WHERE name = '홍길동';
    
    --dbms_output.put_line(vrow.name);
    --dbms_output.put_line(vrow.age);
    --dbms_output.put_line(vrow.height);
    
    -- 2.
    INSERT INTO tblWomen (name, age, height, weight, couple)
        VALUES (vrow.name, vrow.age, vrow.height, vrow.weight, vrow.couple);
    -- 3.
    DELETE FROM tblMen WHERE name = vrow.name;
END;
/

rollback;



-- PL/SQL의 if문
DECLARE
    vnum number := 10;
BEGIN
    if vnum > 0 then
        dbms_output.put_line('양수');
    elsif vnum < 0 then
        dbms_output.put_line('음수');
    else
        dbms_output.put_line('0');
    end if;
END;
/

-- tblInsa. 직원 검색(num) -> 남자냐 여자냐에 따라 다른 업무 진행

DECLARE
    vgender char(1);
BEGIN
    SELECT substr(ssn, 8, 1) INTO vgender FROM tblInsa WHERE num = 1001;
    
    IF vgender = '1' THEN
        dbms_output.put_line('남자업무..');
    ELSIF vgender = '2' THEN
        dbms_output.put_line('여자업무..');
    END IF;
    
END;
/


-- 직원 1명 선택 -> 보너스 차등 지급하는 procedure를 만들어볼 것
-- a. 과장/부장 -> basicpay * 1.5
-- b. 사원/대리 -> basicpay * 2

DROP TABLE tblBonus;

CREATE TABLE tblBonus (
    seq number PRIMARY KEY,
    num number(5) NOT NULL REFERENCES tblInsa(num), -- Foreign Key
    bonus number NOT NULL
);
CREATE Sequence bonus_seq;

DECLARE
    vnum        tblInsa.num%type;
    vbasicpay   tblInsa.basicpay%type;
    vjikwi      tblInsa.jikwi%type;
    vbonus      number;
BEGIN
    SELECT num, basicpay, jikwi INTO vnum, vbasicpay, vjikwi FROM tblInsa WHERE num = 1001;
    
    IF vjikwi in ('과장', '부장') THEN vbonus := vbasicpay * 1.5;
    ELSIF vjikwi in ('사원', '대리') THEN vbonus := vbasicpay * 2;
    END IF;
    
    INSERT INTO tblBonus VALUES (bonus_seq.nextVal, vnum, vbonus);
    
    SELECT num, basicpay, jikwi INTO vnum, vbasicpay, vjikwi FROM tblInsa WHERE num = 1005;
    
    IF vjikwi in ('과장', '부장') THEN vbonus := vbasicpay * 1.5;
    ELSIF vjikwi in ('사원', '대리') THEN vbonus := vbasicpay * 2;
    END IF;
    
    INSERT INTO tblBonus VALUES (bonus_seq.nextVal, vnum, vbonus);
END;
/

SELECT
    *
FROM tblInsa i
INNER JOIN tblBonus b
ON i.num = b.num;



/*
    PL/SQL에서의 case문
    - 표준SQL의 CASE-END와 동일하다.
    
*/

-- 대한민국의 대륙명을 AS 말고 아시아로 출력하라.
SELECT continent FROM tblCountry WHERE name = '대한민국';

DECLARE
    vcontinent tblCountry.continent%type;
    vresult varchar(30);
BEGIN
    SELECT continent INTO vcontinent FROM tblCountry WHERE name = '대한민국';
    
    -- case
    CASE
        WHEN vcontinent = 'AS' THEN vresult := '아시아';
        WHEN vcontinent = 'EU' THEN vresult := '유럽';
        WHEN vcontinent = 'AF' THEN vresult := '아프리카';
        ELSE vresult := '기타';
    END CASE;
    
    dbms_output.put_line(vresult);
    
    -- switch case
    CASE vcontinent
        WHEN 'AS' THEN vresult := '아시아';
        WHEN 'EU' THEN vresult := '유럽';
        WHEN 'AF' THEN vresult := '아프리카';
        ELSE vresult := '기타';
    END CASE;
    
    dbms_output.put_line(vresult);    
    
END;
/


/*
    PL/SQL에서의 반복문
    1. loop
        - 기본형
        - 단순 반복
        - EXIT WHEN 조건
        
    2. for loop
        - 1번 기반
        - 횟수 반복(자바 for)
    
    3. while loop
        - 1번 기반
        - 조건 반복(자바 while)
*/

BEGIN
    LOOP
        dbms_output.put_line('구현부');
    End LOOP;
END;
/


DECLARE
    vnum number := 1;
BEGIN
    LOOP
        dbms_output.put_line(vnum);
        vnum := vnum + 1;
        
        EXIT When vnum > 10;
    END LOOP;
END;
/



create table tblLoop (
    seq number primary key,
    data varchar2(100) not null
);

create sequence seqLoop;

-- tblLoop > 데이터 추가 > 항목001, 항목002, 항목003.. 항목999
declare
    vnum number := 1;
begin

    loop
        
        dbms_output.put_line(lpad(vnum, 3, '0'));
        
        insert into tblLoop 
            values (seqLoop.nextVal, '항목' || lpad(vnum, 3, '0'));
        
        vnum := vnum + 1;
        exit when vnum >= 1000;
        
    end loop;

end;
/

select * from tblLoop order by seq asc;







/*

    2. for loop
    
    for (int i=0; i<10; i++) {
    }
    +
    for (int n : list) {
    }
    for (int n in list) {
    }
    
    foreach(int n : list) {
    }

*/

begin
    
    for i in 1..10 loop
        dbms_output.put_line(i);
    end loop;
    
end;
/

-- ORA-02260: table can have only one primary key
create table tblGugudan (
    dan number not null,
    num number not null,
    result number not null,
    constraint tblgugudan_dan_num_pk primary key(dan, num) --복합키(Composite key)
);


begin
    for i in 2..9 loop
        for j in 1..9 loop
            insert into tblGugudan (dan, num, result)
                values (i, j, i * j);
        end loop;
    end loop;    
end;
/

select * from tblGugudan;

-- 복합키 > 관계 맺기

create table tblStudent (
    name varchar2(30) not null,
    subject varchar2(30) not null,
    tel varchar2(15) not null,
    constraint tblstudent_name_subject_pk primary key(name, subject)
);

create table tblScore (
    score number not null,
    name varchar2(30) not null,     --FK
    subject varchar2(30) not null,  --FK
    constraint tblscore_name_subject_fk 
        foreign key(name, subject) references tblStudent(name, subject)
);

insert into tblStudent (name, subject, tel)
    values ('홍길동', '자바', '010-1234-5678');

insert into tblStudent (name, subject, tel)
    values ('홍길동', '오라클', '010-1234-5678');
    
insert into tblStudent (name, subject, tel)
    values ('고양이', '자바', '010-1234-5678');    

insert into tblScore (score, name, subject)
    values (100, '홍길동', '자바');

insert into tblScore (score, name, subject)
    values (90, '홍길동', '오라클');

insert into tblScore (score, name, subject)
    values (80, '고양이', '자바');

insert into tblScore (score, name, subject)
    values (70, '고양이', '오라클');

select * from tblStudent;
select * from tblScore;

select * from tblStudent where name = '홍길동';
select * from tblStudent where name = '홍길동' and subject = '자바';

-- join
select * from tblStudent t
                inner join tblScore c
                    on t.name = c.name and t.subject = c.subject;
                    

BEGIN
    FOR i IN REVERSE 1..10 LOOP -- REVERSE를 붙이면 역순으로 돌아간다.
        dbms_output.put_line(i);
    END LOOP;
END;
/

/*
    3. WHILE LOOP
    
*/

DECLARE
    vnum number := 1;
BEGIN
    WHILE vnum <= 10 LOOP
        dbms_output.put_line(vnum);
        vnum := vnum + 1;
    END LOOP;
END;
/

/*
    SELECT -> 결과셋 -> PL/SQL 변수 대입
    
    1. SELECT INTO
    - 결과셋의 레코드가 1개일때만 사용 가능하다.
    - 결과가 0이면 에러가 발생한다.
        
    2. cursor + 루프
    - 결과셋의 레코드가 N개일 때 사용 가능하다. (0 ~ 무한)
    
    DECLARE
        변수 선언;
        커서 선언; -- 결과셋 참조 객체
    BEGIN
        커서 열기;
            LOOP
                커서 참조 -> 레코드 접근
                EXIT WHEN 조건
            END LOOP
        커서 닫기;
    END;
*/

-- 1. SELECT INTO

DECLARE
    vname tblInsa.name%type;
    vbasicpay tblInsa.basicpay%type;
    vnum number := 1100;
    vcnt number;
BEGIN
    SELECT count(*) INTO vcnt FROM tblInsa WHERE num = vnum;
    
    IF vcnt > 0 THEN
        SELECT name, basicpay INTO vname, vbasicpay
        FROM tblInsa
        WHERE num = vnum;
    END IF;
    
    dbms_output.put_line('이름: ' || vname);
    dbms_output.put_line('급여 ' || vbasicpay);
END;
/

create view vwTest
as
select name from tblInsa;

cursor vcursor 
is 
select name from tblInsa;

-- tblInsa의 직원명을 가져오시오.(60명)
DECLARE
    -- cursor 커서명 IS SELECT문
   cursor vcursor IS SELECT name FROM tblInsa; 
   vname tblInsa.name%type;
BEGIN
    -- cursor 사용
    open vcursor; -- cursor 열기 -> SELECT문 실행
        
        -- FETCH 커서 INTO 변수
        -- SELECT 컬럼 INTO 변수
--        FETCH vcursor INTO vname;
--        dbms_output.put_line(vname);
--        
--        FETCH vcursor INTO vname;
--        dbms_output.put_line(vname);

        LOOP
            FETCH vcursor INTO vname;
            EXIT when vcursor%notfound; -- cursor가 데이터를 더이상 찾지 못할 때
            dbms_output.put_line(vname);
        END LOOP;
    close vcursor;
END;
/


-- '기획부'에서 이름, 직위, 급여를 출력하시오.

DECLARE
    cursor vcursor
    IS
    SELECT name, jikwi, basicpay FROM tblInsa WHERE buseo = '기획부';
    vname tblInsa.name%type;
    vjikwi tblInsa.jikwi%type;
    vbasicpay tblInsa.basicpay%type;
BEGIN
    open vcursor;
    LOOP
        FETCH vcursor INTO vname, vjikwi, vbasicpay;
        EXIT when vcursor%notfound;
        dbms_output.put_line(vname || ',' || vjikwi || ',' || vbasicpay); -- 1회전마다 기획부 직원 하나의 이름, 직위, 급여 출력
    END LOOP;
    close vcursor;
END;
/

/*
    문제. tblBonus 커서만들고 루프돌려서 루프 안에서 insert
    - 60명 직원 전원에게 보너스 지급
    - 과장/부장 -> basicpay * 1.5
    - 사원/대리 -> basicpay * 2
*/

DROP TABLE tblBonus;

CREATE TABLE tblBonus ( -- 보너스가 들어갈 tblBonus 테이블 생성
    seq number PRIMARY KEY,
    num number(5) NOT NULL REFERENCES tblInsa(num), -- Foreign Key
    bonus number NOT NULL
);
CREATE Sequence bonus_seq; -- seq에 들어갈 일련번호 생성용

DECLARE
    cursor vcursor
    IS
    SELECT num, jikwi, basicpay FROM tblInsa;
    
    vnum tblInsa.num%type;
    vjikwi tblInsa.jikwi%type;
    vbasicpay tblInsa.basicpay%type;
    vbonus number;
BEGIN
    open vcursor;
    LOOP
        FETCH vcursor INTO vnum, vjikwi, vbasicpay;
        EXIT when vcursor%notfound;
        
        IF vjikwi in ('과장', '부장') then vbonus := vbasicpay * 1.5;
        ELSIF vjikwi in ('사원', '대리') then vbonus := vbasicpay * 2;
        END IF;
        
        INSERT INTO tblBonus VALUES (bonus_seq.nextVal, vnum, vbonus);
    END LOOP;
    close vcursor;
END;
/

SELECT * FROM tblBonus;



-- Cursor 탐색
-- 1. 커서 + loop -> 기본형
-- 2. 커서 + for loop -> 간단 버전

-- 직원 60명의 정보를 전부 출력하라.
-- 1. Cursor + LOOP
DECLARE
    Cursor vcursor IS SELECT * FROM tblInsa;
    vrow tblInsa%rowtype;
BEGIN
    OPEN vcursor;
    LOOP
        FETCH vcursor INTO vrow;
        EXIT when vcursor%notfound;
        dbms_output.put_line(vrow.name || ',' || vrow.buseo);
    END LOOP;
    CLOSE vcursor;
END;
/

-- 2. Cursor + FOR LOOP
DECLARE
    cursor vcursor IS SELECT * FROM tblInsa;
BEGIN
    FOR vrow IN vcursor LOOP
        dbms_output.put_line(vrow.name || ',' || vrow.buseo);
    END LOOP;
END;
/


-- 예외 처리, Exception

-- ORA-01403: no data found
-- ORA-01476: divisor is equal to zero
-- 두 오류를 의도적으로 내고 예외처리를 하는 예제
-- https://docs.oracle.com/cd/E11882_01/timesten.112/e21639/exceptions.htm#TTPLS196 
-- SELECT name 줄의 num을 1234로 바꿈


DECLARE

    vname tblInsa.name%type;
    vcnt number;
    
BEGIN

    dbms_output.put_line(111);
    SELECT name INTO vname FROM tblInsa WHERE num = 1001;
    dbms_output.put_line(222);
    dbms_output.put_line(vname);
    dbms_output.put_line(333);
    
    SELECT Count(*) INTO vcnt FROM tblInsa WHERE buseo = '기획부';
    dbms_output.put_line(10000000 / vcnt);
    
    vcnt := '문자열';
    
EXCEPTION
    When NO_DATA_FOUND Then dbms_output.put_line('데이터가 없습니다.');
    When ZERO_DIVIDE Then dbms_output.put_line('0으로 나누려고 시도했습니다.');
    when OTHERS then dbms_output.put_line('예외처리'); 
    
END;
/


/*
    프로시저
    1. 익명 프로시저
    2. 실명 프로시저
    
    저장(실명) 프로시저
    - Stored Procedure
    
    1. 저장 프로시저, Stored Procedure
        - 매개변수, 반환값 전부 구성이 자유롭다.
    
    2. 저장 함수, Stored Function
        - 매개변수와 반환값 둘 다 필수
        
    하는 일은 비슷하지만 용도가 다르다.
    
    
    -- 복습 --
    익명 프로시저 선언법
    [DECLARE
        변수 선언;
        커서 선언;]
    BEGIN
        구현부;
    [EXCEPTION
        예외처리;]
    END
    
    
    저장 프로시저 선언법
    
    CREATE [OR REPLACE] PROCEDURE 프로시저명 -- PROCEDURE에는 TABLE, SEQUENCE 등...이 들어간다.
    IS(또는 AS)
    [   변수 선언;
        Cursor 선언;]
    BEGIN
        구현부
    [EXCEPTION
        예외처리;]
    END
    
    
    
    [SQL 명령어가 실행되는 과정]
    1. 직접 SQL 실행 -> 표준SQL
    2. 익명 프로시저 실행
    3. 저장 프로시저 실행
    
    1, 2
        a. 클라이언트에 구문을 작성한다.(Coding)
        b. 영역 선택 후 CTRL + ENTER로 텍스트를 Oracle 서버에 전송한다.
            - 이 단계는 SQL 실행이라고 부르기 애매하다.
        c. Oracle 서버가 b단계에서 전송된 SQL을 수신한다.
        d. 수신한 SQL을 parsing한다.(구문을 분석하고 쪼갠다. -> 문법검사를 한다.)
        e. SQL을 컴파일한다.(좀 더 정확하게 말하면 산출물이 생기지 않는 Interpreter 작업임.)
        f. SQL을 실행한다.
        g. 결과셋을 생성한다.(서버 메모리에)
        h. 결과셋을 클라이언트에게 반환한다.
        i. 반환받은 결과셋을 클라이언트 메모리에 로드한다.
            - SQL Developer는 결과셋을 화면에 출력한다.
        
        - 동일한 SQL을 반복해도 비용이 동일하다.
    
        
    3
        a. 클라이언트에 구문을 작성한다.(Coding)
        b. 영역 선택 후 CTRL + ENTER로 텍스트를 Oracle 서버에 전송한다.
            - 이 단계는 SQL 실행이라고 부르기 애매하다.
        c. Oracle 서버가 b단계에서 전송된 SQL을 수신한다.
        d. 수신한 SQL을 parsing한다.(구문을 분석하고 쪼갠다. -> 문법검사를 한다.)
        e. SQL을 컴파일한다.(좀 더 정확하게 말하면 산출물이 생기지 않는 Interpreter 작업임.)
        f. SQL을 실행한다.
        g. 프로시저를 생성한다. -> (***) 서버에 객체로 저장한다. (***)
        h. 성공/실패 메시지를 클라이언트에 반환한다.
        i. 클라이언트는 메시지를 화면에 출력한다.
        
        a. 클라이언트에 구문을 작성한다.(Coding)
        b. 영역 선택 후 CTRL + ENTER로 텍스트를 Oracle 서버에 전송한다.
            - 이 단계는 SQL 실행이라고 부르기 애매하다.
        c. Oracle 서버가 b단계에서 전송된 SQL을 수신한다.
        d. 수신한 SQL을 parsing한다.(구문을 분석하고 쪼갠다. -> 문법검사를 한다.)
        e. SQL을 컴파일한다.(좀 더 정확하게 말하면 산출물이 생기지 않는 Interpreter 작업임.)
        f. SQL을 실행한다.
        g. 저장된 프로시저를 호출한다.
            - (***) 프로시저 안의 구문들은 이미 컴파일이 되어있다. (***)
            - 이미 컴파일된 프로시저를 호출한다.
        i. 결과를 클라이언트에게 반환한다.
        j. 클라이언트는 결과를 처리한다.
        
        - 동일한 프로시저를 호출하면 비용이 절감된다.
            1. 프로시저 생산 비용(+컴파일)은 비싸다.
            2. 대신 프로시저 호출 비용이 매우 싸다. -> 반복해야 하는 작업에 쓰면 효율이 좋다.
        - 저장된 객체는 관리비용이 발생한다. -> 사람이 관리해야 한다. (권한 or 네이밍을 지정해줘야 됨.)

*/

SET SERVEROUTPUT ON;

-- [SQL 명령어가 실행되는 과정]
-- 1. 직접 SQL 실행 -> 표준SQL
SELECT * FROM tblInsa;

-- 2. 익명 프로시저 실행
DECLARE
    vnum number;
BEGIN
    vnum := 10;
    dbms_output.put_line(vnum);
END;
/

-- 3. 저장 프로시저 실행
-- 프로시저를 생성했다는 것 -> Oracle 객체로 저장했다는 뜻 -> 호출해서 사용 가능
CREATE OR REPLACE Procedure procTest
IS
    vnum number;
BEGIN
    vnum := 10;
    dbms_output.put_line(vnum);
END procTest; -- END 뒤에 프로시저 이름을 적어주는 것이 좋다. 구문이 길어졌을 때 확인용.
/

-- 프로시저 호출(PL/SQL)
BEGIN
    procTest();
    procTest; -- 매개변수가 없으면 이렇게 쓰는 경우가 더 많다.
END;
/

-- 표준SQL 영역에서 프로시저를 호출하는 법
EXECUTE procTest;
EXEC procTest; -- 나중에 Java에서 Oracle 프로시저를 호출할 때 사용
CALL procTest(); -- CALL은 ()를 붙여야 함.


--1. 매개변수가 있는 프로시저
CREATE OR REPLACE Procedure procTest(pnum number) -- parameter의 첫글자를 앞에 붙여서...
IS
    vnum number; -- 일반 변수
BEGIN
    vnum := pnum * 2;
    dbms_output.put_line(vnum);
END procTest;
/

BEGIN
    procTest(10);
    procTest(20);
    procTest(30);
END;
/

--2. 매개변수가 2개인 프로시저
CREATE OR REPLACE Procedure procTest(
    pwidth number,
    pheight number
)
IS
    varea number;
BEGIN
    varea := pwidth * pheight;
    dbms_output.put_line(varea);
END procTest;
/

BEGIN
    procTest(10, 20);
END;
/

-- Java와 다른 SQL 프로시저의 특성
-- 1. 프로시저의 매개변수 만들 때: 길이 표현(X) /  NOT NULL표현(X) / DEFAULT(O)
-- 2. 일반변수가 없을 때 IS(AS)가 생략되지 않는다.
CREATE OR REPLACE Procedure procTest(
    pname varchar2 -- varchar2(30) 이런 식으로 길이를 지정하면 오류 난다.
)
IS -- 일반변수가 없어도 생략하면 안 된다.
BEGIN
    dbms_output.put_line(pname || '님 안녕하세요.');
END procTest;
/

-- 'DEFAULT는 된다'의 예시
CREATE OR REPLACE Procedure procTest(
    pwidth number,
    pheight number DEFAULT 10 -- 단, DEFAULT는 무조건 인자리스트의 끝에 와야 한다. 안 그러면 변수가 여러개일 때 뭘 DEFAULT로 빼려는지 모르니까.
)
IS
    varea number;
BEGIN
    varea := pwidth * pheight;
    dbms_output.put_line(varea);
END procTest;
/

BEGIN
    procTest(10, 20);
END;
/

BEGIN
    procTest(10); -- procTest(10, 10);이랑 같다.
END;
/


/*
    매개변수 모드
    - 매개변수가 값을 전달하는 방식
    - Call by Value -> 값을 전달(값형)
    - Call by Reference -> 주소값을 전달(주소형)
    
    1. in -> 기본 모드
    2. out
    3. in out -> 잘 안 씀
*/

-- in 모드 매개변수
CREATE OR REPLACE Procedure procTest(
    pnum1 number,       -- in parameter(값을 전달) -> 인자값 역할
    pnum2 IN number,
    pnum3 OUT number,    -- out parameter(주소값을 전달) -> 반환값 역할
    pnum4 OUT number,
    pnum5 OUT number
)
IS
BEGIN
    pnum3 := pnum1 + pnum2; -- pnum3의 조작 = vresult의 조작 (같은 주소값 참조)
    pnum4 := pnum1 * pnum2;
    pnum5 := pnum1 - pnum2;
END procTest;
/

DECLARE
    vresult1 number;
    vresult2 number;
    vresult3 number;
BEGIN
    procTest(10, 20, vresult1, vresult2, vresult3); -- vresult는 NULL을 전달한 게 아니라 자신의 주소값을 전달한 것.
    dbms_output.put_line(vresult1);
    dbms_output.put_line(vresult2);
    dbms_output.put_line(vresult3);
END;
/

/*
    문제.
    1. procTest1
        - 부서(인자 1개)를 전달하면 -> in
        - 해당 부서의 직원 중 급여를 가장 많이 받는 직원의 num 반환 -> out -> 호출 -> 번호 출력
    
    2. procTest2
        - 직원 번호 전달받아서 -> in
        - 같은 지역에 사는 직원 수는? -> out
        - 같은 직위를 가지는 직원 수는? -> out
        - 해당 직원보다 급여를 더 많이 받는 직원 수는? -> out
        -> 호출
*/

-- 1.
CREATE OR REPLACE Procedure procTest1 (
    pbuseo IN varchar2,
    pnum OUT number
)
IS
BEGIN
    SELECT num INTO pnum
    FROM tblInsa
    WHERE buseo = pbuseo and basicpay = (SELECT max(basicpay) FROM tblInsa WHERE buseo = pbuseo);
    
END procTest1;
/


DECLARE
    vnum number;
BEGIN
    procTest1('기획부', vnum);
    dbms_output.put_line(vnum);
END;
/

-- 2.
CREATE OR REPLACE Procedure procTest2(
    pnum number, -- 직원번호
    pcnt1 OUT number,
    pcnt2 OUT number,
    pcnt3 OUT number
)
IS
BEGIN
    SELECT Count(*) - 1 INTO pcnt1
    FROM tblInsa
    WHERE city = (SELECT city FROM tblInsa WHERE num = pnum);
    
    SELECT Count(*) - 1 INTO pcnt2
    FROM tblInsa
    WHERE jikwi = (SELECT jikwi FROM tblInsa WHERE num = pnum);
    
    SELECT Count(*) INTO pcnt3
    FROM tblInsa
    WHERE basicpay > (SELECT basicpay FROM tblInsa WHERE num = pnum);
    
END procTest2;
/

DECLARE
    vcnt1 number;
    vcnt2 number;
    vcnt3 number;
BEGIN
    procTest2(1001, vcnt1, vcnt2, vcnt3);
    dbms_output.put_line(vcnt1);
    dbms_output.put_line(vcnt2);
    dbms_output.put_line(vcnt3);
END;
/


SELECT * FROM tblStaff;
SELECT * FROM tblProject;
-- 직원 퇴사 프로시저, procDeleteStaff
-- 1. 퇴사 직원의 담당프로젝트 유무 확인
-- 2. 담당프로젝트가 있으면 타인에게 위임
-- 3. 퇴사 직원 삭제

CREATE OR REPLACE Procedure procDeleteStaff(
    pseq    IN number, -- 퇴사할 직원번호를 받아옴
    pstaff  IN number, -- 위임받을 직원번호를 받아옴
    presult OUT number -- 결과 -> 성공(1), 실패(0)
)
IS
    vcnt number; -- 퇴사할 직원이 현재 담당 중인 프로젝트 개수
    vcnt2 number;
BEGIN
    -- 0. 퇴사 직원이 존재하는지? 1 or 0
    SELECT Count(*) INTO vcnt2 FROM tblStaff WHERE seq = pseq;
    
    IF vcnt2 = 1 THEN  
        -- 1. 퇴사 직원의 담당프로젝트 갯수 확인 : Count(*)
        SELECT Count(*) INTO vcnt FROM tblProject WHERE staff_seq = pseq;
    
        -- 조건 분기
        IF vcnt > 0 THEN
            -- 2. 위임
            UPDATE tblProject SET staff_seq = pstaff WHERE staff_seq = pseq;
        ELSE
            -- 3. 딱히 할 거 없음
            NULL; -- 반드시 비워두세요. 라는 표현
        END IF;
    
        -- 4. 퇴사처리
        DELETE FROM tblStaff WHERE seq = pseq;
    
        -- 5. 성공
        presult := 1;
    ELSE
        -- 직원이 없으면
        presult := 2;
    END IF;
EXCEPTION
    -- 5. 실패
    WHEN others THEN
        presult := 0;
END procDeleteStaff;
/

DECLARE
    vresult number;
BEGIN
    procDeleteStaff(1, 2, vresult);
    
    IF vresult = 1 THEN
        dbms_output.put_line('퇴사 성공');
    ELSIF vresult = 2 THEN
        dbms_output.put_line('직원 없음');
    ELSE
        dbms_output.put_line('퇴사 실패');
    END IF;
END;
/

SELECT * FROM tblStaff;
SELECT * FROM tblProject;

/*
    저장 프로시저
    
*/



-- num1 + num2 의 결과를 반환하려고 한다.

-- 프로시저로 코딩
CREATE OR REPLACE Procedure procSum(
    pnum1 IN number,
    pnum2 IN number,
    presult OUT number
)
IS
BEGIN
    presult := pnum1 + pnum2;
END procSum;
/

-- 함수로 코딩
-- OUT parameter를 적용할 수 있지만 RETURN을 쓰기 때문에 절대 사용하지 말 것.
CREATE OR REPLACE Function fnSum(
    fnum1 IN number,
    fnum2 IN number
) RETURN number -- number형을 반환한다는 뜻으로 지정
IS
BEGIN
    RETURN fnum1 + fnum2;
END fnSum;
/

DECLARE
    vresult number;
BEGIN
    procSum(10, 20, vresult);
    dbms_output.put_line(vresult);
    
    vresult := fnSum(30, 40);
    dbms_output.put_line(vresult);
END;
/

-- 프로시저와 함수의 사용 차이
-- 1. 프로시저는 PL/SQL용 메서드이다.
-- 2. 함수는 표준SQL용 메서드이다.

SELECT
    name, population, area, population + area,
    --procSum(population, area, PL/SQL 변수)
    fnSum(population, area)
FROM tblCountry;

-- 1. name, buseo, jikwi에 더해서 남녀정보를 가져오고 싶다.
SELECT
    name, buseo, jikwi
    case
        when substr(ssn, 8, 1) = 1 then '남자'
        when substr(ssn, 8, 1) = 2 then '여자'
    end as gender
FROM tblInsa;

-- 2. case-end 부분이 여러군데 쓰일 거라고 가정하고 함수로 만들어보자.
CREATE OR REPLACE Function fnGender(
    pssn varchar2
) RETURN varchar2
IS
BEGIN
    RETURN case
            when substr(pssn, 8, 1) = 1 then '남자'
            when substr(pssn, 8, 1) = 2 then '여자'
           end;
END fnGender;
/

-- 3. SELECT문 안에 case-end 대신 위에서 만든 함수를 넣어보자.
SELECT
    name, buseo, jikwi, fnGender(ssn) as gender
FROM tblInsa;


/*
    저장 프로시저
    
    1. 프로시저
    2. 함수
    3. 트리거
    
    트리거, Trigger
    - 프로시저의 한 종류
    - 개발자의 호출이 아닌, 미리 지정한 특정 사건이 발생하면 시스템이 자동으로 호출하는 프로시저
    
    트리거의 구문
    
    CREATE [OR REPLACE] TRIGGER 트리거명
        BEFORE|AFTER
        INSERT|UPDATE|DELETE
        ON 테이블명
        [FOR EACH ROW]
    DECLARE
    [   변수 선언;
        커서 선언;]
    BEGIN
        구현부;
    [EXCEPTION
        예외처리;]
    END
*/

-- tblInsa를 예로 들면... 트리거는 생성과 동시에 실행되어 tblInsa를 감시한다. 트리거를 호출한 직후 DELETE가 발생한다.

-- tblInsa에서 직원을 삭제.
CREATE OR REPLACE Trigger trgInsa
    BEFORE       -- BEFORE면 프로시저 호출 직전에, AFTER면 프로시저 호출 직후에 발생.
    DELETE       -- 삭제가 발생하는지?
    ON tblInsa  -- tblInsa를 대상으로 감시. 발생하면 BEGIN 실행
BEGIN
    dbms_output.put_line(to_char(sysdate, 'hh24:mi:ss') || ' 트리거 실행');
    
    -- 월요일에는 퇴사 불가능
    IF to_char(sysdate, 'dy') = '월' Then
        -- 강제로 에러 발생
        -- -20000 ~ -29999 (사용자 정의 에러 구간)
        raise_application_error(-20001, '월요일에는 퇴사가 불가능합니다.');
END trgInsa;
/

SELECT * FROM tblInsa;
DELETE FROM tblInsa WHERE num = 1001;

SELECT * FROM tblBonus;
DELETE FROM tblBonus;

ROLLBACK;

select * from tblInsa;
delete from tblInsa where num = 1002; -- child record found > ERD

select * from tblBonus;
delete from tblBonus;

-- 1. SQL
SELECT A.TABLE_NAME
FROM all_cons_columns a
JOIN all_constraints c ON a.owner = c.owner
AND a.constraint_name=c.constraint_name
JOIN all_constraints c_pk ON c.r_owner = c_pk.owner
AND c.r_constraint_name = c_pk.constraint_name
WHERE c.constraint_type ='R'
AND c_pk.table_name ='TBLINSA'
GROUP BY A.TABLE_NAME
ORDER BY A.TABLE_NAME;

-- 2. ERD 툴 > eXERD
-- 순공학, Forward Engineering
-- 역공학, Reverse Engineering
select * from tabs;

-- 트리거 확인
SELECT * FROM user_triggers WHERE table_name = 'TBLINSA'; -- STATUS가 ENABLED면 Trigger 동작중이라는 뜻.

-- 트리거 중지
ALTER Trigger trgInsa DISABLE; -- ENABLED -> DISABLED

-- 트리거 시작
ALTER Trigger trgInsa ENABLE;


-- 로그 기록
-- tblTodo를 감시해서 어떤 일이 발생했는지?
CREATE TABLE tblLog(
    seq number PRIMARY KEY,                 -- PK
    message varchar2(1000) NOT NULL,        -- 메시지
    regdate date DEFAULT sysdate NOT NULL   -- 발생 시각
);
CREATE Sequence seqLog;

CREATE OR REPLACE Trigger trgTodo
    AFTER
    INSERT OR UPDATE OR DELETE
    ON tblTodo
DECLARE
    vmessage varchar2(1000);
BEGIN
    dbms_output.put_line('trgTodo 호출됨');
    
    -- 3개의 사건이 일어날 때 전부 'trgTodo 호출됨'이 호출된다. (무슨 일인지 구분을 못 함)
    -- 그래서 아래처럼 구분해줌
    If inserting Then
        vmessage := '새로운 할 일이 추가되었습니다.';
    Elsif updating Then
        vmessage := '기존 할 일이 수정되었습니다.';
    Elsif Deleting Then
        vmessage := '기존 할 일이 삭제되었습니다.';
    End if;
    
    dbms_output.put_line(vmessage);
    
    INSERT INTO tblLog(seq, message, regdate) VALUES(seqlog.nextVal, vmessage, DEFAULT);
    
END trgTodo;
/
SELECT * FROM tblTodo;
INSERT INTO tblTodo VALUES(22, '새로운 할일', sysdate, NULL);
UPDATE tblTodo SET title = '새로운 할일입니다.' WHERE seq = 22;
DELETE FROM tblTodo WHERE seq = 22;

SELECT * FROM tblLog;


/*
    [FOR EACH ROW]
    
    1. 생략
        - 문장(Query) 단위 트리거
        - Table Level Trigger
        
    2. 사용
        - 행(Record) 단위 트리거
        
*/

CREATE OR REPLACE Trigger trgMen
    BEFORE
    UPDATE
    ON tblMen
    FOR EACH ROW
BEGIN
    dbms_output.put_line('레코드 수정: ' || :old.name); -- old. -> 행단위 트리거에서만 쓸 수 있는 키워드. 사건의 적용 전 레코드값을 보여줘. 라는 뜻
    dbms_output.put_line('수정 전 나이: ' || :old.age);
    dbms_output.put_line('수정 후 나이: ' || :new.age); -- new. -> 행단위 트리거에서만 쓸 수 있는 키워드. 사건의 적용 후 레코드값을 보여줘. 라는 뜻
    dbms_output.put_line('전 여친: ' || :old.couple);
    dbms_output.put_line('현 여친: ' || :new.couple);
END trgMen;
/


SELECT * FROM tblMen;

DELETE FROM tblMen WHERE name = '조세호'; -- FOR EACH ROW가 적용되면 메시지가 1번 출력
DELETE FROM tblMen; -- FOR EACH ROW가 적용되면 메시지가 9번(tblMen의 모든 튜플) 출력

UPDATE tblMen SET age = 30 WHERE name = '홍길동';

UPDATE tblMen SET couple = '박나래' WHERE name = '홍길동';

-- INSERT -> :old(X), :new(O) -> 새로 집어넣는 거니까 옛날 상태라는 게 없음.
-- UPDATE -> :old(O), :new(O) -> 수정이니까 옛날, 현재 상태 전부 존재할 수 있음.
-- DELETE -> :old(O), :new(X) -> 삭제니까 현재 상태라는 건 없음. 데이터가 없지.

-- 회원 테이블, 게시판 테이블
-- 포인트 정책
-- 1. 글 작성 -> 포인트 + 100
-- 2. 글 삭제 -> 포인트 - 50

CREATE TABLE tblUser (
    id varchar2(30) PRIMARY KEY,
    point number NOT NULL
);

CREATE TABLE tblBoard (
    seq number PRIMARY KEY,
    subject varchar2(1000) NOT NULL,
    id varchar2(30) NOT NULL REFERENCES tbluser(id)
);
CREATE Sequence seqBoard;

DROP TABLE tblUser;
DROP TABLE tblBoard;
DROP Sequence seqBoard;

INSERT INTO tblUser VALUES ('hong', 1000);

-- 절차
-- 1. 글을 쓴다(or 삭제한다.)
-- 2. 포인트를 누적한다.(or 차감한다.)

-- Case 1. 하드 코딩

-- 1-1. 글쓰기
INSERT INTO tblBoard VALUES (seqBoard.nextVal, '게시판입니다.', 'hong');

SELECT * FROM tblUser;
SELECT * FROM tblBoard;

-- 1-2. 포인트 누적하기
UPDATE tblUser SET point = point + 100 WHERE id = 'hong';

-- 1-3. 글삭제
DELETE FROM tblBoard WHERE seq = 1;

-- 1-4. 포인트 차감하기
UPDATE tblUser SET point = point - 50 WHERE id = 'hong';

SELECT * FROM tblUser;
SELECT * FROM tblBoard;


-- Case 2. 프로시저 (선생님 개인적으로 추천)
-- 장점: 전체 업무가 단위화됨 -> 간단하고 가독성 높고 재사용성 높음.
-- 단점: 초기 비용이 높음
CREATE OR REPLACE Procedure procAddBoard(
    pid varchar2, -- 회원번호
    psubject varchar2 -- 글 제목
)
IS
BEGIN
    -- 2-1. 글쓰기
    INSERT INTO tblBoard VALUES (seqBoard.nextVal, psubject, pid);
    
    -- 2-2. 포인트 누적하기
    UPDATE tblUser SET point = point + 100 WHERE id = pid;
END procAddBoard;
/

BEGIN
    procAddBoard('hong', '안녕하세요.');
END;
/

CREATE OR REPLACE procedure procDeleteBoard(
    pseq number -- 글 번호
)
IS
    -- 2-4-1. 포인트 차감을 위해 사용자 id를 받아올 변수 선언
    vid tblUser.id%type;
BEGIN
    -- 2-4-2. 포인트 차감할 사람의 id를 미리 찾기
    SELECT id INTO vid FROM tblBoard WHERE seq = pseq; 

    -- 2-3. 글 삭제
    DELETE FROM tblBoard WHERE seq = pseq;
    
    -- 2-4-3. 포인트 차감하기
    UPDATE tblUser SET point = point - 50 WHERE id = vid;
END procDeleteBoard;
/

BEGIN
    procDeleteBoard(2);
END;
/

SELECT * FROM tblUser;
SELECT * FROM tblBoard;


-- Case 3. 트리거
CREATE OR REPLACE Trigger trgBoard
    AFTER
    INSERT OR DELETE
    ON tblBoard
    FOR EACH ROW -- 발생한 사건의 레코드(컬럼)값을 접근하기 위함
BEGIN
    If Inserting Then
        UPDATE tblUser SET point = point + 100 WHERE id = :new.id;
    Elsif Deleting Then
        UPDATE tblUser SET point = point - 50 WHERE id = :old.id;
    End if;
END trgBoard;
/
-- 3-1.
INSERT INTO tblBoard VALUES (seqBoard.nextVal, '게시판입니다', 'hong');

-- 3-2.
DELETE FROM tblBoard WHERE seq = 3;

SELECT * FROM tblUser; -- 1150 -> 1100 -> 1200 -> 1150
SELECT * FROM tblBoard;



-- 프로시저 OUT Parameter 얘기. -> 반환값
-- 1. 원자값(1행 1열) -> SELECT Count(*) FROM tblInsa;
-- 2. 레코드 하나 전체(컬럼 여러개) -> 1번 x N회
-- 3. 레코드 N개 -> ? -> Cursor 사용

-- 1.
CREATE OR REPLACE Procedure procTest(
    pcnt OUT number,
    prow OUT tblInsa%rowtype,
    pcursor OUT sys_refcursor -- 매개변수로 쓸 때의 커서 타입
)
IS
BEGIN
    -- 1. 1행 1열
    SELECT Count(*) INTO pcnt FROM tblInsa;
    
    -- 2. 1행 N열
    SELECT * INTO prow FROM tblInsa WHERE num = 1010;
    
    -- 3. N행 N열 -> 열려진 Cursor를 반환한다. -> 탐색만 하면 된다.
    OPEN pcursor -- cursor를 OUT parameter 방식으로 반환값을 돌려주는 방법.
    FOR
    SELECT * FROM tblInsa; -- 원자값을 돌려주는 RETURN은 사용 불가.
END procTest;
/

DECLARE
    vcnt number;
    vrow tblInsa%rowtype;
    vcursor sys_refcursor;
BEGIN
    procTest(vcnt, vrow, vcursor);
    dbms_output.put_line(vcnt);
    dbms_output.put_line(vrow.name || ',' || vrow.buseo);
    
    LOOP
        FETCH vcursor INTO vrow; -- 인사테이블의 튜플을 담을 수 있는 변수 재사용.
        EXIT When vcursor%notfound;
        
        dbms_output.put_line(vrow.name || ',' || vrow.buseo);
    END LOOP;
END;
/



-- 계정
-- 트랜잭션
-- 인덱스

-- 오라클 -> 모든 자원을 계정 별로 관리. -> 우리는 지금 수업용으로 HR 계정을 사용하고 있다.

SHOW USER; -- SQL*Plus 전용 명령어. DBear, DataGrid 등의 타 DB에서는 안 먹힘.
DESC tblInsa; -- SQL*Plus 전용 명령어.

SELECT * FROM tblInsa; -- HR이 아닌 SYSTEM에서는 볼 수 없음.
SELECT * FROM hr.tblInsa; -- 이러면 볼 수 있음. 단, 권한이 있을 때만.

-- 프로젝트 할 때 만들 테이블들이 수업내용에 썼던 테이블들이랑 이름이 겹칠 수 있으니까 계정을 새로 만들어라.

CREATE USER 계정명 IDENTIFIED BY 암호;
CREATE USER hong IDENTIFIED BY java1234;

GRANT 권한, 권한, 권한 TO 유저명;
GRANT CREATE SESSION TO hong;
GRANT CONNECT TO hong;
GRANT CREATE TABLE TO hong;

GRANT CONNECT, RESOURCE, CREATE VIEW TO hong; -- HR과 권한이 유사해짐.
GRANT DBA TO hong; -- SYSTEM 바로 밑급