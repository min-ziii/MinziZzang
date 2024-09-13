/*
    ex18_groupby.sql
    
    [WITH <Sub Query>]
    SELECT column_list
    FROM table_name
    [WHERE search_condition]
    [GROUP BY group_by_expression]
    [HAVING search_condition]
    [ORDER_BY order_expression [ASC|DESC]];

    
    역할
    SELECT column_list  -> 어떤 테이블로부터 '지정된 컬럼'만 가져와라.
    FROM table_name     -> '어떤 테이블'로부터 데이터를 가져와라.
    WHERE 검색조건;     -> 조건 지정. 원하는 레코드만 걸러낸다.
    GROUP BY 그룹기준   -> 특정 컬럼값을 기준으로 여러 그룹을 나눈다.
    ORDER BY 정렬기준   -> 결과셋의 레코드 순서를 정한다.
    
    
    GROUP BY절
    - 특정 기준으로 레코드를 그룹으로 나는다.(수단)
        -> 각각의 그룹을 대상으로 집계 함수를 실행한다.
*/

SELECT * FROM tblInsa;

SELECT round(avg(basicpay)) FROM tblInsa; -- 1556527

SELECT round(avg(basicpay)) FROM TblInsa WHERE buseo = '총무부'; -- 1714857
SELECT round(avg(basicpay)) FROM TblInsa WHERE buseo = '개발부'; -- 1387857
SELECT round(avg(basicpay)) FROM TblInsa WHERE buseo = '영업부'; -- 1601513
SELECT round(avg(basicpay)) FROM TblInsa WHERE buseo = '기획부'; -- 1855714
SELECT round(avg(basicpay)) FROM TblInsa WHERE buseo = '인사부'; -- 1553300
SELECT round(avg(basicpay)) FROM TblInsa WHERE buseo = '자재부'; -- 1416733
SELECT round(avg(basicpay)) FROM TblInsa WHERE buseo = '홍보부'; -- 1451833

-- 한 개의 SELECT문으로 묶기
SELECT buseo,
    round(avg(basicpay)) as 부서별평균급여,
    Count(*) as 부서별인원수,
    sum(basicpay) as 부서별총급여액,
    max(basicpay) as 부서별최고급여,
    min(basicpay) as 부서별최저급여
FROM tblInsa
GROUP BY buseo;

-- 남자수와 여자수 찾기
-- 경직된 코드
SELECT
    count(decode(gender, 'm', 1)) as 남자수,
    count(decode(gender, 'f', 1)) as 여자수
FROM tblComedian;

-- 유연한 코드
SELECT
    gender, count(*)
FROM tblComedian
GROUP BY gender;

SELECT
    jikwi, Count(*)
FROM tblInsa
GROUP BY jikwi;

SELECT
    city, Count(*)
FROM tblInsa
GROUP BY city;

SELECT
    name, Count(*)
FROM tblInsa
GROUP BY name;

SELECT
    buseo, jikwi, Count(*)
FROM tblInsa
GROUP BY buseo, jikwi;

SELECT
    buseo, jikwi, Count(*)
FROM tblInsa
GROUP BY jikwi, buseo;


-- 급여별 그룹
-- 100만원 이하
-- 100만원 ~ 200만원 이하
-- 200만원 이상
SELECT
    basicpay, count(*)
FROM tblInsa
GROUP BY basicpay;

SELECT
    basicpay,
    (floor((basicpay / 1000000)) + 1) * 100 || '만원 미만'
FROM tblInsa;


-- tblInsa에서 여자의 수와 남자의 수는?
SELECT
     substr(ssn, 8, 1), count(*)
FROM tblInsa
GROUP BY substr(ssn, 8, 1);


-- 한 일의 개수와 안 한 일의 개수는?
SELECT
     case
            when completeDate is NULL then '안 한 일'
            when completeDate is NOT NULL then '한 일'
     end,
     count(*)
FROM tblTodo
GROUP BY case
            when completeDate is NULL then '안 한 일'
            when completeDate is NOT NULL then '한 일'
         end;

