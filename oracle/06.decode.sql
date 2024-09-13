-- decode


-- 1. tblInsa. 부장 몇명? 과장 몇명? 대리 몇명? 사원 몇명?
SELECT
    count(decode(jikwi, '부장', 1)) as 부장인원,
    count(decode(jikwi, '과장', 1)) as 과장인원,
    count(decode(jikwi, '대리', 1)) as 대리인원,
    count(decode(jikwi, '사원', 1)) as 사원인원
FROM tblInsa;


-- 2. tblInsa. 간부(부장, 과장) 몇명? 사원(대리, 사원) 몇명?
SELECT
    count(decode(jikwi, '부장', 1, '과장', 2)) as 간부,
    count(decode(jikwi, '대리', 1, '사원', 1)) as 사원
FROM tblInsa;


-- 3. tblInsa. 기획부, 영업부, 총무부, 개발부의 각각 평균 급여?

SELECT
    round(avg(decode(buseo, '기획부', basicpay))) as 기획부,
    round(avg(decode(buseo, '영업부', basicpay))) as 영업부,
    round(avg(decode(buseo, '총무부', basicpay))) as 총무부,
    round(avg(decode(buseo, '개발부', basicpay))) as 개발부
FROM tblInsa;

--총무 개발 영업 기획 인사 자재 홍보


-- 4. tblInsa. 남자 직원 가장 나이가 많은 사람이 몇년도 태생? 여자 직원 가장 나이가 어린 사람이 몇년도 태생?
SELECT 
    case
        when substr(ssn, 8, 1) = 1 then min(to_number(substr(ssn, 1, 2)) + 1900)
        when substr(ssn, 8, 1) = 2 then max(to_number(substr(ssn, 1, 2)) + 1900)
    end
FROM tblInsa
GROUP BY substr(ssn, 8, 1);


SELECT 
    decode(ssn, '%-1', min(to_number(substr(ssn, 1, 2)) + 1900), '%-2', max(to_number(substr(ssn, 1, 2)) + 1900)) 
FROM tblInsa
GROUP BY substr(ssn, 1, 2);

