-- tblHousekeeping 테이블 문제
SELECT * FROM tblHousekeeping;

-- 1. 각 날짜별로 구매한 물품의 총 개수와 총 금액을 구하시오.
SELECT
    buydate, sum(qty), sum(price)
FROM tblHousekeeping
GROUP BY buydate
ORDER BY buydate;

-- 2. 가격대별로 구매한 물품의 수를 구하시오. (가격대는 1000원 단위로 나누어 주세요)
SELECT
    sum(qty),
    floor(price / 1000) * 1000 as price_range
FROM tblHousekeeping
GROUP BY floor(price / 1000) * 1000
ORDER BY floor(price / 1000) * 1000;

-- 3. 각 물품별로 총 구매 금액을 구하고, 구매 금액이 높은 순으로 정렬하시오.
SELECT
    item, sum(price * qty)
FROM tblHousekeeping
GROUP BY item
ORDER BY sum(price) DESC;

-- 4. 각 주차별로 구매한 물품의 총 개수와 총 금액을 구하시오. (주는 일요일부터 시작합니다)
SELECT
    to_char(buydate, 'yyyy-ww') as the_first_week_of_the_year, sum(qty), sum(price * qty)
FROM tblHousekeeping
GROUP BY to_char(buydate, 'yyyy-ww')
ORDER BY to_char(buydate, 'yyyy-ww');

-- 5. 구매 수량이 10개 이상인 물품들의 평균 가격을 구하시오.
SELECT avg(price)
FROM tblHousekeeping
WHERE qty >=10;

-- 6. 각 물품별로 구매 횟수를 구하고, 구매 횟수가 1회 초과인 물품만 출력하시오.
SELECT item, qty
FROM tblHousekeeping
WHERE qty > 1;

-- 7. 구매 날짜별로 가장 비싼 물품의 가격을 구하시오.
SELECT buydate, max(price)
FROM tblHousekeeping
GROUP BY buydate
ORDER BY buydate ASC;

-- 8. 각 물품 종류별로 총 구매 금액을 구하고, 10만원 이상 구매한 물품 종류만 출력하시오.
SELECT item, sum(price)
FROM tblHousekeeping
WHERE (qty * price) >= 100000
GROUP BY item;

-- 9. 요일별로 평균 구매 금액을 구하고, 평균 금액이 높은 순으로 정렬하시오.
SELECT to_char(buydate, 'day'), round(avg(price))
FROM tblHousekeeping
GROUP BY to_char(buydate, 'day')
ORDER BY round(avg(price)) DESC;

-- 10. 월별로 구매한 물품의 총 개수와 총 금액을 구하시오.
SELECT to_char(buydate, 'yyyy-mm') as month, sum(qty), sum(price * qty)
FROM tblHousekeeping
GROUP BY to_char(buydate, 'yyyy-mm')
ORDER BY month ASC;


-- tblInsa 테이블 문제
SELECT * FROM tblInsa;

-- 1. 각 부서별로 직원 수와 평균 급여(기본급+수당)를 구하시오.
SELECT buseo, count(buseo), round(avg(basicpay + sudang)) as avg_salary
FROM tblInsa
GROUP BY buseo;

-- 2. 직위별로 직원 수와 최고 급여, 최저 급여를 구하시오.
SELECT jikwi, count(jikwi), max(basicpay + sudang), min(basicpay + sudang)
FROM tblInsa
GROUP BY jikwi;

-- 3. 입사년도별로 직원 수를 구하고, 가장 많은 직원이 입사한 연도 3개를 구하시오.
SELECT to_char(ibsadate, 'yyyy') as ibsa_year, count(*)
FROM tblInsa
GROUP BY to_char(ibsadate, 'yyyy')
ORDER BY count(*) DESC;

-- 4. 각 도시별로 직원 수와 평균 급여를 구하고, 직원 수가 5명 이상인 도시만 출력하시오.
SELECT city, count(*), round(avg(basicpay + sudang)) as avg_salary
FROM tblInsa
GROUP BY city
HAVING count(*) >= 5;


-- 5. 부서별, 직위별로 평균 급여를 구하고, 부서와 직위 순으로 정렬하시오.
SELECT buseo, jikwi, round(avg(basicpay + sudang)) as avg_salary
FROM tblInsa
GROUP BY buseo, jikwi
ORDER BY buseo, jikwi;

-- 6. 나이대별(20대, 30대, 40대 등)로 직원 수와 평균 급여를 구하시오.
-- to_number(to_char(sysdate, 'yyyy')) - to_number(substr(ssn, 1, 2));
SELECT
    case
        when to_number(to_char(sysdate, 'yyyy')) - (to_number(substr(ssn, 1, 2)) + 1900) between 20 and 29 then '20대'
        when to_number(to_char(sysdate, 'yyyy')) - (to_number(substr(ssn, 1, 2)) + 1900) between 30 and 39 then '30대'
        when to_number(to_char(sysdate, 'yyyy')) - (to_number(substr(ssn, 1, 2)) + 1900) between 40 and 49 then '40대'
        when to_number(to_char(sysdate, 'yyyy')) - (to_number(substr(ssn, 1, 2)) + 1900) between 50 and 59 then '50대'
    end as generation,
    count(*) as amount_of_employees, round(avg(basicpay + sudang)) as avg_salary
