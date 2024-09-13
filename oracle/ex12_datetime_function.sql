/*
    ex12_datetime_function.sql
    
    sysdate
    - 현재 시스템의 시각을 반환
    - date sysdate
    - Java의 Calendar.getInstance() 같은.
    
*/
SELECT sysdate FROM dual;

/*
    날짜 연산
    1. 시각 - 시각 = 시간: tick - tick
    2. 시각 + 시간 = 시각: now.add(+)
    3. 시각 - 시간 = 시각: now.add(-)
*/

-- 1. 시각 - 시각 = 시간 (일 day)
-- 현재 - 입사일
SELECT
    name,
    to_char(ibsadate, 'yyyy-mm-dd') as 입사일,
    round(sysdate - ibsadate) as 근무일수, -- ex) 5766.632731481481481481481481481481481481 = 5766.63xx일이 지났다.
    round((sysdate - ibsadate) * 24) as 근무시수,
    round((sysdate - ibsadate) * 24 * 60) as 근무분수,
    round((sysdate - ibsadate) * 24 * 60 * 60) as 근무초수,
    round((sysdate - ibsadate) / 30.4 ) as 근무개월수, -- 부정확 (한 달이 30일 고정이 아니잖아)
     round((sysdate - ibsadate) / 30 / 365) as 근무년수 -- 부정확 (윤년 개념은?)
FROM tblInsa;

-- 계획 후 실행까지 얼마나 걸렸는지?
SELECT
    title,
    adddate,
    completedate,
    -- round((completedate - adddate) * 24) as 완료하기까지_걸린_시간 -- ORA-00972 오류: identifier is too long
    -- Oracle은 식별자(테이블명, 컬럼명, 별칭, ...)의 길이를 최대 30byte로 제한하고 있다.
    round((completedate - adddate) * 24) as hours
FROM tblTodo
ORDER BY hours;


/*
    2. 시각 + 시간(일 day) = 시각
    3. 시각 - 시간(일 day) = 시각
*/
SELECT
    sysdate,
    sysdate + 1,
    sysdate - 3,
    sysdate + 100 as "100dayAnniv",
    sysdate + 30 as "1달 뒤", -- 부정확
    sysdate + 365 as "1년 뒤", -- 부정확
    to_char(sysdate + (3/24), 'hh24:mi:ss') as "3시간 뒤",
    to_char(sysdate + (30 / 60 / 24), 'hh24:mi:ss') as "30분 뒤"
FROM dual;


/*
    일/시/분/초 연산: 연산자 (+,-) 사용
    월/년           : 함수 사용
    
    months_between()
    - 시각 - 시각 = 시간(월)
    - months_between(date, date)
*/

-- 현재 - 입사일
SELECT
    name,
    to_char(ibsadate, 'yyyy-mm-dd') as 입사일,
    round(sysdate - ibsadate) as 근무일수, -- ex) 5766.632731481481481481481481481481481481 = 5766.63xx일이 지났다.
    round((sysdate - ibsadate) * 24) as 근무시수,
    round((sysdate - ibsadate) * 24 * 60) as 근무분수,
    round((sysdate - ibsadate) * 24 * 60 * 60) as 근무초수,
    --round((sysdate - ibsadate) / 30.4 ) as 근무개월수, -- 부정확 (한 달이 30일 고정이 아니잖아)
    round(months_between(sysdate, ibsadate)) as 근무개월수,
    --round((sysdate - ibsadate) / 30 / 365) as 근무년수 -- 부정확 (윤년 개념은?)
    round(months_between(sysdate, ibsadate) / 12) as 근무년수
FROM tblInsa;

/*
    add_months()
    - 시각 + 시간(월) = 시각
    - 시각 - 시간(월) = 시각
    - date add_months(date, 월)
*/

SELECT
    sysdate,
    sysdate + 31,
    add_months(sysdate, 1),
    add_months(sysdate, -2),
    add_months(sysdate, 1 * 12),
    add_months(sysdate, -3 * 12)
FROM dual;