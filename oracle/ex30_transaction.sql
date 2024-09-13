/*
    ex30_transaction.sql
    
    트랜잭션
    - 데이터를 조작하는 업무의 물리적(시간적) 단위
    - 1개 이상의 명령어를 묶어놓은 단위
    
    (***) 트랜잭션 처리(ROLLBACK)가 과거로 돌아갈 수 있는 이유는?
    - 트랜잭션 내의 INSERT, UPDATE, DELETE는 실제 DB에 적용되지 않는다.
    - 트랜잭션 내의 INSERT, UPDATE, DELETE는 실행 중인 메모리에만 임시 적용된다.
    
    트랜잭션 명령어
    - DCL, TCL
    1. COMMIT
    2. ROLLBACK
    3. SAVEPOINT
    
    
    
*/

CREATE TABLE tblTrans
AS
SELECT name, buseo, jikwi FROM tblInsa WHERE city = '서울';

SELECT * FROM tblTrans;

-- 우리가 하는 행동(SQL 쿼리 코딩)을 반드시 시간 순서대로 기억해야 한다.

-- 로그인 -> 트랜잭션 시작
-- 트랜잭션의 행위 체크는 INSERT, UPDATE, DELETE 명령어만 포함된다.

SELECT * FROM tblTrans; -- SELECT는 현재 트랜잭션에 포함되지만 행위 체크에는 포함되지 않음.

DELETE FROM tblTrans WHERE name = '박문수'; -- DELETE니까 트랜잭션에 포함됨.

SELECT * FROM tblTrans;

-- ??? : 박문수 씨 퇴사처리 취소해야 합니다!!
ROLLBACK; -- 트랜잭션 내 INSERT, UPDATE, DELETE를 없었던 일로 한다. 현재 트랜잭션을 종료하고 새 트랜잭션을 시작한다.

SELECT * FROM tblTrans;

DELETE FROM tblTrans WHERE name = '박문수';

SELECT * FROM tblTrans;

COMMIT; -- 메모리에서만 진행됐던 지금까지의 트랜잭션을 실제 DB에 반영시키겠다. 현재 트랜잭션을 종료하고 새 트랜잭션을 시작한다.

SELECT * FROM tblTrans;

INSERT INTO tblTrans VALUES ('강아지', '기획부', '사원');

UPDATE tblTrans SET jikwi = '대리' WHERE name = '한석봉';

DELETE FROM tblTrans WHERE buseo = '영업부';

SELECT * FROM tblTrans;

ROLLBACK;

SELECT * FROM tblTrans;



/*
    트랜잭션은 언제 시작해서 언제 끝나는가?
    
    새로운 트랜잭션이 시작하는 시점
    1. 클라이언트 접속 직후
    2. COMMIT 실행 직후
    3. ROLLBACK 실행 직후
    
    현재 트랜잭션이 종료되는 시점
    1. COMMIT -> DB에 반영됨
    2. ROLLBACK -> DB에 반영되지 않음
    3. 클라이언트 접속 종료
        a. 정상 종료
            - 현재 트랜잭션에 반영이 되지 않은 명령어가 남아있으면 사용자에게 질문.
        b. 비정상 종료
            - 트랜잭션을 처리할 만한 시간적 여유가 없는 경우
            - 무조건 ROLLBACK
    4. DDL 실행 (!*-주의-*!)
        - CREATE, ALTER, DROP 실행 시 즉시 COMMIT이 실행된다.
        - DDL은 DB의 구조를 조작하는 성격이 강하다.
    
*/

INSERT INTO tblTrans VALUES ('강아지', '기획부', '사원');

SELECT * FROM tblTrans;

INSERT INTO tblTrans VALUES ('고양이', '기획부', '사원');


SELECT * FROM tblTrans;

CREATE Sequence seqAAA; -- COMMIT 자동 적용

ROLLBACK;

SELECT * FROM tbltrans;


-- 트랜잭션 통제 방법(패턴)
-- 1. 시간 정하기
    -- 5분, 1시간, 일 1회
-- 2. 작업 단위
-- 3. 자신의 행동을 적당한 주석

-- hr 접속
select * from tabs;

insert into tblTrans values ('병아리', '기획부', '사원');
delete from tblTrans;
select * from tblTrans;

rollback;
select * from tblTrans;

insert into tblTrans values ('병아리', '기획부', '사원');
select * from tblTrans;

commit;

select * from tblTrans;

commit;

SELECT * FROM tbltrans;

INSERT INTO tblTrans VALUES('호랑이', '개발부', '사원');

SAVEPOINT a;

DELETE FROM tblTrans WHERE name = '홍길동';

SAVEPOINT b;

UPDATE tblTrans SET buseo = '영업부' WHERE name = '호랑이';

SELECT * FROM tbltrans;

rollback to b;

SELECT * FROM tblTrans;