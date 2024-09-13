-- case

-- tblInsa

-- 1. 직원의 급여가 3000000 이상이면 '고급', 2000000 이상이면 '중급', 그 외는 '초급'으로 분류하세요.
SELECT
    case
        when basicpay >= 3000000 then '고급'
        when basicpay >= 2000000 then '중급'
        else '초급'
    end
FROM tblInsa;


-- 2. 직원의 직위가 '부장'이나 '과장'이면 '관리자', 그 외는 '직원'으로 분류하세요.
SELECT
    case
        when jikwi in ('부장', '과장') then '관리자'
        else '직원'
    end
FROM tblInsa;


-- 3. 직원의 주민번호 뒷자리 첫번째 숫자가 1이나 3이면 '남자', 2나 4면 '여자'로 분류하세요.
SELECT
    case
        when ssn like '%-1%' or ssn like '%-3%' then '남자'
        else '여자'
    end
FROM tblInsa;


-- 4. 부서가 '영업부'면 5%, '총무부'면 3%, '개발부'면 7%, 나머지는 2%의 보너스율을 적용하세요.
SELECT
    case
        when buseo = '영업부' then '5%'
        when buseo = '총무부' then '3%'
        when buseo = '개발부' then '7%'
        else '2%'
    end as 보너스율
FROM tblInsa;

-- 5. 직원의 급여와 수당의 합이 5000000 이상이면 '관리대상', 그 외는 '일반'으로 분류하세요.
SELECT
    case
        when basicpay + sudang >= 5000000 then '관리대상'
        else '일반'
    end
FROM tblInsa;


-- tblCountry

-- 6. 인구가 5000만 이상이면 '대국가', 1000만 이상이면 '중국가', 그 외는 '소국가'로 분류하세요.
SELECT
    case
        when population >= 5000 then '대국가'
        when population >= 1000 then '중국가'
        else '소국가'
    end
FROM tblCountry;

-- 7. 대륙별로 'AS'는 '아시아', 'EU'는 '유럽', 'AF'는 '아프리카', 그 외는 '기타'로 출력하세요.
SELECT
    case
        when continent = 'AS' then '아시아'
       when continent = 'EU' then '유럽'
       when continent = 'AF' then '아프리카'
        else '기타'
    end
FROM tblCountry;

-- 8. 국가 이름의 길이가 4자 이상이면 '긴 이름', 그 외는 '짧은 이름'으로 분류하세요. 길이 함수: length(컬럼명)
SELECT
    case
        when length(name) >= 4 then '긴 이름'
        else '짧은 이름'
    end
FROM tblCountry;

-- 9. 면적이 10만 이상이면 '대면적', 1만 이상이면 '중면적', 그 외는 '소면적'으로 분류하세요.
SELECT
    case
        when area >= 100 then '대면적'
        when area >= 10 then '중면적'
        else '소면적'
    end
FROM tblCountry;

-- 10. 수도 이름이 '서울'이면 '한국', '베이징'이면 '중국', '도쿄'면 '일본', 그 외는 '기타'로 분류하세요.
SELECT
    case
        when name = '서울' then '한국'
       when name = '베이징' then '중국'
       when name = '도쿄' then '일본'
        else '기타'
    end
FROM tblCountry;



-- tblComedian

-- 11. 몸무게가 70kg 이상이면 '과체중', 50kg 이상이면 '정상체중', 그 외는 '저체중'으로 분류하세요.
SELECT
    case
        when weight >= 70 then '과체중'
        when weight >= 50 then '정상체중'
        else '저체중'
    end
FROM tblComedian;

-- 12. 성별이 'm'이면 '남자', 'f'면 '여자'로 표시하세요.
SELECT
    case
        when gender = 'm' then '남자'
        when gender = 'f' then '여자'
    end
FROM tblComedian;

-- 13. 키가 180cm 이상이면 '장신', 170cm 이상이면 '중간', 그 외는 '단신'으로 분류하세요.
SELECT
    case
        when height >= 180 then '장신'
        when height >= 170 then '중간'
        else '단신'
    end
FROM tblComedian;

-- 14. 이름의 길이가 3자 이상이면 '긴 이름', 그 외는 '짧은 이름'으로 분류하세요.
SELECT
    case
        when length(first+last) >= 3 then '긴 이름'
        else '짧은 이름'
    end
FROM tblComedian;

-- 15. 코미디언의 나이(2024년 기준)를 계산하여 30세 이상이면 '중견', 그 외는 '신인'으로 분류하세요.
-- 나이, 출생연도 데이터가 없다.

-- tblDiary

-- 16. 날씨가 '맑음'이면 '야외활동', '흐림'이면 '실내활동', 그 외는 '휴식'으로 분류하세요.
SELECT
    case
        when weather = '맑음' then '야외활동'
        when weather = '흐림' then '실내활동'
        else '휴식'
    end
FROM tblDiary;

-- 17. 일기의 제목이 '오늘'로 시작하면 '일상', '영화'로 시작하면 '문화생활', 그 외는 '기타'로 분류하세요.
SELECT
    case
        when subject like '오늘%' then '일상'
        when subject like '영화%' then '문화생활'
        else '기타'
    end
FROM tblDiary;
