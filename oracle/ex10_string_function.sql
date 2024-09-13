/*
    ex10_string_function.sql
    
    문자열 함수
    
    대소문자 변환
    - upper(), lower(), initcap()
    - varchar2 upper(컬럼명)
    - varchar2 lower(컬럼명)
    - varchar2 initcap(컬럼명)
    
*/
SELECT
    'javaTest',
    upper('javaTest'),  -- JAVATEST
    lower('javaTest'),  -- javatest
    initcap('javaTest') -- Javatest
FROM dual;

-- employees의 이름에 'de'가 포함된 사람을 검색하시오.
SELECT first_name
FROM employees
WHERE first_name like '%de%'
    or first_name like '%DE%'
    or first_name like '%De%'
    or first_name like '%dE%';

SELECT first_name
FROM employees
WHERE upper(first_name) like '%DE%'; -- 전부 upper로 바꿨으니 DE로만 찾으면 되겠지.

/*
    문자열 추출 함수
    - substr()
    - varchar2 substr(컬럼명, 시작위치, 가져올 문자 개수)
    - varchar2 substr(컬럼명, 시작위치)
*/

SELECT
    name,
    substr(name, 1, 3),
    substr(name, 1)
FROM tblCountry;

SELECT
    name, ssn,
    substr(ssn, 1, 2) as 생년,
    substr(ssn, 3, 2) as 생월,
    substr(ssn, 5, 2) as 생일,
    substr(ssn, 8, 1) as 성별
FROM tblInsa;

-- tblInsa에서 김이박최정 씨는 각각 몇 명씩인가?
SELECT count(*) FROM tblInsa WHERE name like '김%'; -- 12
SELECT count(*) FROM tblInsa WHERE substr(name, 1, 1) = '김'; -- 12

SELECT count(*) FROM tblInsa WHERE substr(name, 1, 1) = '이'; -- 14
SELECT count(*) FROM tblInsa WHERE substr(name, 1, 1) = '박'; -- 2
SELECT count(*) FROM tblInsa WHERE substr(name, 1, 1) = '최'; -- 1
SELECT count(*) FROM tblInsa WHERE substr(name, 1, 1) = '정'; -- 5

SELECT
    count(case
        when substr(name, 1, 1) = '김' then 1
    end) as 김,
    count(case
        when substr(name, 1, 1) = '이' then 1
    end) as 이,
    count(case
        when substr(name, 1, 1) = '박' then 1
    end) as 박,
    count(case
        when substr(name, 1, 1) = '최' then 1
    end) as 최,
    count(case
        when substr(name, 1, 1) = '정' then 1
    end) as 정,
    count(case
        when substr(name, 1, 1) not in ('김', '이', '박', '최', '정') then 1
    end) as 나머지
FROM tblInsa;

/*
    문자열 길이
    - length()
    - number length(컬럼명)
*/

-- 대부분의 함수에서 적용 가능한 위치
-- 컬럼 리스트
SELECT name, length(name) FROM tblCountry;

-- 조건절에서 사용
SELECT name, length(name) as ln FROM tblCountry
WHERE length(name) > 3;

SELECT name, length(name) FROM tblCountry
ORDER BY length(name) DESC;

SELECT name, length(name) as ln FROM tblCountry
ORDER BY ln DESC;

/*
    문자열 검색 
    - instr()
    - 검색어의 위치 반환
    - number instr(컬럼명, 검색어)
    - number inster(컬럼명, 검색어, 시작위치, -1)  -- == lastIndexOf()
    - 못 찾으면 0을 반환
    
*/

SELECT
    '안녕하세요. 홍길동님',
    instr('안녕하세요. 홍길동님', '홍길동'), -- 8
    instr('안녕하세요. 홍길동님', '아무개'), -- 0
    instr('안녕하세요. 홍길동님, 홍길동님', '홍길동'), -- 1번째 홍길동
    instr('안녕하세요. 홍길동님, 홍길동님', '홍길동', instr('안녕하세요. 홍길동님, 홍길동님', '홍길동') + length('홍길동')), -- 2번째 홍길동
    instr('안녕하세요. 홍길동님, 홍길동님', '홍길동', -1) -- 끝에서 1번째 홍길동
