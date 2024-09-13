/*
    ex02_dataType.sql
    
    관계형 데이터베이스
    - 변수가 없는 프로그래밍 언어
    - SQL은 대화형 언어로, DB와 대화를 목적으로 하는 언어
    - 데이터를 조작하려면 자료형 사용 / 테이블 정의에는 컬럼의 자료형
    
    표준 SQL 자료형(=Oracle 자료형)
    1. 숫자형
        - 정수, 실수
        a. number
            - 유효자리(40자리) 이하의 숫자를 표현하는 자료형
            - 범위 밖은 0으로 저장
            
            - number: 정수 or 실수
            - number(precision): 정수만 저장
            - number(precision, scale): 실수범위까지 저장
            
    
    2. 문자형 (주로 varchar2 사용)
        - 문자, 문자열
        - n의 의미가 무엇인가? : UTF-8이냐 UTF-16이냐
            - char VS nchar
            - varchar2 VS nvarchar2
        - var의 의미가 무엇인가? : 공간길이가 고정이냐 가변형이냐
            - char VS varchar
            - nchar VS nvarchar2
        
        a. char
            - 고정 자릿수 문자열, 컬럼의 크기가 불변
            - char(n): 최대 n자리 문자열, n(바이트)까지 넣을 수 있음
                - char(n byte)
                - 이 때 n의 크기는 1바이트 ~ 2000바이트
            - char(n char): n(문자수) -> UTF-8 형식으로 데이터 저장
            
        b. nchar
            - national language -> Oracle 인코딩 옵션과 상관 없이 해당 컬럼은 UTF-16으로 저장됨
            - 고정 자릿수 문자열, 컬럼의 크기가 불변
            - nchar(n): 최대 n자리 문자열, n(문자수)
                -최소 크기: 1자
                -최대 크기: 1000자
        
        c. varchar2
            - variable character
            - 바차투 라고 읽더라...
            - 가변 자릿수 문자열
            - varchar(n) : 최대 n자리 문자형, n바이트 크기
            - 이 때 n의 크기는 1바이트 ~ 4000 바이트
        d. nvarchar2
            - national language -> Oracle 인코딩 옵션과 상관 없이 해당 컬럼은 UTF-16으로 저장됨
            - 가변 자릿수 문자열
            - nvarchar2(n): 최대 n자리 문자열, n(문자수)
                -최소 크기: 1자
                -최대 크기: 2000자
        
        e. clob
            - character large object
            - 대용량 텍스트 저장용 오브젝트 (4GB까지)
            - 참조형 (속도가 상대적으로 느림)
            
        f. nclob
            - national character large object
            - 대용량 텍스트 저장용 오브젝트 (4GB까지)
            - 참조형 (속도가 상대적으로 느림)
    
    
    3. 날짜/시간형 -> 보통 date 사용
        a. date
            - 년 월 일 시 분 초 까지 저장 가능
        b. tiemstamp
            - date + 밀리초 나노초 까지 저장 가능
        c. interval
            - 시간 저장
            - tick값 저장용
            - 숫자형으로 대체해서 쓰기도 함
    
    4. 이진 데이터형
        a. blob
            - 비 텍스트 데이터
            - 이미지, 동영상, 음악, 실행파일, 압축파일 등...
            - 잘 사용하지 않음. 왜? 느림. 유지비가 비쌈.
            ex) 첨부파일을 저장해야 할 경우 파일명만 저장하고 파일은 물리적 저장장치에 넣어놓음.
            
    
테이블 선언하기

CREATE TABLE 테이블명 (컬럼, 컬럼, 컬럼, ...);

CREATE TABLE 테이블명 (
    컬럼 선언,
    컬럼 선언,
    컬럼 선언,
    컬럼명 자료형
);
*/

-- 수업에서 DB Object 식별자(컬럼 빼고) -> 헝가리안 표기법

DROP TABLE tblType;

CREATE TABLE tblType (
    -- num number
    
    -- num number(3) -- 정수 3자리(-999 ~ +999)까지 기억하겠다.
    
    -- num number(4,2) -- (전체자릿수,소수이하자릿수) -> 주의: 소수 이하 자릿수까지 전체자릿수에 포함됨 (-99.99 ~ 99.99)
    
    --txt char(10) -- 최대 10바이트
    
    txt1 char(10),
    txt2 varchar2(10)
);

-- 현재 계정이 소유한 테이블 목록
SELECT * FROM tabs;

-- 데이터 추가
-- insert into 테이블명(컬럼명) values (값);

-- num number 실험
INSERT INTO tblType(num) VALUES (100);  -- 정수 리터럴
INSERT INTO tblType(num) VALUES (3.14); -- 실수 리터럴
INSERT INTO tblType(num) VALUES (1234567890123456789012345678901234567890); -- 40개

-- number(3) 실험
INSERT INTO tblType(num) VALUES (100);
INSERT INTO tblType(num) VALUES (3.14);
--INSERT INTO tblType(num) VALUES (1234567890123456789012345678901234567890); -- Overflow(오류코드 ORA-01438) 발생
INSERT INTO tblType(num) VALUES (999);
INSERT INTO tblType(num) VALUES (-999);
--INSERT INTO tblType(num) VALUES (1000); -- Overflow(오류코드 ORA-01438) 발생

-- num number(4,2) 실험
-- INSERT INTO tblType(num) VALUES (100); -- Overflow(오류코드 ORA-01438) 발생
INSERT INTO tblType(num) VALUES (99.99);
INSERT INTO tblType(num) VALUES (-99.99);

-- txt(*10) 실험
INSERT INTO tblType(txt) VALUES (100);
INSERT INTO tblType(txt) VALUES ('ABC');
INSERT INTO tblType(txt) VALUES ('ABCDEFGHIJ');
INSERT INTO tblType(txt) VALUES ('홍길동');
--INSERT INTO tblType(txt) VALUES ('홍길동님'); -- (actual: 12, maximum: 10)

-- char, varchar2 실험
-- char(10)     : 'ABC       ' (남은 칸을 공백으로 채움)
-- varchar2(10) : 'ABC'        (7칸어치 공간을 없앰)
INSERT INTO tblType(txt1, txt2) VALUES ('ABC', 'ABC');

INSERT INTO tblType(txt1, txt2) VALUES ('ABCDEFGHIJ', 'ABCDEFGHIJ');


-- 데이터 읽어오기
SELECT * FROM tblType;
















