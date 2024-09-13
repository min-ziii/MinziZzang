/*
    ex16_update.sql
    
    UPDATE문
    - DML
    - 원하는 행의 원하는 컬럼값을 수정하는 명령어
    
    UPDATE 테이블명 SET 컬럼명 = 값 [, 컬럼명 = 값] x N [WHERE절]
    
*/

-- 트랜잭션 처리
COMMIT;
ROLLBACK;

-- 대한민국 수도 이전: 서울 -> 세종
SELECT * FROM tblCountry;

UPDATE tblCountry SET capital = '세종' WHERE name = '대한민국';

UPDATE tblCountry
SET capital = '제주',
    population = 5000,
    continent = 'EU'
WHERE name = '대한민국';

-- 아시아 나라들의 인구수가 10% 증가한 상황 반영

UPDATE tblCountry
SET population = population * 1.1
WHERE continent = 'EU';