FROM dual;

/*
    패딩 (padding)
    - lpad(), rpad()
    - varchar2 lpad(컬럼명, 개수, 문자)
    - varchar2 rpad(컬럼명, 개수, 문자)
    
*/

SELECT
    lpad('A', 5),               --     A
    lpad('A', 5, 'B'),          -- BBBBA
    lpad('AA', 5, 'B'),         -- BBBAA
    lpad('AAA', 5, 'B'),        -- BBAAA
    lpad('AAAA', 5, 'B'),       -- BAAAA
    lpad('AAAAA', 5, 'B'),      -- AAAAA
    lpad('AAAAAA', 5, 'B'),     -- AAAAA (자릿수를 5로 고정했기 때문)
    lpad('1', 3, '0'),          -- 001
    rpad('1', 3, '0')           -- 100
FROM dual;


/*
    공백 제거
    - trim(), ltrim(), rtrim()
    - varchar2 trim(컬럼명)
    - varchar2 ltrim(컬럼명)
    - varchar2 rtrim(컬럼명)
    
*/

SELECT
    '     하나     둘     셋     ',
    trim('     하나     둘     셋     '),
    ltrim('     하나     둘     셋     '),
    rtrim('     하나     둘     셋     ')
FROM dual;

/*
    문자열 치환
    - replace()
    - varchar2 replace(컬럼명, 찾을 문자열, 바꿀 문자열)
    - varchar2 regexp_replace(컬럼명, 찾을 문자열, 바꿀 문자열)
    - 치환할 대상을 못 찾으면 원본을 반환한다. (*)
*/

SELECT
    replace('홍길동', '홍', '김'),
    replace('홍길동', '이', '김'),
    replace('홍길홍', '홍', '김')
FROM dual;

-- 정규식 표현
SELECT
    name,
    regexp_replace(name, '김[가-힣]{2}', '김OO'),
    tel,
    regexp_replace(tel, '(\d{3})-(\d{4})-\d{4}' , '\1-\2-****'), -- 국번과 첫4번을 ()를 사용해 각각 그룹으로 묶음
    regexp_replace(tel, '(\d{3}-\d{4})-\d{4}' , '\1-****') -- 국번과 첫4번을 ()를 사용해 한 그룹으로 묶음
FROM tblInsa;

/*
    SQL에 split()은 없음. 배열이 없기 때문.
*/

/*
    문자열 치환
    - decode()
    - replace()와 유사하지만 쓰임새가 다름.
    - varchar2 decode(컬럼명, 찾을 문자열, 바꿀 문자열)
    - varchar2 decode(컬럼명, 찾을 문자열, 바꿀 문자열[, 찾을 문자열, 바꿀 문자열] x N)
    - 찾을 문자열과 바꿀 문자열을 계속해서 넣을 수 있다.
    - replace()와 다르게 치환할 대상을 못 찾으면 case-end처럼 NULL을 반환한다. (*)
*/

-- tblComedian의 성별 탭에 있는 값들을 m, f 말고 남자, 여자로 바꿔라.
SELECT
    case
        when gender = 'm' then '남자'
        when gender = 'f' then '여자'
    end as g1,
    replace(replace(gender, 'm', '남자'), 'f', '여자') as g2, -- 바꿀 게 없으면 원본 반환
    decode(gender, 'm', '남자', 'f', '여자') as g3 -- 바꿀 게 없으면 NULL 반환
FROM tblComedian;

-- tblComedian의 남녀 수를 한 테이블에 보여라.
SELECT
    count(case
        when gender = 'm' then 1
    end),
    count(case
        when gender = 'f' then 1
    end),
    count(decode(gender, 'm', 1)),
    count(decode(gender, 'f', 1))
FROM tblComedian;