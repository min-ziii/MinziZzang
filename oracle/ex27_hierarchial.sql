/*
    ex27_hierarchial.sql
    
    계층형 쿼리, Hierarchial Query
    - Oracle 전용
    - 레코드의 관계가 서로 상하 수직 관계인 경우에 사용
    - 자기 참조를 하는 테이블에서 사용
    - Java의 트리 구조라고 생각하자.
    
    tblSelf
    홍사장
        김부장
            박과장
                최대리
                    정사원
        이부장
        
    
*/

create table tblComputer (
    seq number primary key,                         --식별자(PK)
    name varchar2(50) not null,                     --부품명
    qty number not null,                            --수량
    pseq number null references tblComputer(seq)    --부모부품(FK)
);

INSERT INTO tblComputer VALUES (1, '컴퓨터', 1, NULL);

INSERT INTO tblComputer VALUES (2, '본체', 1, 1);
INSERT INTO tblComputer VALUES (3, '메인보드', 1, 2);
INSERT INTO tblComputer VALUES (4, '그래픽카드', 1, 2);
INSERT INTO tblComputer VALUES (5, '랜카드', 1, 2);
INSERT INTO tblComputer VALUES (6, 'CPU', 1, 2);
INSERT INTO tblComputer VALUES (7, '메모리', 2, 2);

INSERT INTO tblComputer VALUES (8, '모니터', 1, 1);
INSERT INTO tblComputer VALUES (9, '보호필름', 1, 8);
INSERT INTO tblComputer VALUES (10, '모니터암', 1, 8);
INSERT INTO tblComputer VALUES (14, '모니터클리너', 1, 8);

INSERT INTO tblComputer VALUES (11, '프린터', 1, 1);
INSERT INTO tblComputer VALUES (12, 'A4용지', 100, 11);
INSERT INTO tblComputer VALUES (13, '잉크카트리지', 3, 11);

SELECT * FROM tblComputer;

-- tblSelf 직원 명단에서 상사 번호 대신 상사 이름 가져오기 -> 서브쿼리 + 조인

-- tblCouputer (자식)부품 명단과 부모 부품의 이름 가져오기
-- JOIN
SELECT
    c2.name as 부품명,
    c1.name as 부모부품명
FROM tblComputer c1       -- 부모 부품
INNER JOIN tblComputer c2 -- 자식 부품
ON c1.seq = c2.pseq;      -- 부모의 PK와 자식의 FK를 잇는다



/*
    계층형 쿼리
    1. START WITH절
    2. CONNECT BY절
    3. 의사 컬럼
        a. prior: 자신과 연관된 부모 레코드를 참조하는 가상 컬럼
        b. level
*/

SELECT
    seq as 번호,
    lpad(' ', ((level - 1) * 5)) || name as 부품명, -- lpad()로 level별 들여쓰기를 해서 컬럼의 계층구조 가독성 향상
    PRIOR name as 부모부품명,
    level, -- 계층 레벨값인듯?
    CONNECT_BY_ROOT name,           -- 계층 꼭대기에 있는 노드의 name이 뭐야?
    CONNECT_BY_ISLEAF,              -- 최하위 노드이면 1, 아니면 0
    SYS_CONNECT_BY_PATH(name, '️→') -- 계층간 구분을 내가 넣어준 문자(️→)로 해줘.
FROM tblComputer
    -- START WITH seq = 1         -- 기준이 될 최상위 레코드(Root Record)
    -- START WITH pseq = NULL     -- 윗줄 대신 이렇게 쓰면 코드가 더 유연해짐. 최상위인 컴퓨터는 pseq(부모부품)가 없으니까.
    START WITH seq = (SELECT seq FROM tblComputer WHERE name = '컴퓨터') -- 컴퓨터가 최상인건 아는데 pseq 조건을 잘 모를 때. 코드를 좀더 유연하게.
    CONNECT BY PRIOR seq = pseq;  -- 부모와 자식을 연결하는 조건절(join같은 느낌)


SELECT
    seq as 번호,
    lpad(' ', ((level - 1) * 5)) || name as 부품명,
    level -- 계층 레벨값인듯?
FROM tblComputer
    START WITH seq = 2 -- 기준이 될 최상위 레코드(Root Record), seq=2니까 본체를 최상위 레코드 기준으로 한 컬럼들만 가져옴 (2~7)
    CONNECT BY PRIOR seq = pseq;
    
-- 홍사장네 계층구조화 시켜보기.
SELECT
    lpad(' ', ((level -1) * 2)) || name,
    department
FROM tblSelf
    START WITH super IS NULL
    CONNECT BY PRIOR seq = super;