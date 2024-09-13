/*
    ex09_numerical_function.sql
    
    숫자 함수
    - Math.xxx()
    
    round()
    - 반올림 함수
    - number round(컬럼명)
    - number round(컬럼명, 소수 이하 자릿수) : 원하는 자릿수 반환(실수)
    
*/

SELECT avg(basicpay) FROM tblInsa; -- 1556526.666...
SELECT round(avg(basicpay)) FROM tblInsa; -- 1556527
SELECT round(avg(basicpay), 1) FROM tblInsa; -- 1556526.7

-- 시스템 테이블
SELECT * FROM tabs;
SELECT * FROM dual; -- 1행 1열짜리 테이블. Dummy(X)

SELECT round(3.14) FROM dual;
-- 다 필요 없고 3.14의 round값을 보고싶을 때 dual이라는 1행 1열짜리 테이블을 이용해서 값을 하나만 볼 수 있다.

SELECT
    3.5678,
    round(3.5678),
    round(3.5678, 1),
    round(3.5678, 2),
    round(3.5678, 0)
FROM dual;


/*
    floor(), trunc()
    - 절삭 함수
    - 무조건 내림 함수
    - number floor(컬럼명)
    - number trunc(컬럼명)
    - number trunc(컬럼명, 소수 이하 자릿수)
    
*/

SELECT
    3.5678,
    floor(3.5678),
    trunc(3.5678),
    trunc(3.5678, 1),
    trunc(3.5678, 0)
FROM dual;

/*
    ceil()
    - 무조건 올림 함수
    - 천장
    - number ceil(컬럼명)
    
*/

SELECT
    3.14,
    ceil(3.14)
FROM dual;

SELECT
    floor(3.99999999999),
    ceil(3.000000000001)
FROM dual;

/*
    mod()
    - 나머지 함수
    - number mod(피제수, 제수)
    
*/

SELECT
    10 / 3,
    floor(10 / 3) as 몫,
    mod(10, 3) as 나머지
FROM dual;

SELECT
    -- 절댓값
    abs(10), abs(10),
    -- 제곱
    power(2, 2), power(2, 3), power(2, 4),
    -- 제곱근(루트)
    sqrt(4), sqrt(9), sqrt(16)
FROM dual;