-- 직위 별 인원수를 구하되, 과장+부장=관리직이 몇 명이고 대리+사원=현장직이 몇 명?
-- 1. case-end
SELECT
    case
        when jikwi in ('과장', '부장') then '관리직'
        when jikwi in ('대리', '사원') then '현장직'
    end,
    count(*)
FROM tblInsa
GROUP BY case
            when jikwi in ('과장', '부장') then '관리직'
            when jikwi in ('대리', '사원') then '현장직'
         end;

-- GROUP BY 사용 시 주의점 - 집계함수의 주의점과 유사
-- GROUP BY가 포함된 SELECT문은 목적이 그룹에 관련된 질의이다.
-- -> SELECT절에 일반 컬럼(개인 데이터)은 사용할 수 없다.
-- -> 집계함수 또는 GROUP BY의 기준이 되는 컬럼만 사용할 수 있다.
SELECT
    -- count(*), name -- ORA-00979: not a GROUP BY expression. 집계데이터, 개인데이터? nonono
    count(*), jikwi -- 여기의 jikwi는 GROUP BY에서 집합의 의미로 쓰였기 때문에 SELECT에 들어갈 수 있다.
FROM tblInsa
GROUP BY jikwi;


/*
    [WITH <Sub Query>]
    SELECT column_list
    FROM table_name
    [WHERE search_condition]
    [GROUP BY group_by_expression]
    [HAVING search_condition]
    [ORDER_BY order_expression [ASC|DESC]];
    
    역할
    SELECT column_list  -> 어떤 테이블로부터 '지정된 컬럼'만 가져와라.
    FROM table_name     -> '어떤 테이블'로부터 데이터를 가져와라.
    WHERE 검색조건;     -> 조건 지정. 원하는 레코드만 걸러낸다.
    GROUP BY 그룹기준   -> 특정 컬럼값을 기준으로 여러 그룹을 나눈다.
    HAVING 검색조건     -> 조건 지정. 원하는 그룹만 걸러낸다.
    ORDER BY 정렬기준   -> 결과셋의 레코드 순서를 정한다.
    
    순서
    5. SELECT
    
    1. FROM
    2. WHERE
    
    3. GROUP BY
    4. HAVING
    
    6. ORDER BY
*/

SELECT
    COUNT(*)
FROM tblInsa
WHERE basicpay >= 1500000;

-- 개인에 관한 질문
SELECT
    buseo, COUNT(*)         -- 4. 집계함수 호출 (급여가 150 이상인 사람들을 부서별로 분류)
FROM tblInsa                -- 1. 60명의 데이터를 가져온다.
WHERE basicpay >= 1500000   -- 2. '60명'을 대상으로 조건 검사
GROUP BY buseo;             -- 3.'2번을 통과한 사람들'을 대상으로 그룹 짓기

-- 그룹에 관한 질문
SELECT
    buseo, COUNT(*)              -- 4. 그룹 함수 호출 (평균급여가 150 이상인 부서만 생존. 개개인으로는 150을 넘는 사람이 탈락그룹 안에 있을 수도 있다.)
FROM tblInsa                     -- 1. 60명의 데이터를 가져온다.
GROUP BY buseo                   -- 2. '60명'을 대상으로 그룹 짓기
HAVING avg(basicpay) >= 1500000; -- 3. '그룹'을 대상으로 조건 검사. HAVING은 개인데이터를 기준삼을 수 없다.


SELECT
    buseo, COUNT(*)              
FROM tblInsa
WHERE basicpay >= 1500000
GROUP BY buseo                   
HAVING avg(basicpay) >= 1500000; -- 이미 WHERE에서 HAVING에 해당하는 개인이 다 선별됐으니 이 조건이 딱히 의미가 없다.

-- 부서 별(GROUP BY)로 과장/부장의(WHERE) 인원수가 3명 이상인(HAVING) 부서가 있는지?
SELECT
    buseo, count(*)
FROM tblInsa
WHERE jikwi in ('과장', '부장')
GROUP BY buseo
HAVING count(*) >= 3;