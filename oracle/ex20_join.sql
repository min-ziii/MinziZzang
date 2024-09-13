/*
    ex20_join.sql
    
    관계형 데이터베이스 시스템이 지양하는 것들
    
    1. 테이블에 기본 키가 없음 -> 데이터 조작 불가능(레코드 식별 불가능)
    2. NULL이 많은 상태의 테이블 -> 공간 낭비 + SQL작업이 불편해짐
    3. 데이터가 중복되는 상태 -> 공간 낭비 + 데이터 조작 문제 발생(일관성 저하)
    4. 하나의 속성값이 원자값이 아닌 경우 -> 더이상 쪼개지지 않는 값을 넣는다. 
    
    
*/

-- 직원 정보
-- 직원(번호(PK), 직원명, 급여, 거주지, 담당프로젝트
SELECT * FROM tblStaff;

DROP TABLE tblStaff;
DROP TABLE tblProject;

CREATE TABLE tblStaff (
    seq number primary key,             -- 직원번호(PK)
    name varchar2(30) NOT NULL,         -- 직원명
    salary number NOT NULL,             -- 급여
    address varchar2(300) NOT NULL,     -- 거주지
    project varchar2(300)               -- 담당프로젝트
);

INSERT INTO tblStaff (seq, name, salary, address)
    VALUES (1, '홍길동', 300, '서울시');
    
INSERT INTO tblStaff (seq, name, salary, address)
    VALUES (2, '아무개', 250, '인천시');
    
INSERT INTO tblStaff (seq, name, salary, address)
    VALUES (3, '하하하', 350, '의정부시');
    
INSERT INTO tblProject (seq, project, staff_seq) VALUES (1, '홍콩 수출', 1);
INSERT INTO tblProject (seq, project, staff_seq) VALUES (2, 'TV 광고', 2);
INSERT INTO tblProject (seq, project, staff_seq) VALUES (3, '매출 분석', 3);
INSERT INTO tblProject (seq, project, staff_seq) VALUES (4, '노조 협상', 1);
INSERT INTO tblProject (seq, project, staff_seq) VALUES (5, '대리점 분양', 2);

    
-- '홍길동'에게 담당프로젝트 1건 추가: '고객 관리'

UPDATE tblStaff set project = project || ',고객 관리' WHERE seq = 1; -- 이런거 하지마.    

INSERT INTO tblStaff (seq, name, salary, address , project)
    VALUES (4, '홍길동', 300, '서울시', '고객 관리');
    
-- 원인: 테이블 스키마(구조)가 잘못된 상태
-- 해결: 테이블 재구성


DELETE FROM tblProject;
DELETE FROM tblStaff;


-- 직원 정보
-- 직원(번호(PK), 직원명, 급여, 거주지, 담당프로젝트
CREATE TABLE tblStaff (
    seq number primary key,             -- 직원번호(PK)
    name varchar2(30) NOT NULL,         -- 직원명
    salary number NOT NULL,             -- 급여
    address varchar2(300) NOT NULL      -- 거주지
);

-- 프로젝트 정보, 담당프로젝트
CREATE TABLE tblProject (
    seq number Primary Key,             -- 프로젝트번호
    project varchar2(300),              -- 담당프로젝트
    staff_seq number references tblStaff(seq) NOT NULL -- 담당직원번호(FK)
    
);

SELECT * FROM tblStaff;
SELECT * FROM tblProject;

-- A. 신입 사원 입사 -> 신규 프로젝트 배정
-- A.1 신입사원 추가 (성공)
INSERT INTO tblStaff (seq, name, salary, address)
    VALUES (4, '호호호', 250, '성남시');
    
-- A.2 신규 프로젝트 발주 + 담당자 배정
INSERT INTO tblProject (seq, project, staff_seq) VALUES (6, '자재 매입', 4);

-- A.2 신규 프로젝트 발주 + 담당자 배정
-- ORA-02291: integrity constraint (HR.SYS_C007135) violated - parent key not found
INSERT INTO tblProject (seq, project, staff_seq) VALUES (7, '고객 유치', 5); 

-- B. '홍길동' 퇴사
-- B.1 '홍길동' 삭제
-- ORA-02292: integrity constraint (HR.SYS_C007135) violated - child record found
DELETE FROM tblStaff WHERE seq = 1;

-- B.2 '홍길동' 삭제 -> 업무 인수인계(위임)
UPDATE tblProject SET staff_seq = 2 WHERE staff_seq = 1;

-- B.3 '홍길동' 삭제
DELETE FROM tblStaff WHERE seq = 1;



/*
    조인 Join
    - (서로 관계를 맺고 있는) 2개(1개) 이상의 테이블을 1개의 결과셋으로 만드는 행위
    
    조인의 종류
    1. 단순 조인 (Cross Join)
    2. 내부 조인 (Inner Join)
    3. 외부 조인 (Outer Join)
    4. 셀프 조인 (Self Join)
    5. 전체 외부 조인 (Full Outer Join)
*/

/*    
    1. 단순 조인, Cross Join, Cartesian 곱
    - 모든 조인의 기본 동작
    - A 테이블 X B 테이블
    - 쓸모 없음. -> 맞는 데이터 행과 틀린 데이터 행이 뒤섞여있다.
    - 그럼 왜 씀? -> 유효성이 필요 없는 대량의 더미 데이터 생산용
    
    SELECT 컬럼리스트
    FROM 테이블A CROSS JOIN 테이블B;
*/

SELECT * FROM tblCustomer; -- 3명
SELECT * FROM tblSales;    -- 9건

SELECT * FROM tblCustomer CROSS JOIN tblSales; -- 3x9=27

/*
    2. 내부 조인 (*)
    - 단순 조인에서 유효한 레코드만 추출한 조인
    
    SELECT 컬럼리스트
    FROM 부모테이블 INNER JOIN 자식테이블
    ON 부모테이블.컬럼(PK) = 자식테이블.컬럼(FK); -- 조인의 WHERE절
*/

-- 고객변호, 고객명(tblCustomer),, 상품번호, 상품명(tblSales)
SELECT c.seq as cseq, c.name, s.seq as sseq, s.item -- 2. 헷갈리지 않게 상황에 맞는 alias를 전부 붙여주는 것이 좋음
FROM tblCustomer c INNER JOIN tblSales s            -- 1. 테이블용 alias는 최대한 간결히. alias가 붙었으면 이후 표기에서도 전부 alias로 지킬 것.
ON c.seq = s.cseq;

SELECT * FROM tblStaff INNER JOIN tblProject
ON tblstaff.seq = tblproject.staff_seq;

-- 고객테이블 + 판매테이블
-- 어떤 고객(customer.name)이 어떤 물건(sales.item)을 몇 개(sales.qty) 사갔는지?
-- 1. 조인
SELECT c.name, s.item, s.qty
FROM tblCustomer c
INNER JOIN tblSales s ON c.seq = s.cseq;
-- 2. 서브쿼리
-- 메인쿼리를 자식테이블. 서브쿼리를 부모테이블.
SELECT
    (SELECT name FROM tblCustomer WHERE seq = tblsales.cseq) as 고객명,
    item as 상품명,
    qty as 수량
FROM tblSales;


-- 장르 + 비디오
SELECT *
FROM tblGenre g
INNER JOIN tblVideo v ON g.seq = v.genre;

-- 장르 + 비디오 + 대여
SELECT *
FROM tblGenre g
INNER JOIN tblVideo v ON g.seq = v.genre
INNER JOIN tblRent r ON v.seq = r.video;

-- 장르 + 비디오 + 대여 + 회원
SELECT
    m.name as 누가,
    v.name as 무엇을,
    r.rentdate as 언제,
    g.price as 얼마에
FROM tblGenre g
INNER JOIN tblVideo v ON g.seq = v.genre
INNER JOIN tblRent r ON v.seq = r.video
INNER JOIN tblMember m ON m.seq = r.seq;

--hr의 테이블들을 붙여보자
SELECT *
FROM employees e
INNER JOIN departments d ON d.department_id = e.department_id
INNER JOIN locations l ON l.location_id = d.location_id
INNER JOIN countries c ON c.country_id = l.country_id
INNER JOIN regions r ON r.region_id = c.region_id
INNER JOIN jobs j ON j.job_id = e.job_id;



/*
    3. 외부 조인
    - 내부 조인의 반댓말이 아님
    - 내부 조인 결과셋 + a(내부 조인에 포함되지 않은 부모테이블의 나머지 레코드)
    
    SELECT
        컬럼리스트
    FROM 부모테이블
    (LEFT|RIGHT) OUTER JOIN 자식테이블 ON 부모테이블.PK = 자식테이블.FK;
    부모테이블쪽을 가리키는 게 보통이다.
*/

SELECT * FROM tblCustomer; -- 3 -> 5
SELECT * FROM tblSales; -- 9

INSERT INTO tblCustomer values (4, '호호호', '010-9999-9999', '서울시');
INSERT INTO tblCustomer values (5, '후후후', '010-5555-5555', '강릉시');

-- 내부 조인 -> 9개
-- 물건을 한 번이라도 구매한 이력이 있는 고객 정보 + 구매내역을 가져오시오.
-- 구매 이력이 없는 호호호와 후후후는 제외됨
-- 내부 조인은 양쪽 테이블 모두에 존재하는 행만 가져오기 때문
SELECT *
FROM tblCustomer c
INNER JOIN tblSales s ON c.seq = s.cseq;

-- 외부 조인 -> 11개 -> 내부 조인 9개 + 2명
-- 물건을 한 번이라도 구매한 이력이 있는 고객 정보 + 구매내역을 가져오시오.
-- 구매 내력이 없는 고객은 고객 정보만 가져오시오.
SELECT *
FROM tblCustomer c RIGHT OUTER JOIN tblSales s
ON c.seq = s.cseq;



-- 내부 조인 -> 프로젝트를 최소 1건 이상 담당하고 있는 직원 명단 + 담당 프로젝트를 가져오시오.
SELECT *
FROM tblStaff s
INNER JOIN tblProject p ON s.seq = p.staff_seq;

-- 외부 조인 -> 담당 프로젝트 유무와 상관 없이 모든 직원 명단 + 프로젝트 담당하고 있으면 담당프로젝트를 가져오시오.
SELECT *
FROM tblStaff s
LEFT OUTER JOIN tblProject p ON s.seq = p.staff_seq;


-- 내부 조인 -> 비디오(부모) + 대여(자식) -> 대여기록이 한 번이라도 있는 비디오 + 그 비디오들의 대여 기록
SELECT *
FROM tblVideo v
INNER JOIN tblrent r ON v.seq = r.video;

-- 외부 조인 -> 비디오(부모) + 대여(자식) -> 대여기록과 상관없이 모든 비디오 + 대여 기록이 있다면 비디오의 대여기록도
SELECT *
FROM tblVideo v
LEFT OUTER JOIN tblrent r ON v.seq = r.video;

-- 내부 조인 -> 회원 + 대여 -> 대여 기록이 한 번이라도 있는 회원의 정보 + 대여 내역
SELECT *
FROM tblMember m
INNER JOIN tblrent r ON m.seq = r.member;


-- 외부 조인 -> 회원 + 대여 -> 대여 기록과 상관 없이 회원들 정보 + 대여 내역
SELECT *
FROM tblMember m
LEFT OUTER JOIN tblrent r ON m.seq = r.member;


-- 안 빌린 사람들
SELECT *
FROM tblMember m
LEFT OUTER JOIN tblrent r ON m.seq = r.member
WHERE r.seq IS NULL;


-- 대여 기록이 있는(INNER JOIN) 회원 이름, 연락처 + 대여 횟수
--1.
SELECT m.name, count(*), (SELECT tel FROM tblMember WHERE name = m.name) as tel -- 서브쿼리(특히나 지금처럼 Scalar 서브쿼리)는 상수니까 그냥 쓸 수 있다.
FROM tblMember m
INNER JOIN tblrent r ON m.seq = r.member
GROUP BY m.name
ORDER BY count(*) DESC;

--2.
SELECT
    m.name,
    m.tel,
    count(*)
FROM tblmember m
INNER JOIN tblRent r ON m.seq = r.member
GROUP BY m.name, m.tel
ORDER BY count(*) DESC;



/*  
    INNER JOIN -> 테이블 2개
    OUTER JOIN -> 테이블 2개
    INNER JOIN + SELF JOIN -> 테이블 1개
    OUTER JOIN + SELF JOIN -> 테이블 1개
    
    4. SELF JOIN
    - 1개의 테이블을 사용하는 조인
    - 테이블이 자기 스스로와 관계를 맺는 경우에 사용
    
*/

-- 직원 테이블
DROP TABLE tblSelf;

CREATE TABLE tblSelf (
    seq number PRIMARY KEY,                     -- 직원 번호 (PK)
    name varchar2(30) NOT NULL,                 -- 직원명
    department varchar2(30) NOT NULL,           -- 부서명
    super number NULL REFERENCES tblSelf(seq)   -- 상사 번호(FK)
);

INSERT INTO tblSelf VALUES (1, '홍사장', '사장실', NULL);
INSERT INTO tblSelf VALUES (2, '김부장', '영업부', 1);
INSERT INTO tblSelf VALUES (3, '박과장', '영업부', 2);
INSERT INTO tblSelf VALUES (4, '최대리', '영업부', 3);
INSERT INTO tblSelf VALUES (5, '정사원', '영업부', 4);
INSERT INTO tblSelf VALUES (6, '이부장', '개발부', 1);
INSERT INTO tblSelf VALUES (7, '하과장', '개발부', 6);
INSERT INTO tblSelf VALUES (8, '신과장', '개발부', 6);
INSERT INTO tblSelf VALUES (9, '황대리', '개발부', 7);
INSERT INTO tblSelf VALUES (10, '허사원', '개발부', 9);

-- 직원 명단을 가져오시오. 단 상사의 이름도
-- 1. JOIN
-- 2. SUB QUERY
-- 3. 계층형 QUERY (Oracle 전용)

-- 1.
SELECT
    s2.name as 직원명,
    s1.name as 상사명,
    s2.department as 부서명
FROM tblSelf s1                 -- 역할: 상사(부모테이블)
RIGHT OUTER JOIN tblSelf s2     -- 역할: 직원(자식테이블) (RIGHT OUTER JOIN이 사용되는 몇 안 되는 경우.)
ON s1.seq = s2.super;           -- s2를 가져오고, s2의 super(상사번호)에 해당하는 s1도 가져온다.

-- 2.
SELECT
    name as 직원명,
    department as 부서명,
    (SELECT name FROM tblSelf s1 WHERE s1.seq = s2.super) as 상사명 -- 서브 쿼리. 부모테이블에서 상사의 정보를 가져온다.
FROM tblSelf s2; -- 메인 쿼리. 자식 테이블에서 직원 정보를 가져온다.



/*
    5. 전체 외부 조인, FULL OUTER JOIN
    - 서로 참조하고 있는 관계에서 사용
    
*/

SELECT * FROM tblMen;
SELECT * FROM tblWomen;

-- 커플인 남/녀를 가져오시오.
SELECT 
    m.name as 남자이름,
    w.name as 여자이름
FROM tblMen m           -- 부모
INNER JOIN tblWomen w   -- 자식
ON m.name = w.couple;   -- 남자테이블의 이름이 여자테이블의 커플에 적혀있는 경우 (커플인 경우)

-- 커플과 남자 솔로를 가져오시오.
SELECT 
    m.name as 남자이름,
    w.name as 여자이름
FROM tblMen m                -- 부모
LEFT OUTER JOIN tblWomen w   -- 자식
ON m.name = w.couple;

-- 커플과 여자 솔로를 가져오시오.
SELECT 
    m.name as 남자이름,
    w.name as 여자이름
FROM tblMen m                 -- 부모
RIGHT OUTER JOIN tblWomen w   -- 자식
ON m.name = w.couple;

-- 커플과 남자 솔로, 여자 솔로를 가져오시오.
SELECT 
    m.name as 남자이름,
    w.name as 여자이름
FROM tblMen m                -- 부모
FULL OUTER JOIN tblWomen w   -- 자식 -- (RIGHT + LEFT = FULL)
ON m.name = w.couple;