/*
    ex19_subquery.sql
    
    SubQuery
    - 하나의 SQL 안에 또다른 SQL을 사용하는 기술
    
    1. MainQuery
        - 일반 SQL 구문(SELECT, UPDATE, INSERT, CREATE 등...)
        - 또다른 SQL을 포함하는 바깥쪽 SQL
        
    2. SubQuery
         - 하나의 SQL 안에 포함된 또다른 SQL을 지칭
         - SELECT를 main으로 두고 그 안에 SELECT를 sub로 넣는 경우 (이 쪽이 사용 빈도가 가장 높음)
         - INSERT를 main으로 두고 그 안에 SELECT를 sub로 넣는 경우
         - UPDATE를 main으로 두고 그 안에 SELECT를 sub로 넣는 경우
         - DELETE를 main으로 두고 그 안에 SELECT를 sub로 넣는 경우
         - 서브 쿼리 삽입 위치 -> 대부분의 절에 가능 (SELECT, WHERE, ORDER BY, ...)
                               -> 값을 넣을 수 있는 곳이면 사용 가능
                               -> 상수 or 컬럼명 or 함수 호출 or 서브 쿼리
                               
*/

-- tblCountry에서 인구수가 가장 많은 나라의 이름은? (=중국)
SELECT max(population) FROM tblCountry; -- 120660
SELECT name FROM tblCountry WHERE population = 120660;
--WHERE절(개인에 대한 조건)에서는 집계함수를 사용할 수 없다.
SELECT name FROM tblCountry WHERE population = max(population);

SELECT name -- 메인 쿼리
FROM tblCountry
WHERE population = (SELECT max(population) -- 서브 쿼리
                    FROM tblCountry);
                    
-- tblComedian에서 몸무게가 가장 많이, 가장 적게 나가는 사람의 정보는?
SELECT * FROM tblComedian WHERE weight = (SELECT max(weight) FROM tblComedian);
SELECT * FROM tblComedian WHERE weight = (SELECT min(weight) FROM tblComedian);

-- tblInsa에서 평균 급여보다 많이 받는 직원은?
SELECT * FROM tblInsa WHERE basicpay >= (SELECT avg(basicpay) FROM tblInsa);

-- tblMen, tblWomen
SELECT * FROM tblMen;
SELECT * FROM tblWomen;

-- 남자(166cm)의 여자친구 키는?
SELECT height FROM tblWomen WHERE couple = (SELECT name FROM tblMen WHERE height = 166);


/*
    서브쿼리 삽입 위치
    1. 조건절 -> 비교값으로 사용하기 위해
        a. 반환값이 1행 1열 -> 단일값 반환 -> 상수 취급 -> 값 1개
        b. 반환값이 N행 1열 -> 다중값 반환 -> 열거형 비교를 위해 in 사용
        c. 반환값이 1행 N열 -> 다중값 반환 -> 그룹 비교 -> N개 컬럼 : N개 컬럼
        d. 반환값이 N행 N열 -> 다중값 반환 -> b+c -> in + 
        
    2.컬럼리스트 -> 결과셋의 컬럼값으로 사용
        - 반드시 1행 1열을 반환하는 서브쿼리만 사용할 수 있다. (*)
            > Scalar 쿼리(원자값 반환)
        a. 정적 서브 쿼리: 모든 행에 동일한 값 반환
        b. 상관 서브 쿼리: 메인 쿼리의 일부 컬럼값을 서브쿼리에서 사용 (컬럼리스트 대부분의 서브 쿼리는 상관 서브 쿼리이다)
    
    3. FROM절에서 사용
        - 서브쿼리의 결과셋을 또 하나의 테이블이라고 생각하고 메인 쿼리 실행
        - 인라인 뷰(Inline biew)
*/

-- 1-b. 급여를 260만원 이상 받는 직원이 근무하는 부서의 직원들 명단을 가져오시오.
-- ORA-01427: single-row subquery returns more than one row
SELECT * FROM tblInsa WHERE buseo = (SELECT buseo FROM tblInsa WHERE basicpay >= 2600000);

SELECT * FROM tblInsa WHERE buseo in (SELECT buseo FROM tblInsa WHERE basicpay >= 2600000);

-- 1-c. '홍길동'과 같은 지역(city), 같은 부서(buseo)인 직원의 명단
SELECT * FROM tblInsa WHERE city = (SELECT city FROM tblInsa WHERE name = '홍길동') and buseo = (SELECT buseo FROM tblInsa WHERE name = '홍길동');
SELECT * FROM tblInsa WHERE (city, buseo) = (SELECT city, buseo FROM tblInsa WHERE name = '홍길동');

-- 1-d. 급여가 260 이상인 직원과 같은 지역, 같은 부서인 직원 명단
SELECT * FROM tblInsa WHERE (buseo, city) in (SELECT buseo, city FROM tblInsa WHERE basicpay >= 2600000);

-- 2.
SELECT
    name, buseo, basicpay,
    (SELECT avg(basicpay) FROM tblInsa)
FROM tblInsa;

-- ORA-01427: single-row subquery returns more than one row
SELECT
    name, buseo, basicpay,
    (SELECT jikwi FROM tblInsa)
FROM tblInsa;

-- ORA-00913: too many values
SELECT
    name, buseo, basicpay,
    (SELECT jikwi, sudang FROM tblInsa WHERE num = 1001)
FROM tblInsa;


SELECT
    name, buseo, basicpay,
    (SELECT avg(basicpay) FROM tblInsa WHERE buseo = a.buseo)
FROM tblInsa a;

-- 남자(이름, 키, 몸무게)
SELECT name, height, weight, couple,
    (SELECT height FROM tblWomen WHERE name = couple)
FROM tblMen;