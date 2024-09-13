/*
    ex23_alter.sql
    
    DDL -> 객체 조작
    - 객체 생성: CREATE
    - 객체 수정: ALTER
    - 객체 삭제: DROP
    
    DML -> 데이터 조작
    - 데이터 생성: INSERT
    - 데이터 수정: UPDATE
    - 데이터 삭제: DELETE
    
    
    테이블 수정하기
    - 테이블 수정 -> 테이블 정의 수정 -> 컬럼 수정 -> 컬럼명 OR 자료형(길이) OR 제약사항 수정
    - 되도록 테이블을 수정하는 상황을 발생시키면 안 된다.
    - 테이블 설계를 최대한 문제 없게 할 것.
    
    테이블을 수정해야 하는 상황 발생!
    1. 테이블 삭제(DROP) -> 테이블 DDL(CREATE) 수정 -> 수정된 테이블로 다시 테이블 생성
        a. 기존 테이블에 데이터가 없을 경우 -> 딱히 문제 X
        b. 기존 테이블에 데이터가 있을 경우 -> 미리 데이터 백업 -> 테이블 삭제 -> 수정테이블 생성 -> 데이터 복구
            - 개발 중에 가능
            - 공부 중에 가능
            - 서비스 중에..? 많이 부담되지만 가능
    
    2. ALTER 명령어 사용 -> 기존 테이블의 구조 변경
        a. 기존 테이블에 데이터가 없을 경우 -> 딱히 문제 X
        b. 기존 테이블에 데이터가 있을 경우 -> 상황에 따라 다름
            - 개발 중에 가능
            - 공부 중에 가능
            - 서비스 중에..? 1보다는 덜 부담되지만 가능
        
*/

DROP TABLE tblEdit;

CREATE TABLE tblEdit (
    seq number PRIMARY KEY,
    name varchar2(20) NOT NULL
);

INSERT INTO tblEdit VALUES (1, '마우스');
INSERT INTO tblEdit VALUES (2, '키보드');
INSERT INTO tblEdit VALUES (3, '모니터');

-- Case 1. 새로운 컬럼을 추가하기
ALTER TABLE 테이블
    ADD (컬럼 정의);

ALTER TABLE tblEdit
    ADD (price number);
    
SELECT * FROM tblEdit;



ALTER TABLE tblEdit
ADD (qty number NOT NULL); -- ORA-01758: table must be empty to add mandatory (NOT NULL) column
-- 데이터가 없는데 NOT NULL 컬럼을 만들면 제약사항에 바로 위배되니까 아예 테이블 데이터를 다 지워서 초기화상태로 만들라는 경고

DELETE FROM tblEdit;

INSERT INTO tblEdit VALUES (1, '마우스', 1000, 1);
INSERT INTO tblEdit VALUES (2, '키보드', 2000, 1);
INSERT INTO tblEdit VALUES (3, '모니터', 3000, 2);

ALTER TABLE tblEdit
ADD(color varchar2(30) DEFAULT 'white' NOT NULL); -- DEFAULT를 넣으면 color가 NOT NULL로 추가선언되어도 괜찮음.

SELECT * FROM tblEdit;

-- Case 2. 컬럼 삭제하기
ALTER TABLE 테이블명
DROP COLUMN 컬럼명;

ALTER TABLE tblEdit
DROP COLUMN name;  -- 사라진 데이터는 복구되지 않는다.

ALTER TABLE tblEdit
DROP COLUMN seq;
-- ORA-12983: cannot drop all columns in a table
-- 모든 컬럼을 다 지울 순 없다. 그럼 그게 테이블이냐???
-- 그리고 seq는 Primary Key인데 이걸 왜 지워? 절대 금지.
 
SELECT * FROM tblEdit;


-- Case 3. 컬럼 수정하기
INSERT INTO tblEdit VALUES (4, '맥북 M3 프로 2024 고급형');
-- ORA-12899: value too large for column "HR"."TBLEDIT"."NAME" (actual: 31, maximum: 20)
-- 컬럼 길이를 너무 짧게 잡아서 수정하려고 한다.

-- Case 3.1 컬럼 길이 수정하기(확장/축소)
ALTER TABLE 테이블명
MODIFY (컬럼 정의);

ALTER TABLE tblEdit
MODIFY (name varchar2(100)); -- 20byte에서 100byte로 확장

INSERT INTO tblEdit VALUES (4, '맥북 M3 프로 2024 고급형');

SELECT * FROM tblEdit;

ALTER TABLE tblEdit
MODIFY (name varchar2(20));
-- ORA-01441: cannot decrease column length because some value is too big
-- 이미 20byte보다 큰 데이터가 들어가 있어서 변경 불가. -> 상황을 보고 해야 한다.


-- Case 3.2 컬럼 제약사항 수정하기(NOT NULL/NULL)
ALTER TABLE tblEdit
MODIFY (name varchar2(100) NULL); -- NOT NULL -> NULL

DESC tblEdit;
/*
이름   널?       유형            
---- -------- ------------- 
SEQ  NOT NULL NUMBER        
NAME          VARCHAR2(100) 
*/

ALTER TABLE tblEdit
MODIFY (name varchar2(100) NOT NULL); -- NULL -> NOT NULL
-- NOT NULL인데 이미 NULL인 값이 있으면 변경이 안 되니까
ALTER TABLE tblEdit
MODIFY (name varchar2(100) DEFAULT '임시' NOT NULL);
-- 마찬가지로 DEFAULT를 써서 예외처리를 해줌.

ALTER TABLE tblEdit
MODIFY (name varchar2(100) UNIQUE); -- 나머지 제약사항도 동일한 방식

DESC tblEdit;
/*
이름   널?       유형            
---- -------- ------------- 
SEQ  NOT NULL NUMBER        
NAME NOT NULL VARCHAR2(100) 
*/


-- Case 3.3 컬럼의 자료형 수정하기
-- ...이건 테이블 설계 자체가 잘못됐다는 소리니까 웬만하면 있어선 안 될 경우임.
DELETE FROM tblEdit;

ALTER TABLE tblEdit
MODIFY (seq varchar2(10));

DESC tblEdit;
/*
이름   널?       유형            
---- -------- ------------- 
SEQ  NOT NULL VARCHAR2(10)  
NAME NOT NULL VARCHAR2(100)
*/


-- Case 4. 제약 사항 조작 (NOT NULL 제외)
DROP TABLE tblEdit;

CREATE TABLE tblEdit (
    seq number,
    name varchar2(20)
);

ALTER TABLE tblEdit
ADD CONSTRAINT tblEdit_seq_pk PRIMARY KEY(seq);

INSERT INTO tblEdit VALUES (1,'강아지');
INSERT INTO tblEdit VALUES (1,'강아지'); -- ORA-00001: unique constraint (HR.TBLEDIT_SEQ_PK) violated
-- =PK가 잘 추가됐다.

ALTER TABLE tblEdit
ADD CONSTRAINT tblEdit_name_uq UNIQUE(name);

INSERT INTO tblEdit VALUES (2,'강아지'); -- ORA-00001: unique constraint (HR.TBLEDIT_NAME_UQ) violated
-- =UNIQUE가 잘 추가됐다.

ALTER TABLE tblEdit
DROP CONSTRAINT tblEdit_name_uq;

INSERT INTO tblEdit VALUES (2,'강아지');  -- UNIQUE가 풀렸으니 잘 들어간다.

SELECT * FROM tblEdit;