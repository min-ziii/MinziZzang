/*
    ex14_sequence.sql
    
    시퀀스
    - 데이터베이스 객체 중 하나 (테이블, 제약 사항, 시퀀스)
    - Oracle 전용 객체 (다른 DBMS 제품에는 없음)
    - 일련번호를 생성하는 객체
    - 주로 식별자를 만드는 데 사용한다. 그 중에서도 PK값을 만들 때 주로 사용하며, 다른 식별자도 만들 수 있다.
    
    시퀀스 객체 생성하기
    CREATE SEQUENCE 시퀀스명;
    
    시퀀스 객체 삭제하기
    DROP Sequence 시퀀스명;
    
    시퀀스 객체 사용하기
    - 시퀀스명.nextVal
    - 시퀀스명.currVal
*/
-- DB Object
-- 명명법은 Hungarian으로 하자.

DROP sequence seqNum;
CREATE Sequence seqNum;
CREATE Sequence seqNum start with 17;

SELECT sysdate FROM dual;
SELECT seqNum.nextVal FROM dual; -- 계속 변함. 프로그램을 껐다 켜도 물리적으로 계속 유지됨
SELECT seqNum.currVal FROM dual; -- 안 변함. 프로그램을 껐다 켜도 물리적으로 계속 유지됨


INSERT INTO tblMemo(seq, name, memo, regdate) 
             VALUES(seqNum.nextVal, '시퀀스', '일련번호입니다.', sysdate);
             
SELECT * FROM tblMemo;

-- 쇼핑몰의 상품번호가 ABC001, ABC002, ABC003 순으로 되어있다고 가정
SELECT 'ABC' || seqNum.nextVal FROM dual;
-- ABCxx에서 ABCxxx로 바꾸고 싶다면?
SELECT 'ABC' || lpad(seqNum.nextVAl, 3, 0) FROM dual;

--------------------------------------------------------------------------

/*
    시퀀스 객체 생성하기
    CREATE SEQUENCE 시퀀스명;
    
    시퀀스 객체 생성하기(실제 형식)
    CREATE SEQUENCE 시퀀스명
                increment by n  -- 증감치
                start with n    -- 시작값
                maxvalue n      -- 최댓값
                minvalue n      -- 최솟값
                cycle           -- 순환유무
                cache n;        -- 임시 저장 관련
*/
DROP Sequence seqTest;

CREATE Sequence seqTest
            --increment by -1
            --start with 10
            --maxvalue 10
            --minvalue 1
            --cycle
            cache 20 -- 기본값은 20
            ;

SELECT seqTest.nextVal FROM dual;