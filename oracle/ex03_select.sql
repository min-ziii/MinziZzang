/*

    ex03_select.sql
    
    SQL (SEQUEL에서 상표권 분쟁을 피하기 위해 이름 변경)
    - Query
    
    SELECT문
    - DML, DQL
    - 테이블로부터 데이터를 읽어온다. == C[R]UD
    - "DB(SQL)는 SELECT로 시작해서 SELECT로 끝난다."
    
    *각각의 절은 역할과 실행순서가 정해져 있다.*
    [WITH <Sub Query>] ([] 붙은 구문은 써도 되고 안 써도 됨)
    SELECT column_list
    FROM table_name
    [WHERE search_condition]
    [GROUP BY group_by_expression]
    [HAVING search_condition]
    [ORDER_BY order_expression [ASC|DESC]];
    
    SELECT column_list -> 어떤 테이블로부터 '지정된 컬럼'만 가져와라.
    FROM table_name; -> '어떤 테이블'로부터 데이터를 가져와라.
    
*/

-- SELECT문의 결과는 항상 테이블이다. -> 결과 테이블 혹은 "결과 셋(Result Set)"이라고 부름
SELECT * FROM tblcountry;

SELECT name, capital, population, continent, area
FROM tblcountry;

SELECT name
FROM tblcountry;

SELECT name, capital
FROM tblcountry;
                        --^v 순서가 바뀌면 바뀌는 대로 출력해준다.
SELECT capital, name
FROM tblcountry;

SELECT name, length(name)
FROM tblcountry;

--SELECT name, length(name)
--FROM tblcountr; -- ORA-00942번 에러 발생. 테이블이나 뷰가 존재하지 않습니다.

--SELECT nam, length(name) -- ORA-00904번 에러 발생. 잘못된 식별자입니다.
--FROM tblcountry;