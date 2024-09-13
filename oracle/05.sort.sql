-- 정렬


-- 1. employees. 전체 이름(first_name + last_name)이 가장 긴 -> 짧은 사람 순으로 정렬해서 가져오기
--    > 컬럼 리스트 > fullname(first_name + last_name), length(fullname)
SELECT (first_name || last_name) as fullname
FROM employees
ORDER BY length(fullname) DESC;


-- 2. employees. 전체 이름(first_name + last_name)이 가장 긴 사람은 몇글자? 가장 짧은 사람은 몇글자? 평균 몇글자?
--    > 컬럼 리스트 > 숫자 컬럼 3개
SELECT max(length(first_name || last_name)), min(length(first_name || last_name)), avg(length(first_name || last_name))
FROM employees;

-- 3. employees. last_name이 4자인 사람들의 first_name을 가져오기
--    > 컬럼 리스트 > first_name, last_name
--    > 정렬(first_name, 오름차순)
SELECT
    first_name, last_name
FROM employees
WHERE length(last_name) = 4
ORDER BY first_name ASC;


