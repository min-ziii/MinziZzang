/*
    ex31_index.sql
    
    인덱스
    - 색인
    - 검색을 빠른 속도로 하기 위해 사용되는 도구
    
    데이터베이스
    - 실제 DB는 저장장치에 있다. -> 레코드 순서가 사용자가 INSERT한 순서가 아닐 수도 있다. -> DBMS(Oracle)가 자체적으로 순서에 맞게 저장한다.
    - 어떤 데이터를 검색(SELECT) -> 처음부터 끝까지 차례대로 검색 (Table Full Scan)
    - 특정 컬럼을 하나 선택 -> 별도의 테이블을 만들어서 복사하고 미리 정렬 -> 인덱스
    
    인덱스 장단점
    - 처리 속도 향상(SELECT 속도)
    - DB 성능을 저하시킬 수 있다. (주의)
    
    자동으로 인덱스가 걸리는 컬럼
    1. PRIMARY KEY
    2. UNIQUE
    
    
    *** 검색 속도: WHERE seq = 1 >>>> WHERE buseo = '영업부'

  
  
*/

SELECT Count(*) FROm tblAddressBook;

CREATE TABLE tblIndex
AS
SELECT * FROM tblAddressBook;

INSERT INTO tblIndex SELECT * FROM tblIndex;

SELECT Count(*) FROm tblIndex; -- 8192000

SET TIMING ON;

-- 인덱스 없이 검색 -> 경과 시간: 00:00:01.242
SELECT Count(*) FROM tblIndex WHERE name = '최민기'; -- F5로 실행해보자

-- 인덱스 생성
CREATE INDEX idxName ON tblIndex(name);

-- 경과 시간: 00:00:00.012
SELECT Count(*) FROM tblIndex WHERE name = '최민기';

-- 인덱스 삭제
DROP INDEX idxName;

-- 경과 시간: 00:00:01.293
SELECT Count(*) FROM tblIndex WHERE name = '최민기';


/*
    인덱스 종류
    1. 고유 인덱스
         - PK, UQ -> 자동으로 생성되는 인덱스
         - 색인의 값이 중복이 불가능
         
    2. 비고유 인덱스
        - 일반 컬럼 -> 사용자가 생성하는 인덱스
        - 색인의 값이 중복 가능
        
    3. 단일 인덱스
        - 컬럼 1개를 대상으로 만드는 인덱스
    
    4. 복합 인덱스
        - 컬럼 N개를 대상으로 만드는 인덱스
        
    5. 함수 기반 인덱스
*/

-- 경과 시간: 00:00:01.289
SELECT Count(*) FROM tblIndex WHERE hometown = '서울';

CREATE INDEX idxHometown on tblIndex(hometown);

-- 경과 시간: 00:00:00.049
SELECT Count(*) FROM tblIndex WHERE hometown = '서울';


SELECT Count(*) FROM tblIndex WHERE hometown = '서울' and job = '학생';

CREATE INDEX idxHometownJob ON tblIndex(hometown, job);

SELECT Count(*) FROM tblIndex WHERE hometown = '서울' and job = '학생';

SELECT Count(*) FROM tblIndex WHERE job = '학생' and hometown = '서울' ;

-- 함수 기반 인덱스
SELECT * FROM tblIndex WHERE rownum <= 10;

SELECT Count(*) FROM tblIndex WHERE substr(email, instr('email', '@')) = '@naver.com';

CREATE INDEX idxEmail ON tblIndex(email);
DROP INDEX Email;
CREATE INDEX idxEmail ON tblIndex(substr(email, instr('email', '@')));


/*
    인덱스를 사용해야 하는 상황
    1. 테이블에 데이터가 많을 때
    2. WHERE절에 사용되는 횟수가 많은 컬럼에 적용 (***)
    3. 인덱스 손익분기점 -> 검색 결과가 원본테이블의 10% ~ 15% 이하인 경우
    4. NULL을 포함하는 경우 -> 인덱스 NULL을 제외
    
    인덱스를 사용하지 말아야 하는 상황
    1. 테이블에 데이터가 적을 때 -> FULL SCAN하고 별 차이가 없음
    2. WHERE절에 사용되는 횟수가 적은 컬럼에 적용 -> 효과 없음
    3. 인덱스 손익분기점 -> 검색 결과가 원본 테이블의 15% 이상인 경우
    4. 삽입, 수정, 삭제가 아주 빈번한 테이블 (***)
*/

