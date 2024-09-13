/*
    ex26_with.sql
    
    [WITH <Sub Query>]
    SELECT column_list
    FROM table_name
    [WHERE search_condition]
    [GROUP BY group_by_expression]
    [HAVING search_condition]
    [ORDER_BY order_expression [ASC|DESC]];
    
    역할
    WITH 서브쿼리       -> 임시 View를 만든다.
    SELECT column_list  -> 어떤 테이블로부터 '지정된 컬럼'만 가져와라.
    FROM table_name     -> '어떤 테이블'로부터 데이터를 가져와라.
    WHERE 검색조건;     -> 조건 지정. 원하는 레코드만 걸러낸다.
    GROUP BY 그룹기준   -> 특정 컬럼값을 기준으로 여러 그룹을 나눈다.
    HAVING 검색조건     -> 조건 지정. 원하는 그룹만 걸러낸다.
    ORDER BY 정렬기준   -> 결과셋의 레코드 순서를 정한다.
    

    순서
    1. WITH
    6. SELECT
    2. FROM
    3. WHERE
    4. GROUP BY
    5. HAVING
    7. ORDER BY
    
    WITH절
    - In-Line View에 이름을 붙이는 기술
    
    WITH 테이블명 as 서브쿼리
    SELECT문;
    
*/

SELECT * FROM (SELECT name, buseo, jikwi FROM tblInsa WHERE city = '서울');

WITH seoul as (SELECT name, buseo, jikwi FROM tblInsa WHERE city = '서울')
SELECT * FROM seoul;


SELECT *
FROM
    (SELECT name, age, couple FROM tblMen WHERE weight < 90) a
INNER JOIN
    (SELECT name, age, couple FROM tblWomen WHERE weight > 60) b
ON a.couple = b.name;

WITH men as (SELECT name, age, couple FROM tblMen WHERE weight < 90),
     women as (SELECT name, age, couple FROM tblWomen WHERE weight > 60)
SELECT *
FROM men INNER JOIN women
ON men.couple = women.name;


------------------------------------------------------------------------
/*
    NULL 함수
    - NULL을 치환하는 함수
    
    null value
    1. nvl(컬럼, 값)
    2. nvl2(컬럼, 값)
    
*/

-- 고객: 비어있는 곳은 그냥 0으로 채워주면 안 돼요?
-- 1. case-end문
SELECT
    name,
    case
        when population IS NOT NULL then population
        when population IS NULL then 0
    end as population
FROM tblCountry;

-- 2. nvl(값)
SELECT
    name,
    nvl(population, 0)
FROM tblCountry;

-- 2-1. nvl() 주의사항
-- ORA-01722: invalid number. number에 varchar2를 넣으려고 해서 생기는 오류.
SELECT
    name,
    nvl(population, '인구조사 미완료')
FROM tblCountry;

-- 3. nvl2(값1, 값2)
-- NOT NULL일 경우 값1, NULL일 경우 값2
SELECT
    name,
    nvl2(population, 1, 2)
FROM tblCountry;

-- 3-1. nvl2() 특징
-- 둘 다 varchar이기 때문에 이 경우는 사용 가능.
SELECT
    name,
    nvl2(population, '조사 완료', '조사 미완료')
FROM tblCountry;




-- nvl() 사용 예시
DROP TABLE tblItem;

CREATE TABLE tblItem (
  seq number Primary Key,
  name varchar2(30) NOT NULL,
  color varchar2(30) NOT NULL
);

SELECT * FROM tblItem;

INSERT INTO tblItem (seq, name, color)
VALUES ((SELECT nvl(max(seq), 0) + 1 FROM tblItem), '마우스', 'white'); -- 그냥 max(seq)만 쓰면 값이 없을 경우에 NULL참조가 일어나서 오류가 발생함.

INSERT INTO tblItem (seq, name, color)
VALUES ((SELECT nvl(max(seq), 0) + 1 FROM tblItem), '키보드', 'white');

INSERT INTO tblItem (seq, name, color)
VALUES ((SELECT nvl(max(seq), 0) + 1 FROM tblItem), '모니터', 'white');