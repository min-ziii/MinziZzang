/*
    ex07_order.sql
        
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
    ORDER BY 정렬기준   -> 결과셋의 레코드 순서를 정한다.
    
    ORDER BY절
    - 정렬
    - ORDER BY 컬럼명      -- ascending
    - ORDER BY 컬럼명 ASC  -- ascending
    - ORDER BY 컬럼명 DESC -- descending
    
*/

SELECT * FROM tblInsa;

SELECT * FROM tblInsa ORDER BY basicpay;

SELECT * FROM tblInsa ORDER BY sudang DESC;


-- 자료형 확인
SELECT * FROM tblInsa ORDER BY basicpay;    -- 숫자형 (가능)
SELECT * FROM tblInsa ORDER BY name;        -- 문자형 (가능)
SELECT * FROM tblInsa ORDER BY ibsadate;    -- 날짜시간형 (가능)

-- 다중 정렬
SELECT * FROM tblInsa ORDER BY buseo ASC; -- 1차 정렬
SELECT * FROM tblInsa ORDER BY buseo ASC, jikwi ASC; -- 2차 정렬
SELECT * FROM tblInsa ORDER BY buseo ASC, jikwi ASC, basicpay DESC; -- 3차 정렬

-- Java는 첨자가 0부터 시작한다. (컴파일 언어들)
-- SQL은 첨자가 1부터 시작한다. (스크립트 언어들)
SELECT num, name, buseo, jikwi
FROM tblInsa
ORDER BY 3 ASC; -- 컬럼리스트의 index로도 정렬 기준을 선정할 수 있지만, 권장하지 않는다. (3 == buseo)

-- 가공된 값을 정렬 기준으로 사용
SELECT * FROM tblInsa ORDER BY basicpay + sudang DESC; -- 실수령액 기준 정렬

SELECT name, ssn,
    case
        when ssn like '%-1%' then '남자'
        when ssn like '%-2%' then '여자'
    end as gender
FROM tblInsa
ORDER BY case
        when ssn like '%-1%' then '남자'
        when ssn like '%-2%' then '여자'
         end ASC;
    
SELECT name, ssn,
    case
        when ssn like '%-1%' then '남자'
        when ssn like '%-2%' then '여자'
    end as gender
FROM tblInsa
ORDER BY gender ASC; -- ORDER BY의 case-end 문을 미리 설정한 alias로 대체


-- 직위순 정렬: 부장-과장-대리-사원 순으로 정렬
-- 글자로는 정렬이 안 되니 직위 별로 숫자를 설정하고 그 숫자를 비교한다.
SELECT name, jikwi
    
FROM tblInsa
ORDER BY case
        when jikwi = '부장' then 1
        when jikwi = '과장' then 2
        when jikwi = '대리' then 3
        when jikwi = '사원' then 4
    end ASC;