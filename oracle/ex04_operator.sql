/*
    ex04_operator.sql
    
    연산자,  Operator
    
    1. 산술 연산자
     - +, -, *, /
     - %가 없다! -> mod() 라는 함수로 제공   
    
    2. 문자열 연산자(concat)
     - + 대신 ||를 쓴다.
     
    3. 비교 연산자
    - >, >=, <, <=
    - =(Java의 ==), <>(Java의 !=)
    - 논리값을 반환하는 boolean이 없기 때문에 명시적인 표현을 할 수 없어서 SELECT절에서는 사용할 수 없다.
    - 조건절에서 사용한다.
    
    4. 비교 연산자2
    - and(Java의 &&), or(Java의 ||), not(Java의 !)
    - 컬럼 리스트(SELECT절)에서는 못 쓴다.
    - 조건절에서 사용한다.
    
    5. 대입 연산자
    - =
    - 컬럼 = 값
    - UPDATE문에서만 사용한다.
    - +=, -= 같은 복합대입연산자는 존재하지 않는다.
    
    6. 삼항 연산자
    - 제어문이 없으니 당연히 없다.
    
    7. 증감 연산자
    - 없다.
    
    8. SQL(에서만 볼 수 있는) 연산자
    - Java: [객체 instanceOf 타입]
    - Oracle: in, between, like, is, ...
*/

SELECT * FROM tblcountry;

SELECT population, area, population + area, population - area, population * area, population / area
FROM tblcountry;

--SELECT name + capital -- ORA-01722 에러 발생.
--FROM tblcountry;

SELECT name || capital
FROM tblcountry;

--SELECT name = capital
--FROM tblcountry;

--SELECT name <> capital
--FROM tblcountry;

--SELECT population > area -- ORA-00923 에러 발생. ORACLE에는 직접적인 boolean의 비교 개념이 없다.
--FROM tblcountry;

/*
    컬럼의 별칭(Alias)
    - 결과 셋의 컬럼명을 원하는 컬럼명으로 바꾸는 기술
    - 원본의 이름이 바뀌는 것이 아님.
    - 결과 셋의 컬럼명이 식별자로 적합하지 않을 때 적합한 식별자로 수정하는 행위.
*/

SELECT name, age - 1 as age, couple
FROM tblmen;

SELECT *
FROM tblwomen;



SELECT name, age, height -- 보통 쓰는 표현
FROM tblmen;

SELECT tblmen.name, tblmen.age, tblmen.height -- 원래 표현
FROM tblmen;

SELECT hr.tblmen.name, hr.tblmen.age, hr.tblmen.height -- DB계정명까지 추가된 진짜 원래 표현
FROM hr.tblmen;


SELECT m.name, m.age, m.height
FROM tblmen m; -- Oracle에서는 테이블의 별?칭을 지을 때 as 키워드를 생략해야 한다.



SELECT tblmen.name, tblmen.age, tblmen.height
FROM tblmen m; -- 테이블의 별칭이 선언됐을 경우 그 문장 안에서는 반드시 테이블명을 별칭으로 불러야 한다.