/*
    ex15_insert.sql
    - DML
    - 테이블에 데이터 추가
    
    INSERT INTO 테이블명 (컬럼리스트) VALUES (값리스트);
    
    
*/

DROP TABLE tblMemo;

SELECT * FROM tblMemo;

CREATE TABLE tblMemo (
    seq number(3) Primary Key,
    name varchar2(30) default '익명',
    memo varchar2(1000),
    regdate date default sysdate NOT NULL
);

CREATE Sequence seqMemo;


-- 1. 표준
-- 원본 테이블에 정의된 컬럼 순서대로 컬럼 리스트&값 작성
-- 특별한 목적이 없다면 이 방법을 사용할 것
INSERT INTO tblMemo(seq, name, memo, regdate)
        VALUES (seqMemo.nextVal, '홍길동', '메모입니다.', sysdate);
        
SELECT * FROM tblMemo;


-- 2. 컬럼 리스트와 값 리스트의 순서는 원본 테이블의 변수 선언 순서와 상관 없다.
-- 단, 컬럼 리스트와 값 리스트의 순서는 서로 일치해야 한다.
INSERT INTO tblMemo(regdate, seq, name, memo) -- regdate가 둘 다 맨 앞으로 옴
        VALUES (sysdate, seqMemo.nextVal, '홍길동', '메모입니다.'); -- 정상 기능
        
        
-- 3. ORA-00947: not enough values. 값이 하나 지워져서 컬럼 리스트와 안 맞다고 함.
INSERT INTO tblMemo(seq, name, memo, regdate)
        VALUES (seqMemo.nextVal, '홍길동', '메모입니다.');
     
        
-- 4. ORA-00913: too many values. 컬럼이 하나 지워져서 값이 너무 많다고 함.
INSERT INTO tblMemo(seq, name, memo)
        VALUES (seqMemo.nextVal, '홍길동', '메모입니다.', sysdate);
     
        
-- 5. NULL 컬럼 조작
-- 5.1 NULL 상수 사용
INSERT INTO tblMemo(seq, name, memo, regdate)
        VALUES (seqMemo.nextVal, '홍길동', NULL, sysdate);
-- 5.2 컬럼(+값) 생략
INSERT INTO tblMemo(seq, name, regdate)
        VALUES (seqMemo.nextVal, '홍길동', sysdate);
        
        
-- 6. Default 조작
-- 6.1 컬럼을 생략하는 방식(5.2)처럼 NULL을 대입하면 미리 걸려있던 Default가 호출됨
INSERT INTO tblMemo(seq, memo, regdate)
        VALUES (seqMemo.nextVal, '메모입니다.', sysdate);
-- 6.2 NULL 상수 넣기 -> Default가 호출되지 않음
INSERT INTO tblMemo(seq, name, memo, regdate)
        VALUES (seqMemo.nextVal, NULL, '메모입니다.', sysdate);
-- 6.3 Default 상수를 넣기
INSERT INTO tblMemo(seq, name, memo, regdate)
        VALUES (seqMemo.nextVal, Default, '메모입니다.', sysdate);
        
        
-- 7. 단축
-- 7.1 컬럼 리스트는 생략할 수 있다.
INSERT INTO tblMemo
        VALUES (seqMemo.nextVal, '홍길동', '메모입니다.', sysdate);
-- 7.2 컬럼 리스트를 생략하면, 테이블 원본 컬럼 순서대로 값 리스트를 만들어야 한다.
-- ORA-00932: inconsistent datatypes: expected NUMBER got DATE. 생략된 컬럼 리스트와 변경된 값 리스트의 순서가 서로 맞지 않는다.
INSERT INTO tblMemo
        VALUES (sysdate, seqMemo.nextVal, '홍길동', '메모입니다.');
-- 7.3 NULL 컬럼은 생략할 수 없다. -> ORA-00947: not enough values
INSERT INTO tblMemo
        VALUES (seqMemo.nextVal, '홍길동', sysdate);
INSERT INTO tblMemo
        VALUES (seqMemo.nextVal, '홍길동', NULL, sysdate); -- NULL 상수를 넣으면 된다.
-- 7.4 Default 컬럼도 생략할 수 없다. -> ORA-00947: not enough values
INSERT INTO tblMemo
        VALUES (seqMemo.nextVal, '메모입니다.', sysdate);
INSERT INTO tblMemo
        VALUES (seqMemo.nextVal, Default, '메모입니다.', sysdate); -- 마찬가지로 Default 상수를 넣는다.
        

-- 8.
-- tblMemo 테이블을 복사해서 새 테이블(tblMemoCopy)을 만들어달라는 요청
DROP TABLE tblMemoCopy;

CREATE TABLE tblMemoCopy (
    seq number(3) Primary Key,
    name varchar2(30) default '익명',
    memo varchar2(1000),
    regdate date default sysdate NOT NULL
);

INSERT INTO tblMemoCopy SELECT * FROM tblMemo; -- SubQuery 기술
INSERT INTO tblMemoCopy SELECT * FROM tblMemo WHERE name = '홍길동'; -- 부분선택복사
SELECT * FROM tblMemo;      -- 8
SELECT * FROM tblMemoCopy;  -- 5


-- 9.
-- tblMemo 테이블을 복사해서 새 테이블(tblMemoCopy)을 만들어달라는 요청
-- 9.1 테이블 구조 복사(Yes)
-- 9.2 제약 사항 복사(No)
-- NOT NULL 조건만 복사해줌. -> 진짜 테이블로 쓰는 대신 더미 테이블로 쓰려고 할 때 유용한 방법
CREATE TABLE tblMemoCopy
AS
SELECT * FROM tblMemo;

SELECT * FROM tblMemoCopy;
DESC tblMemoCopy;