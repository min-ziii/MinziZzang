/*
    
    ex25_rank.sql
    
    
    순위 함수
    -rownum 기반으로 만들어진 함수
    
    1. rank() over(ORDER BY 컬럼명 [ASC|DESC])
        - 동일한 값을 갖게 되면 동일한 순위로 매기고, 공동 n위 다음은 값의 수만큼 순위를 민다. (예- 공동9위가 둘이면 그다음 값의 순위는 11위)
        
    2. dense_rank() over(ORDER BY 컬럼명 [ASC|DESC])
        - 동일한 값을 갖게 되면 동일한 순위로 매기고, 공동 n위 다음은 n+1위로 처리한다. (예- 공동9위가 둘이면 그다음 값의 순위는 10위)
    
    3. row_number() over(ORDER BY 컬럼명 [ASC|DESC])
        - rownum처럼 순위를 매긴다. 중복값이든 아니든 순위
    
*/

-- tblInsa. 급여 순으로 가져오시오. 순위를 표시하시오.
-- 1. rownum을 사용한다.
-- 1-1. 급여 순으로 나열한다.
SELECT
    name, buseo, basicpay
FROM tblInsa
ORDER BY basicpay DESC;
-- 1-2. 1을 서브쿼리로 묶고 바깥에 rownum으로 순위를 표시한다.
SELECT
    a.*, rownum
FROM (SELECT name, buseo, basicpay FROM tblInsa ORDER BY basicpay DESC) a;


-- 2. rank() over()를 사용한다.
SELECT
    name, buseo, basicpay,
    rank() over(ORDER BY basicpay DESC) as rnum
FROM tblInsa;

-- 3. dense_rank() over()를 사용한다.
SELECT
    name, buseo, basicpay,
    dense_rank() over(ORDER BY basicpay DESC) as rnum
FROM tblInsa;

-- 4. row_number() over()를 사용한다.
SELECT
    name, buseo, basicpay,
    row_number() over(ORDER BY basicpay DESC) as rnum
FROM tblInsa;

-- 5-1.
-- ORA-00904: "RNUM": invalid identifier
-- FROM이 1번, WHERE가 2번, SELECT가 3번으로 처리되기 때문에 WHERE 시점에서 rnum이 정의되지 않았다.
SELECT
    name, buseo, basicpay,
    row_number() over(ORDER BY basicpay DESC) as rnum
FROM tblInsa
WHERE rnum = 5;

-- 5-2. 위의 query를 subquery로 보냈다.
SELECT * FROM
(SELECT
    name, buseo, basicpay,
    row_number() over(ORDER BY basicpay DESC) as rnum
FROM tblInsa)
WHERE rnum = 5;

-----------------------------------------------------------------------

-- 6. 순위 함수 (+ ORDER BY + PARTITION BY) -> + ORDER BY + GROUP BY라는 소리.
SELECT
    name, buseo, basicpay,
    rank() over(PARTITION BY buseo ORDER BY basicpay DESC) as rnum
FROM tblInsa;
-- rank() over()가 PARTITION BY buseo 별로 rnum을 만들었다!

-- 6-1. 전직원 중 연봉 1위
SELECT *
FROM (SELECT
        name, buseo, basicpay,
        rank() over(ORDER BY basicpay DESC) as rnum
      FROM tblInsa)
WHERE rnum = 1;

-- 6-2. 각 부서별 basicpay 1위
SELECT * FROM (SELECT
    name, buseo, basicpay,
    rank() over(PARTITION BY buseo ORDER BY basicpay DESC) as rnum
FROM tblInsa) WHERE rnum = 1;

-- 6-2-1. 6-2를 ex26의 WITH절을 사용한다면
WITH aaa as (SELECT
                    name, buseo, basicpay,
                    rank() over(PARTITION BY buseo ORDER BY basicpay DESC) as rnum
             FROM tblInsa)
SELECT * FROM aaa WHERE rnum = 1;