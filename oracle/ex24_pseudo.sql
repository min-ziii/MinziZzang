/*
    ex24_pseudo.sql
    
    의사 컬럼,  Pseudo column
    - 실제 컬럼이 아닌데 컬럼처럼 행동하는 객체
    
    rownum
    - 행번호
    - 결과셋의 행번호 반환
    - Oracle 전용. / MSSQL-top
    
*/

SELECT
    name, buseo -- 컬럼(속성). 레코드(객체)에 따라 각자 다른 값을 가진다.
    100, -- 상수. 모든 레코드(객체)가 동일한 값을 가진다.
    substr(name, 2), -- 함수. 레코드(객체)에 따라 다른 값을 가진다.
    rownum -- 의사 컬럼
FROM tblInsa;

SELECT name, buseo, rownum FROM tblInsa WHERE rownum = 1;
SELECT name, buseo, rownum FROM tblInsa WHERE rownum <= 5;

-- 게시판 페이징
-- 1페이지: WHERE rownum between 1 and 10
-- 2페이지: WHERE rownum between 11 and 20
-- 3페이지: WHERE rownum between 21 and 30

SELECT name, buseo, rownum FROM tblInsa WHERE rownum >= 1 and rownum =< 10;     -- 안됨
SELECT name, buseo, rownum FROM tblInsa WHERE rownum >= 11 and rownum =< 20;    -- 안됨
SELECT name, buseo, rownum FROM tblInsa WHERE rownum = 1;                       -- 됨
SELECT name, buseo, rownum FROM tblInsa WHERE rownum = 3;                       -- 안됨

--???????머임?

-- *** 1. rownum은 FROM절이 실행되는 순간 생성된다.
-- *** 2. WHERE절에 의해 결과셋에 변화가 생길 때 rownum이 재계산된다.

SELECT name, buseo, rownum  -- 2. 1번에서 생성된 rownum을 읽기만 한다.
FROM tblInsa;               -- 1. FROM절이 실행되는 순간 모든 레코드에 rownum이 할당되어 있다.

SELECT name, buseo, rownum  -- 2. 읽기
FROM tblInsa                -- 1. 할당
WHERE rownum = 1;           -- 3. 조건
-- 원하는 값이 나옴.

SELECT name, buseo, rownum  -- 2. 읽기
FROM tblInsa                -- 1. 할당
WHERE rownum = 3;           -- 3. 조건 (3이니? 아뇨. 삭제 -> 3이니? 아뇨. 삭제 -> ...)
-- 원하는 값이 안나옴.

SELECT name, buseo, rownum   -- 2. 읽기
FROM tblInsa                 -- 1. 할당
WHERE rownum <= 3;           -- 3. 조건 (3이거나 작니? 예. 패스. -> 3이거나 작니? 예. 패스. -> 3이거나 작니? 예. 패스. 3이거나 작니? 아뇨. 삭제. -> ...)
-- 원하는 값이 나옴.


-- rownum + subquery로 원하는 영역을 가져올 수 있다.
-- subquery를 1~2개 정도 사용.

-- 급여가 많은 1~3등을 가져와라.
SELECT name, basicpay FROM tblInsa WHERE rownum <= 3 ORDER BY basicpay DESC; -- 결과값이 꼬임

SELECT name, basicpay, rownum FROM tblInsa;
SELECT name, basicpay, rownum FROM tblInsa ORDER BY basicpay DESC; -- rownum이 꼬?여있음
-- 사실은 꼬여있는 게 아니고... ORDER BY 정렬 이전에 초기 상태를 기준으로 생성된 rownum인 것뿐이다.

SELECT name, basicpay, rnum, rownum FROM (SELECT name, basicpay, rownum as rnum FROM tblInsa ORDER BY basicpay DESC); -- rownum이 2번 만들어진다.

-- 급여가 많은 순서로 5등 ~ 10등을 가져오기
SELECT
    b.*
FROM (SELECT a.*, rownum as rnum
      FROM (SELECT name, basicpay
            FROM tblInsa
            ORDER BY basicpay DESC) a
     ) b
WHERE rnum >= 5 and rnum <= 10;

-- Oracle 12c 버전부터 지원되는 구문
SELECT
*
FROM tblInsa
ORDER BY basicpay DESC
    OFFSET 4 rows fetch next 6 rows only; -- 5 ~ 10