FROM tblInsa
GROUP BY case
            when to_number(to_char(sysdate, 'yyyy')) - (to_number(substr(ssn, 1, 2)) + 1900) between 20 and 29 then '20대'
            when to_number(to_char(sysdate, 'yyyy')) - (to_number(substr(ssn, 1, 2)) + 1900) between 30 and 39 then '30대'
            when to_number(to_char(sysdate, 'yyyy')) - (to_number(substr(ssn, 1, 2)) + 1900) between 40 and 49 then '40대'
            when to_number(to_char(sysdate, 'yyyy')) - (to_number(substr(ssn, 1, 2)) + 1900) between 50 and 59 then '50대'
         end
ORDER BY generation ASC;



-- 7. 입사월별로 직원 수와 평균 급여를 구하고, 평균 급여가 높은 순으로 정렬하시오.
SELECT to_char(ibsadate, 'yyyy-mm'), count(*), round(avg(basicpay + sudang)) as avg_salary
FROM tblInsa
GROUP BY to_char(ibsadate, 'yyyy-mm')
ORDER BY avg_salary DESC;

-- 8. 부서별로 가장 높은 급여를 구하시오.
SELECT buseo, max(basicpay + sudang) as max_salary
FROM tblInsa
GROUP BY buseo;

-- 9. 직위별로 총 급여 합계를 구하고, 총 급여가 5000만원 이상인 직위만 출력하시오.
SELECT jikwi, sum(basicpay + sudang)
FROM tblInsa
GROUP BY jikwi
HAVING sum(basicpay + sudang) >= 50000000;

-- 10. 입사년도별, 부서별로 직원 수를 구하고, 직원 수가 5명 이상인 그룹만 출력하시오.
SELECT to_char(ibsadate, 'yyyy'), buseo, count(*)
FROM tblInsa
GROUP BY to_char(ibsadate, 'yyyy'), buseo
HAVING count(*) >= 5;



-- tblMen, tblWomen 테이블 문제
SELECT * FROM tblMen;
SELECT * FROM tblWomen;

-- 1. 남녀별로 평균 나이와 평균 키를 구하시오.
SELECT round(avg(age)), round(avg(height)) FROM tblMen;
SELECT round(avg(age)), round(avg(height)) FROM tblWomen;

-- 2. 커플 상태(연인 있음, 없음)별로 남녀 수를 구하시오.
SELECT count(*) FROM tblMen WHERE couple is NOT NULL;
SELECT count(*) FROM tblWomen WHERE couple is NOT NULL;

-- 3. 남녀별로 키 구간(160cm 미만, 160-170cm, 170-180cm, 180cm 이상)에 속하는 사람 수를 구하시오.
SELECT
    case
        when height < 160 then '160cm 미만'
        when height between 160 and 170 then '160-170cm'
        when height between 170 and 180 then '170-180cm'
        else '180cm 이상'
    end as height_check,
    count(*)
FROM tblMen
GROUP BY case
            when height < 160 then '160cm 미만'
            when height between 160 and 170 then '160-170cm'
            when height between 170 and 180 then '170-180cm'
            else '180cm 이상'
         end;
         
SELECT
    case
        when height < 160 then '160cm 미만'
        when height between 160 and 170 then '160-170cm'
        when height between 170 and 180 then '170-180cm'
        else '180cm 이상'
    end as height_check,
    count(*)
FROM tblWomen
GROUP BY case
            when height < 160 then '160cm 미만'
            when height between 160 and 170 then '160-170cm'
            when height between 170 and 180 then '170-180cm'
            else '180cm 이상'
         end;

-- 5. 남녀별로 가장 높은 체중을 구하시오.
SELECT '남자' AS gender, MAX(weight) AS max_weight
FROM tblMen
WHERE weight IS NOT NULL;

SELECT '여자' AS gender, MAX(weight) AS max_weight
FROM tblWomen
WHERE weight IS NOT NULL;

-- 6. 키 구간별(10cm 단위)로 남녀 수를 구하고, 남녀 수가 많은 순으로 정렬하시오.
SELECT floor(height / 10) * 10 || '대', count(*)
FROM tblMen
WHERE height is NOT NULL
GROUP BY floor(height / 10)
ORDER BY count(*) DESC;

SELECT floor(height / 10) * 10 || '대', count(*)
FROM tblWomen
WHERE height is NOT NULL
GROUP BY floor(height / 10)
ORDER BY count(*) DESC;



-- 7. 남녀별로 나이대(20대, 30대 등)에 따른 평균 키를 구하시오.
SELECT
    case
        when floor(age / 10) = 2 then '20대'
        when floor(age / 10) = 3 then '30대'
    end
    ,avg(height)
