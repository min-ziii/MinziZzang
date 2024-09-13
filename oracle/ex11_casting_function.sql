/*
    ex11_casting_function.sql
    
    형변환 함수
    
    Java의 경우: (자료형)데이터
    
    SQL의 경우: 함수 제공
    1. varchar2 to_char(숫자형): 숫자 -> 문자열
    2. varchar2 to_char(날짜형): 날짜 -> 문자열
    3. number to_number(문자형): 문자열 -> 숫자
    4. date to_date(문자형)    : 문자열 -> 날짜
    
*/
    
/*

    1. varchar2 to_char(숫자형[, 형식문자열]): 숫자 -> 문자열
    
    형식문자열의 구성요소
    a. 9: 숫자 1개를 문자 1개로 바꾸는 역할, 빈 자리는 공백 -> %5d
    b. 0: 숫자 1개를 문자 1개로 바꾸는 역할, 빈 자리는 0    -> %05d
    c. $: 통화 기호
    d. L: 통화 기호(지역)
    e. .: 소수점
    f. ,: 천단위 표기
    ...Java보다 별론데? -> SQL에선 이게 최선임
    
*/

SELECT
    100,
    to_char(100),
    '@' || to_char(100, '99999') || '@',
    '@' || to_char(100, '00000') || '@',
    to_char(100, '$999'),
    '$' || ltrim(to_char(100, '999')),
    to_char(100, 'L999')
FROM dual;

SELECT
    3.14,
    to_char(3.14, '9.99'),
    to_char(3.15, '9.9'),
    1000000,
    to_char(1000000, '9,999,999')
FROM dual;
    
    
/*

    2. varchar2 to_char(날짜형): 날짜 -> 문자열
    (*매*우*중*요*)
    Calendar.get(CALENDAR.YEAR) 역할
    
    형식문자열의 구성요소
    a. yyyy
    b. yy
    c. month
    d. mon
    e. mm
    f. day
    g. dy
    h. ddd
    i. dd
    j. d
    k. hh
    l. hh24
    m. mi
    n. ss
    o. am(pm)
    
*/


SELECT sysdate FROM dual; -- sysdate 함수(현재시각) (환경설정-데이터베이스-NLS)

SELECT to_char(sysdate, 'yyyy') FROM dual;  -- 2024     년, 4자리
SELECT to_char(sysdate, 'yy') FROM dual;    -- 24       년, 2자리
SELECT to_char(sysdate, 'month') FROM dual; -- 7월      월, 풀네임
SELECT to_char(sysdate, 'mon') FROM dual;   -- 7월      월, 약어
SELECT to_char(sysdate, 'mm') FROM dual;    -- 07       월
SELECT to_char(sysdate, 'day') FROM dual;   -- 목요일   요일, 풀네임
SELECT to_char(sysdate, 'dy') FROM dual;    -- 목       요일, 약어
SELECT to_char(sysdate, 'ddd') FROM dual;   -- 207      일, 올해의 몇번째 일인지
SELECT to_char(sysdate, 'dd') FROM dual;    -- 25       일, 이번달의 몇번째 일인지
SELECT to_char(sysdate, 'd') FROM dual;     -- 5        일, 이번주의 몇번째 일인지 (요일) (1 ~ 7)
SELECT to_char(sysdate, 'hh') FROM dual;    -- 12       시간, 12H
SELECT to_char(sysdate, 'hh24') FROM dual;  -- 12       시간, 24H
SELECT to_char(sysdate, 'mi') FROM dual;    -- 35       분
SELECT to_char(sysdate, 'ss') FROM dual;    -- 38       초
SELECT to_char(sysdate, 'am') FROM dual;    -- 오후     오전오후
SELECT to_char(sysdate, 'pm') FROM dual;    -- 오후     오전오후

SELECT
    sysdate, -- SQL Developer 기본 설정에서는 24/07/25로 보임
    to_char(sysdate, 'yyyy-mm-dd'), -- 2024-07-25
    to_char(sysdate, 'hh24:mi:ss'), -- 12:38:33
    to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss'), -- 2024-07-25 12:38:51
    to_char(sysdate, 'day am hh:mi:ss') -- 목요일 오후 12:39:54
FROM dual;

-- 휴일에 입사한 사람과 핑일에 입사한 사람을 구분지어달라.
SELECT
    name,
    to_char(ibsaDate, 'yyyy-mm-dd day') as ibsadate,
    case
        when to_char(ibsaDate, 'd') in ('1', '7') then '휴일 입사'
        else '평일 입사'
    end
FROM tblInsa;

-- tblIbsa에서 요일별 입사한 인원 수를 구하라.
SELECT
    count(decode(to_char(ibsadate, 'd'), 1, '월')) as 일,
    count(decode(to_char(ibsadate, 'd'), 2, '화')) as 월,
    count(decode(to_char(ibsadate, 'd'), 3, '수')) as 화,
    count(decode(to_char(ibsadate, 'd'), 4, '목')) as 수,
    count(decode(to_char(ibsadate, 'd'), 5, '금')) as 목,
    count(decode(to_char(ibsadate, 'd'), 6, '토')) as 금,
    count(decode(to_char(ibsadate, 'd'), 7, '일')) as 토
    
    --count(decode((to_char(ibsadate, 'd'), 1)))
FROM tblInsa;

/*
    3. number to_number(문자형): 문자열 -> 숫자
    잘 안 쓴다.
*/
SELECT
    to_number('100'),
    to_number('100') * 2,
    '100' * 2 -- 암시적 형변환 가능.
FROM dual;

/*
    4. date to_date(문자형)    : 문자열 -> 날짜
    (*많*이*쓴*다*)
*/

SELECT
*
FROM tblInsa
WHERE ibsadate >= '2010-01-01'; -- date >= varchar2 암시적 형변환

SELECT
    '2010-01-01', -- 이 때 자료형은 varchar2
    to_date('2010-01-01'), -- 이 때 자료형은 date
    to_date('2010-01-01', 'yyyy-mm-dd'),
    to_date('2010/01/01', 'yyyy-mm-dd'),
    to_date('20100101', 'yyyymmdd'),
    to_date('2010-01-01 12:30:30', 'yyyy-mm-dd hh24:mi:ss') 
FROM dual;