-- distinct

-- 1.employees
-- 직업이 어떤것들이 있는지 가져오시오. > job_id
SELECT DISTINCT job_id FROM employees;

-- 2.employees
-- 고용일이 2002~2004년 사이인 직원들은 어느 부서에서 근무합니까? > hire_date + department_id
SELECT DISTINCT department_id FROM employees WHERE hire_date between '20020101' and '20041231';

-- 3.employees
-- 급여가 5000불 이상인 직원들은 담당 매니저가 누구? > manager_id
SELECT DISTINCT manager_id FROM employees WHERE salary >= 5000;

-- 4.tblInsa
-- 남자 직원 + 80년대생들의 직급은 무엇입니까? > ssn + jikwi
SELECT DISTINCT jikwi FROM tblInsa WHERE ssn like '8%' and ssn like '%-1%';

-- 5.tblInsa
-- 수당 20만원 넘는 직원들은 어디 삽니까? > sudang + city
SELECT DISTINCT city FROM tblInsa WHERE sudang >= 200000;

-- 6.tblInsa
--연락처가 아직 없는 직원은 어느 부서입니까? > null + buseo
SELECT DISTINCT buseo FROM tblInsa WHERE tel is null;
