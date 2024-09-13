/*
    ex21_view.sql

    View, 뷰
    - 데이터베이스 객체(테이블, 제약사항, 시퀀스, 뷰, ...) 중 하나
    - 가상 테이블, 뷰 테이블이라고도 부름
    - 테이블처럼 사용한다.(*)
    - SQL(SELECT)문을 저장하는 객체.(***) -- View의 정의에 대해 질문이 들어오면 이렇게 대답하면 된다.
    
    CREATE [OR REPLACE] VIEW 뷰이름
    AS
    SELECT문;
    
    사용 목적
    1. 자주 쓰는 query를 저장
    2. 복잡하고 긴 query를 저장
    3. 저장 객체 -> DB Object -> Oracle에 저장 -> 사용자끼리 공유/재사용/협업 가능
    4. 권한 통제
*/

-- View 생성1
CREATE OR REPLACE VIEW vwInsa   -- CREATE OR REPLACE: 없으면 만들고 있으면 대체해라.
AS                              -- 하는 일은 없고 그냥 연결부.
SELECT * FROM tblInsa;

-- View 사용
SELECT * FROM vwInsa;

-- View 생성2
CREATE OR REPLACE VIEW vwInsa
AS
SELECT * FROM tblInsa WHERE buseo = '개발부';

UPDATE tblInsa SET city = '제주' WHERE num = 1060; -- 여기서 원본을 수정하면

SELECT * FROM vwInsa; -- 복사본도 수정된다. view는 SELECT문을 저장하는 객체니까. java의 method같은 느낌이라고 생각하자. 상세 내용은 view생성2 참고.
SELECT * FROM (SELECT * FROM tblInsa WHERE buseo = '개발부'); -- 윗줄과 의미가 같다. 이름이 있냐 없냐만 다르다. 얘는 inline view라고 함.
SELECT * FROM tblInsa;


-- 비디오 대여점 사장이 출근해서 '이 업무'를 매일 반복하는 상황.
CREATE OR REPLACE VIEW vwCheck
AS                              -- SELECT...뭐시기 다 쓰고 맨 위에 이 두 줄만 쓰면 바로 View 생성 가능.
SELECT
    m.name as 회원,
    v.name as 비디오,
    r.rentdate as 언제,
    r.retdate as 반납,
    g.period as 대여기간, 
    r.rentdate + g.period as 반납예정일,
    round(sysdate - (r.rentdate + g.period)) as 연체일,
    round((sysdate - (r.rentdate + g.period)) * g.price * 0.1) as 연체료 -- 대여료의 10%를 일 연체료로 설정
FROM tblRent r
INNER JOIN tblVideo v
ON v.seq = r.video
INNER JOIN tblGenre g
ON g.seq = v.genre
INNER JOIN tblMember m
ON m.seq = r.member;

SELECT * FROM vwCheck; -- 매일 이 한 줄만 쓰면 반복 가능.

CREATE OR REPLACE VIEW vwInsa
AS
SELECT * FROM tblInsa;

-- 4. 권한 통제 예시
-- 신입사원이 전직원에게 메시지를 보내는 담당업무를 맡았다. -> 전직원의 연락처(tblInsa.tel)를 알고있어야 한다.
-- 데이터베이스 로그인이 필요 -> 신입사원용 계정 생성
-- tblInsa에 직접 접근할 수 있는 권한 대신 View에 접근할 수 있는 권한 부여 (tblInsa는 너무 많은 정보를 가지고 있으니까 View로 컬럼 몇 개만 지정)

SELECT * FROM tblInsa;

CREATE OR REPLACE VIEW vwInsa
AS
SELECT name, buseo, jikwi, tel FROM tblInsa;


-- View 사용 시 주의점.
-- 1. SELECT -> 실행 잘 됨 -> View는 읽기 전용 테이블로만 사용할 것.
-- 2. INSERT -> 실행 잘 됨 -> 절대 사용 금지.
-- 3. UPDATE -> 실행 잘 됨 -> 절대 사용 금지.
-- 4. DELETE -> 실행 잘 됨 -> 절대 사용 금지.

-- 단순 뷰 -> View의 SELECT문이 1개의 테이블로 구성된 View (CRUD 모두 정상 동작)
CREATE OR REPLACE VIEW vwTodo
AS
SELECT * FROM tblTodo;

-- 1. SELECT
SELECT * FROM vwTodo;

-- 2. INSERT
INSERT INTO vwTodo VALUES(21, '뷰 만들기', sysdate, NULL);

-- 3. UPDATE
UPDATE vwTodo SET completeDate = sysdate WHERE seq = 21;

-- 4. DELETE
DELETE FROM vwTodo WHERE seq = 21; 

-- 복합 뷰 -> View의 SELECT문이 2개 이상의 테이블로 구성된 View
SELECT * FROM vwCheck;
INSERT INTO vwCheck (컬럼리스트) VALUES (값리스트); -- 문장 하나로 테이블 4개에 어떻게 집어넣지?
DELETE FROM vwCheck WHERE 기본키 = ?; -- 문장 하나로 4개를 어떻게 지울거지?

-- vwTodo VS. vwCheck -> 어떤게 단순 View이고 어떤게 복합 View인가? 모름. 선언부를 보면 알겠지.