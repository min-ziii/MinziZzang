/*
    ex08_aggregation_function.sql
    
    메서드
    - 함수, 프로시저 등...
    - 클래스 안에서 선언된 함수
    
    함수
    1. 내장 함수(Built-in Function)
    2. 사용자 정의 함수(User Function) -> 표준SQL에서는 할 수 없다. PL/SQL에서는 가능.
    
    집계 함수(Aggregation Function)
    - Java Stream -> count(), sum(), max(), min(), avg()
    - EEEEEEEEEASY -> 나중에 배우는 수업 내용과 결합하면 꽤 어려워짐.
    
    1. count()
    2. sum()
    3. avg()
    4. max()
    5. min()
       
    
*/


/*
    1. count()
    - 결과 테이블의 레코드 수 반환
    - number count(컬럼명)
    - NULL값은 제외
*/
select * FROM tblCountry;
select count(*) FROM tblCountry;

select count(name) FROM tblCountry;         -- 14
select count(population) FROM tblCountry;   -- 13. NULL값은 안 센다.


-- tblInsa에 어떤 부서들이 있고 부서의 수는 총 몇개인가?
SELECT count(DISTINCT buseo) FROM tblInsa;



-- tblComedian에 남자의 수와 여자의 수는?
SELECT COUNT(*) FROM tblComedian WHERE gender = 'm';
SELECT COUNT(*) FROM tblComedian WHERE gender = 'f';

-- 남자의 수와 여자의 수를 하나의 테이블에서 같이 볼 수 있게 하려면? (*중요*)
SELECT 
    Count(case
        when gender = 'm' then 1 -- 값이 있냐 없냐만 따질거니까
    end),
    Count(case
        when gender = 'f' then 1 -- 뭐라고 부를지는 크게 중요하지 않다.
    end)
FROM tblComedian;


-- tblInsa의 기획부, 총무부, 개발부 직원 수는 각각 몇 명인지 하나의 테이블에서 보고싶다.
SELECT
    COUNT(case
        when buseo = '기획부' then 1
    end) as 기획부인원수,
    COUNT(case
        when buseo = '총무부' then 1
    end) as 총무부인원수,
    COUNT(case
        when buseo = '개발부' then 1
    end) as 개발부인원수,
    COUNT(case
        when buseo not in ('기획부', '총무부', '개발부') then 1
    end) as 나머지,
    COUNT(*) as 총직원수
FROM tblInsa;



SELECT
    name as "Employee Name",
    buseo as "select" -- 억지로 공백을 넣어서 alias로 쓸 순 있지만... 비추
FROM tblInsa;


/*
    2. sum()
    - 해당 컬럼값의 합을 구한다.
    - number sum(컬럼명)
    - 숫자형에만 적용할 수 있다.
*/
SELECT * FROM tblCountry;

SELECT count(area) FROM tblCountry; -- 레코드 개수
SELECT sum(area) FROM tblCountry; -- 수치의 합
SELECT sum(name) FROM tblCountry; -- ORA-01722 오류: invalid number
SELECT sum(ibsadate) FROM tblCountry; -- ORA-00932 오류: inconsistent datatypes

SELECT
    sum(basicpay) as "지출 급여 합",
    sum(sudang) as "지출 수당 합",
    sum(basicpay) + sum(sudang) as "총 지출",
    sum(basicpay + sudang) as "총 지출" -- 3번줄 대신 이렇게
FROM tblInsa;

/*
    3. avg()
    - 해당 컬럼값의 평균값을 구한다.
    - number avg(컬럼명)
    - 숫자형에만 적용 가능
    - NULL이 제외됨
*/

-- tblInsa 에서 평균 급여를 계산하라.
SELECT sum(basicpay) / 60 FROM tblInsa; -- 약 1556526원
SELECT sum(basicpay) / count(*) FROM tblInsa; -- 약 1556526원
SELECT avg(basicpay) FROM tblInsa; -- 약 1556526원

-- tblCountry에서 14개국의 평균 인구수를 구하라.
SELECT sum(population) / 14 FROM tblCountry; -- 14475.xx
SELECT sum(population) / count(*) FROM tblCountry; -- 14475.xx
SELECT avg(population) FROM tblCountry; -- 15588.xx (값이 다르다!)

SELECT count(*) FROM tblCountry; -- 14
SELECT count(population) FROM tblCountry; -- 13 (인구정보가 없는 케냐가 제외됨)
SELECT sum(population) / count(population) FROM tblCountry; -- 15588.xx

-- 회사에서 성과급을 지원하려고 보니 1팀의 능력만으로 인해 발생한 성과급이었다.
-- 사장: 다같은 사원인데 나눠먹자 -> 총성과급 / 모든직원수 -> sum() / count(*)
-- 사장: 그건 아닌가? 차등지급ㄱ  -> 총성과급 / 1팀직원수  -> sum() / count(1팀) = avg()

--... 어떤 식의 평균값이 필요한건지 한 번 생각해보고 사용해야 한다.

/* 
    4. max()
    - 최댓값 반환
    - object max(컬럼명)
    
    5. min()
    - 최솟값 반환
    - object min(컬럼명)
    
    - 컬럼명 + 반환값 -> 숫자형, 문자형, 날짜형
*/

SELECT max(basicpay), min(basicpay) FROM tblInsa; -- 숫자형
SELECT max(name), min(name) FROM tblInsa; -- 문자형
SELECT max(ibasdate), min(ibsadate) FREM tblInsa; -- 날짜형

SELECT
    count(*) as 직원수,
    sum(basicpay) as 총급여합,
    avg(basicpay) as 평균급여,
    max(basicpay) as 최고급여,
    min(Basicpay) as 최저급여
FROM tblInsa;


-- 집계함수 사용 시 주의점과 집계함수의 특징. (*)
-- 컬럼 리스트(SELECT절)에서는 집계 함수와 일반컬럼을 동시에 사용할 수 없다.
-- WHERE절에서도 개인대상 조건과 집합대상 조건을 동시에 쓸 수 없다.

-- 요구사항) 인사테이블에서 직원이름과 총직원수를 가져와주쇼.
SELECT name, count(*) FROM tblInsa; -- ORA-00937 오류: not a single-group group function
-- 따로는 가능한데 같이 쓸 수가 없다.

-- 요구사항) 평균 급여보다 더 많이 받는 직원
SELECT avg(basicpay) from tblInsa; -- 약 1556526원

SELECT * FROM tblInsa WHERE baisicpay >= avg(basicpay); -- ORA-00934 오류: group function is not allowed here
-- Oracle 설계 방식때문에 개인대상 조건과 집합대상 조건을 동시에 쓸 수 없다.
-- 서브쿼리를 사용하면 이 문제를 해결할 수 있다.