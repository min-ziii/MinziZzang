-- subquery
SELECT * FROM employees;
SELECT * FROM departments;
SELECT * FROM locations;
SELECT * FROM tblAddressBook;


-- 1. employees. 'Munich' 도시에 위치한 부서에 소속된 직원들 명단?
-- a. munich은 locations 테이블의 city에 있는 값이다. -> munich의 location_id는?
SELECT location_id
FROM locations
WHERE city = 'Munich';

-- b. 부서를 확인하려면 departments.location_id와 locations.location_id를 대조해야 한다. -> location_id가 2700인 departments의 department_id는?
SELECT department_id
FROM departments
WHERE location_id = (SELECT location_id FROM locations WHERE city = 'Munich');

-- c. 직원 명단을 확인하려면 employees.department_id와 departments.department_id를 대조해야 한다. -> department_id가 70인 직원들의 명단을 employees에서 찾는다.
SELECT *
FROM employees
WHERE department_id = (SELECT department_id FROM departments WHERE location_id = (SELECT location_id FROM locations WHERE city = 'Munich'));




-- 2. tblMen. tblWomen. 서로 짝이 있는 사람 중 남자와 여자의 정보를 모두 가져오시오.
--    [남자]        [남자키]   [남자몸무게]     [여자]    [여자키]   [여자몸무게]
--    홍길동         180       70              장도연     177        65
--    아무개         175       null            이세영     163        null
--    ..
SELECT name, height, weight
FROM tblwomen
WHERE couple IS NOT NULL;

SELECT
    name as 남자,
    height as 남자키,
    weight as 남자몸무게,
    (SELECT name FROM tblWomen WHERE name = tblMen.couple) as 여자,
    (SELECT height FROM tblWomen WHERE name = tblMen.couple) as 여자키,
    (SELECT weight FROM tblWomen WHERE name = tblMen.couple) as 여자몸무게
FROM tblMen;



-- 3. tblAddressBook. 가장 많은 사람들이 가지고 있는 직업은 주로 어느 지역 태생(hometown)인가?

SELECT max(count(*)) -- 332, 학생 숫자
FROM tblAddressBook
GROUP BY job;


SELECT job -- 학생이 332명으로 가장 많은 사람들이 가지고 있는 직업이다.
FROM tblAddressBook
GROUP BY job
HAVING count(*) = (SELECT max(count(*)) FROM tblAddressBook GROUP BY job);

SELECT hometown, count(hometown) -- 서울의 학생 수가 167명으로 가장 많다.
FROM tblAddressBook
WHERE job = (SELECT job FROM tblAddressBook GROUP BY job HAVING count(*) = (SELECT max(count(*)) FROM tblAddressBook GROUP BY job))
GROUP BY hometown
ORDER BY count(hometown) DESC;


-- 4. tblAddressBook. 이메일 도메인들 중 평균 아이디 길이가 가장 긴 이메일 사이트의 도메인은 무엇인가?


-- 5. tblAddressBook. 평균 나이가 가장 많은 출신(hometown)들이 가지고 있는 직업 중 가장 많은 직업은?


-- 6. tblAddressBook. 남자 평균 나이보다 나이가 많은 서울 태생 + 직업을 가지고 있는 사람들을 가져오시오.


-- 7. tblAddressBook. gmail.com을 사용하는 사람들의 성별 > 세대별(10,20,30,40대) 인원수를 가져오시오.


-- 8. tblAddressBook. 가장 나이가 많으면서 가장 몸무게가 많이 나가는 사람과 같은 직업을 가지는 사람들을 가져오시오.


-- 9. tblAddressBook.  동명이인이 여러명 있습니다. 이 중 가장 인원수가 많은 동명이인의 명단을 가져오시오.(모든 이도윤)


-- 10. tblAddressBook. 가장 사람이 많은 직업의(332명) 세대별 비율을 구하시오.
--    [10대]       [20대]       [30대]       [40대]
--    8.7%        30.7%        28.3%        32.2%

