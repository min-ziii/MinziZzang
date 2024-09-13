/*
    ex05_where절.sql
    
    [WITH <Sub Query>]
    SELECT column_list
    FROM table_name
    [WHERE search_condition]
    [GROUP BY group_by_expression]
    [HAVING search_condition]
    [ORDER_BY order_expression [ASC|DESC]];
    
    WHERE절
    - 레코드(행)을 검색한다.
    - 조건절
    - 조건을 제시한 후 그 조건을 만족하는 레코드(행)만 가져온다.
    - Java Stream: list.stream().filter(조건).foreach(); // list의 모든 요소를 스트림으로부터 가져와서 하나씩 내보내는 방식과 비슷.
    - 조건을 만족하는 레코드로 결과셋을 만든다.
    
    역할
    SELECT column_list -> 어떤 테이블로부터 '지정된 컬럼'만 가져와라.
    FROM table_name -> '어떤 테이블'로부터 데이터를 가져와라.
    WHERE 검색조건; -> 조건 지정. 원하는 레코드만 걸러낸다.
    - 조건을 만족하는 레코드를 가진 결과셋을 만든다.
    
*/

-- 컬럼 5개, 레코드 14개
-- 1레코드 == 1국가 (객체)

SELECT name, capital FROM tblCountry WHERE continent = 'AS';

SELECT * FROM tblcountry;

SELECT * FROM tblcountry WHERE NAME = '대한민국';

SELECT * FROM tblcountry WHERE capital <> '서울';

SELECT * FROM tblcountry WHERE population > 10000;

SELECT * FROM tblMen WHERE (age - 1) >= 25;

SELECT * FROM tblComedian WHERE (height + weight) > 300;

-- tblComedian

--1. 몸무게가 60kg 이상이고 키가 170cm 미만인 사람을 가져오시오.
SELECT * FROM tblcomedian WHERE weight >= 60 and height < 170;
--2. 몸무게가 70kg 이하인 여자만.
SELECT * FROM tblcomedian WHERE weight < 70 and gender = 'f';


-- tblInsa
--3. 부서가 개발부이고 급여가 150만원 이상인 직원을 가져오시오.
SELECT * FROM tblInsa WHERE buseo = '개발부' and basicpay >= 1500000;
--4. 급여 + 수당이 200만원 이상인 직원을 가져오시오.


/*
    between
    - where절에서 조건으로 사용
    - 컬럼명 between 최솟값 and 최댓값
    - 범위조건
    
*/

SELECT * FROM tblinsa WHERE basicpay <= 1200000 and basicpay >= 1000000;
SELECT * FROM tblinsa WHERE basicpay between 1000000 and 1200000; -- ~ 이상 ~ 이하

desc tblInsa; -- tblInsa가 가지는 속성값의 이름과 유형 검색

-- 비교 연산(자료형)
-- 1. 숫자형
SELECT * FROM tblInsa WHERE basicpay >= 1000000 and basicpay <= 1200000;
SELECT * FROM tblInsa WHERE basicpay between 1000000 and 1200000;

-- 2. 문자형
SELECT * FROM tblInsa WHERE name >= '이순신'; -- name.compareTo('이순신') == 한 글자씩 뽑아서 char의 ascii 코드값 비교
SELECT * FROM employees WHERE first_name >= 'J' and first_name <= 'L';
SELECT * FROM employees WHERE first_name between'J' and 'L';

-- 3. 날짜시간형
SELECT * FROM tblInsa WHERE ibsaDate > '2010-01-01';
SELECT * FROM tblInsa WHERE ibsaDate > '100101';
SELECT * FROM tblInsa WHERE ibsaDate >= '2010-01-01' and ibsaDate <= '2010-12-31';
SELECT * FROM tblInsa WHERE ibsaDate between '100101' and '101231';


/*
    in
    - where절에서 조건으로 사용
    - 열거형 조건
    - 컬럼명 in (값, 값, 값, ...)
    - 가독성 향상을 위해 사용
    - between보다 가독성이 더 좋다고??? WOW~~
    
*/

-- tblInsa의 개발부, 총무부, 홍보부 사람을 전부 가져와라.
SELECT * FROM tblInsa WHERE buseo = '개발부' or buseo = '총무부' or buseo = '홍보부';
SELECT * FROM tblInsa WHERE buseo in ('개발부', '총무부', '홍보부');

-- city가 서울이거나 경기 + jikwi가 과장이거나 부장 + basicpay가 250 ~ 300
SELECT * FROM tblInsa WHERE city in ('서울', '경기') and jikwi in ('과장', '부장') and basicpay between 2500000 and 3000000;



/*
    like
    - where절에서 조건으로 사용
    - 패턴 비교
    - 컬럼명 like '패턴 문자열'
    - 정규표현식의 초간단 버전.
    
    패턴 문자열의 구성요소
    1. _: 임의의 한 문자 (.)
    2. %: 임의의 문자 n개 (.*)
    
    
*/

-- 특정 이름인 사람을 보고싶다.
SELECT name FROM tblInsa WHERE name like '김__'; -- 김OO 인 사람
SELECT name FROM tblInsa WHERE name like '_길_'; -- O길O 인 사람
SELECT name FROM tblInsa WHERE name like '__수'; -- OO수 인 사람

SELECT * FROM employees WHERE first_name like 'S_____'; -- S로 시작하는 6글자 이름
SELECT * FROM employees WHERE first_name like 'S%'; -- S로 시작하는 모든 이름

SELECT name FROM tblInsa WHERE name like '김%'; -- 김 한글자도 찾을 수 있다.
SELECT name FROM tblInsa WHERE name like '%길%'; -- 정상길 처럼 %부분에 문자가 없어도 탐색에 걸린다. 조심

SELECT * FROM tblInsa WHERE ssn like '______-2______'; -- 주민번호 뒷자리가 2로 시작하는 사람
SELECT * FROM tblInsa WHERE ssn like '%-2%';


-- 일반적인 검색환경
SELECT * FROM 게시판 where subject = '자바';
SELECT * FROM 게시판 where subject = '%자바%'; -- 자바가 포함된 글


/*
    RDBMS에서의 null은?
    - 컬럼값(셀)이 비어있는 상태
    - null 상수(null이라는 키워드) 제공
    ex) String txt = null;
    - ** 대부분의 언어는 null이 연산의 대상이 될 수 없다. **
    ex) 10 + null = ?
    
    
*/

/*
    null 조건
    - where 절에서 사용
    - 컬럼명 is null;
    - 컬럼명 is not null;
*/

SELECT * FROM tblcountry;

-- 인구수가 미기재된 나라는 어디?
SELECT * FROM tblcountry WHERE population = null; -- 결과가 제대로 안나옴
SELECT * FROM tblcountry WHERE population is null;
-- 인구수가 기재된 나라는 어디?
SELECT * FROM tblcountry WHERE population <> null; -- 결과가 제대로 안나옴
SELECT * FROM tblcountry WHERE population is not null;

