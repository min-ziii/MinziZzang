/*
    ex22_union.sql
    
    관계 대수 연산
    1. 셀렉션 -> SELECT WHERE
    2. 프로젝션 -> SELECT COLUMN
    3. 조인 -> JOIN
    4. 합집합(UNION), 차집합(MINUS), 교집합(INTERSECT) 
    
    합집합(UNION)
    - 테이블 + 테이블 연산
    - 스키마가 동일한 결과셋끼리만 가능
*/
-- UNION
SELECT * FROM tblmen
UNION
SELECT * FROM tblWomen;

-- ORA-01789: query block has incorrect number of result columns
SELECT * FROM tblMen
UNION
SELECT * FROM tblTodo;

-- 결과셋의 스카마만 맞추면 원본들의 구성이 달라도 UNION이 되긴 함... 데이터는 의미가 없겠지만.
SELECT name, height
FROM tblMen
UNION
SELECT title, seq FROM tblTodo;


-- UNION을 쓰는 예시.

-- 1. 어떤 회사에 부서별 게시판이 있다고 가정.
SELECT * FROM 영업부게시판;
SELECT * FROM 총무부게시판;
SELECT * FROM 개발부게시판;

-- 사장: 모든 부서 게시판을 한 번에 보고싶은데?
SELECT * FROM 영업부게시판
UNION
SELECT * FROM 총무부게시판
UNION
SELECT * FROM 개발부게시판;


-- 2. 야구선수 트레이닝 사이트 -> 공격수, 수비수 명단이 따로 있다고 가정.
SELECT * FROM 공격수;
SELECT * FROM 수비수;

-- 전체 선수 목록을 봐야 하는 업무 발생.
SELECT 공통컬럼리스트 FROM 공격수
UNION
SELECT 공통컬럼리스트 FROM 수비수;


-- 3. 다량의 데이터 관리
-- SNS -> 많은 데이터들이 쌓임. -> 누적되면 SELECT 속도가 저하됨. -> 기간 별로 테이블 분리.
SELECT * FROM 게시판2024
UNION
SELECT * FROM 게시판2023
UNION
SELECT * FROM 게시판2022;

-- 뭔가 검색을 해야 할 일이 생긴다면?
SELECT * FROM
    (SELECT * FROM 게시판2024
    UNION
    SELECT * FROM 게시판2023
    UNION
    SELECT * FROM 게시판2022)
    WHERE 조건;



CREATE TABLE tblAAA (
    name varchar2(30) NOT NULL,
    color varchar2(30) NOT NULL
);

CREATE TABLE tblbbb (
    name varchar2(30) NOT NULL,
    color varchar2(30) NOT NULL
);

INSERT INTO tblAAA VALUES ('강아지', '검정');
INSERT INTO tblAAA VALUES ('고양이', '노랑');
INSERT INTO tblAAA VALUES ('토끼', '갈색');
INSERT INTO tblAAA VALUES ('거북이', '녹색');
INSERT INTO tblAAA VALUES ('강아지', '회색');

INSERT INTO tblBBB VALUES ('강아지', '검정');
INSERT INTO tblBBB VALUES ('고양이', '노랑');
INSERT INTO tblBBB VALUES ('호랑이', '주황');
INSERT INTO tblBBB VALUES ('사자', '회색');
INSERT INTO tblBBB VALUES ('고양이', '검정');

SELECT * FROM tblAAA; -- 5
SELECT * FROM tblBBB; -- 5

-- UNION -> 중복이 제거된다.
SELECT * FROM tblAAA
UNION
SELECT * FROM tblBBB; -- 8

-- UNION ALL -> 중복을 포함한다.
SELECT * FROM tblAAA
UNION ALL
SELECT * FROM tblBBB; -- 10

-- INTERSECT -> 교집합.
SELECT * FROM tblAAA
INTERSECT
SELECT * FROM tblBBB; -- 2


-- MINUS -> 차집합. A-B
SELECT * FROM tblAAA
MINUS
SELECT * FROM tblBBB; -- 3

-- MINUS -> 차집합. B-A
SELECT * FROM tblBBB
MINUS
SELECT * FROM tblAAA; -- 3