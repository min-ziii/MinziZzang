/*
    ex06_column.sql
    
    컬럼 리스트에서 할 수 있는 행동
    - SELECT 컬럼리스트
    
    
*/

-- 컬럼명
SELECT name from tblCountry;

-- 연산 -> 컬럼의 결과셋이 유효한지 확인 후 별칭 사용.
SELECT area + 100, name || '나라' from tblCountry; -- String || String(SQL) == String + String(Java)

-- 상수
SELECT name, population, 100 FROM tblCountry;

-- 함수(Method) == 데이터
SELECT length(name) FROM tblCountry;


/*
    distinct
    - Java Stream: list.stream().distinct().forEach() -> 중복값을 제거하는 중간 파이프
    - 컬럼 리스트에서 사용
    - 중복값 제거용
    - 컬럼의 중복값이 아니라 레코드 전체의 중복값을 제거
*/

-- tblCountry에 어떤 종류의 대륙들이 있는지?
SELECT DISTINCT continent FROM tblCountry;

-- tblInsa. 이 회사는 어떤 부서가 있는지?
SELECT DISTINCT buseo FROM tblInsa;

-- 어떤 부서이고 그 부서에 속한 직원명은?
SELECT buseo, DISTINCT name FROM tblInsa;



/*
    case
    - 대부분의 절에서 사용
    - switch cas 문의 역할
    - 조건(when)을 만족하면 then을 반환
    - 조건을 불만족하면 NULL을 반환
    case
        when ~ then ~
        [else]
    end
*/

-- 요구사항) 성과 이름을 붙여서 name으로 만들고, 성의 mf값을 남자와 여자로 바꿔주세요.
SELECT last || first as name,
    gender,
    case 
        -- when 조건 then 값
        when gender = 'm' then '남자'
        when gender = 'f' then '여자'
    end as kgender
FROM tblComedian;


-- 요구사항) continent의 값들이 뭔 소린지 모르겠으니 우리가 알게끔 바꿔서 출력해주세요.
SELECT name, continent,
    case
        when continent = 'AS' then '아시아'
        when continent = 'EU' then '유럽'
        when continent = 'AF' then '아프리카'
        --else '기타'
        else continent -- '기타'같은 상수를 꼭 돌려줄 필요는 없고, 같은 자료형을 돌려주는 것만 지키면 된다.
    end as continentName
FROM tblCountry;


-- 요구사항) 몸무게 데이터를 50kg, 90kg 기준으로 저체중/보통체중/과체중으로 분류해주세요.
SELECT last || first as name,
    weight,
    case 
       when weight > 90 then '과체중'
       when weight > 50 then '보통체중'
       else '저체중'
    end as state,
    case
        when weight >= 50 and weight <= 90 then '보통체중'
        else '주의체중'
    end as state2,
    case
        when weight between 50 and 90 then '보통체중' -- state2를 between으로 구현
        else '주의체중'
    end as state3
FROM tblComedian;


-- 요구사항) 사원/대리 -> 현장직, 과장/부장 -> 관리직 으로 표현해주세요.
SELECT name, jikwi,
    case
        when jikwi = '과장' or jikwi = '부장' then '관리직'
        else '현장직'
    end as jikwiName,
    case
        when jikwi in ('과장', '부장') then '관리직'
        else '현장직'
    end as jikwiName2,
    case
        when jikwi like '_장' then '관리직'
        else '현장직'
    end as jikwiName3
FROM tblInsa;


SELECT title,
    case
        when completedate is not null then '완료'
        when completedate is null then '미완료'
    end
FROM tblTodo;