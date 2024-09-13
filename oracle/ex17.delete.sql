/*
    ex17.delete.sql
    
    DELETE문
    - DML
    - 원하는 행을 삭제하는 명령어
    
    DELETE [FROM] 테이블명 [WHERE절];
*/

COMMIT;
ROLLBACK;

SELECT * FROM tblInsa;

DELETE FROM tblInsa WHERE name = '홍길동'; -- 동명이인 가능성
DELETE FROM tblInsa WHERE num = 1001; -- PK값을 기준으로 했으니 good
DELETE FROM tblInsa; -- 참사