FROM tblMen
GROUP BY
    case
        when floor(age / 10) = 2 then '20대'
        when floor(age / 10) = 3 then '30대'
    end;
    
SELECT
    case
        when floor(age / 10) = 2 then '20대'
        when floor(age / 10) = 3 then '30대'
    end
    ,avg(height)
FROM tblWomen
GROUP BY
    case
        when floor(age / 10) = 2 then '20대'
        when floor(age / 10) = 3 then '30대'
    end;

-- 9. 남녀별로 체중이 가장 적게 나가는 사람과 가장 많이 나가는 사람의 몸무게 차이를 구하시오.
SELECT max(weight) - min(weight)
FROM tblMen;

SELECT max(weight) - min(weight)
FROM tblWomen;

-- 10. 남녀 모두에서 가장 긴 이름의 글자 수를 구하시오.
SELECT max(length(name)) as longest_name
FROM tblMen;

SELECT max(length(name)) as longest_name
FROM tblWomen;


-- tblTodo 테이블 문제
SELECT * FROM tblTodo;

-- 1. 각 날짜별로 등록된 할 일의 수와 완료된 할 일의 수를 구하시오.
SELECT adddate, count(adddate), count(completedate)
FROm tblTodo
GROUP BY adddate
ORDER BY adddate ASC;

-- 2. 요일별로 할 일 등록 수를 구하고, 가장 많이 등록된 요일부터 정렬하시오.
SELECT to_char(adddate, 'day') as week_of_day, count(adddate)
FROM tblTodo
GROUP BY to_char(adddate, 'day')
ORDER BY count(adddate) DESC;

-- 3. 각 주차별로 완료된 할 일의 수와 완료되지 않은 할 일의 수를 구하시오.
SELECT to_char(adddate, 'yyyy-ww') as week_of_the_year, count(completedate), count(adddate)
FROM tblTodo
GROUP BY to_char(adddate, 'yyyy-ww')
ORDER BY week_of_the_year ASC;

-- 4. 완료 여부에 따라 평균 소요 시간(완료시간 - 등록시간)을 구하시오.
SELECT round(completedate - adddate) * 24 as duration_hour
FROM tblTodo;

-- 5. 각 달별로 등록된 할 일의 수와 완료율(완료된 할 일 / 전체 할 일)을 구하시오.
SELECT to_char(adddate, 'yyyy-mm') as month, (count(completedate) / count(adddate)) * 100 as complete_percentage
FROM tblTodo
GROUP BY to_char(adddate, 'yyyy-mm')
ORDER BY month;

-- 6. 등록 시간대별(0-6시, 6-12시, 12-18시, 18-24시)로 할 일의 수를 구하시오.
SELECT
    case
        when to_number(to_char(adddate, 'hh24')) between 0 and 5 then '0-6시'
        when to_number(to_char(adddate, 'hh24')) between 6 and 11 then '6-12시'
        when to_number(to_char(adddate, 'hh24')) between 12 and 17 then '12-18시'
        else '18-24시'
    end as add_time, count(*)
FROM tblTodo
GROUP BY case
            when to_number(to_char(adddate, 'hh24')) between 0 and 5 then '0-6시'
            when to_number(to_char(adddate, 'hh24')) between 6 and 11 then '6-12시'
            when to_number(to_char(adddate, 'hh24')) between 12 and 17 then '12-18시'
            else '18-24시'
         end;

-- 7. 완료된 일 중에서, 소요 시간이 가장 긴 일의 소요 시간을 구하시오.
SELECT round(max(completedate-adddate) * 24) as hours_to_complete
FROM tblTodo
WHERE completedate IS NOT NULL;

-- 8. 등록 후 완료까지 걸린 기간별(당일, 1일, 2일 이상)로 할 일의 수를 구하시오.
SELECT
    case
        when floor(completedate - adddate) = 0 then 0
        when floor(completedate - adddate) = 1 then 1
        else 2
    end as days_until_complete, count(*)
FROM tblTodo
GROUP BY case
            when floor(completedate - adddate) = 0 then 0
            when floor(completedate - adddate) = 1 then 1
            else 2
         end
ORDER BY days_until_complete ASC;

-- 9. 각 날짜별로 가장 늦게 완료된 시간을 구하시오.
SELECT to_char(completedate, 'yyyy-mm-dd'), max(to_char(completedate, 'hh24:mi:ss')) as latest_complete_time
FROM tblTodo
WHERE completedate IS NOT NULL
GROUP BY to_char(completedate, 'yyyy-mm-dd')
ORDER BY to_char(completedate, 'yyyy-mm-dd');

-- 10. 완료 여부에 따라 제목의 평균 글자 수를 구하시오.
SELECT 
    case
        when completedate IS NULL then 'completed' else 'incompleted'
    end
,avg(length(title))
FROM tblTodo
GROUP BY case
        when completedate IS NULL then 'completed' else 'incompleted'
    end;
