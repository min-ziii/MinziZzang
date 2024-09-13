SELECT * FROM tblRecBook;
SELECT * FROM tblTeacher;
SELECT * FROM tblStudent;
SELECT * FROM tblCourse;
SELECT * FROM vwTeCourse;   
SELECT * FROM vwTeBookSearch;
SELECT * FROM tblScore;
SELECT * FROM tblReply;
SELECT * FROM tblQuestion;
SELECT * FROM tblTeaNotice;

SET SERVEROUTPUT ON;

-- 교사 개인정보 관련 뷰
CREATE OR REPLACE View vwTeCourse
AS
SELECT
    t.id as TeacherID,
    t.name as TeacherName,
    c.name as CourseName,
    c.endTime as CourseEndTime,
    c.status as CourseStatus,
    s.name as StudentName,
    s.status as StudentStatus,
    s.phone as StudentPhoneNumber
FROM tblTeacher t
INNER JOIN tblCourse c
ON c.seqTeacher = t.seq
INNER JOIN tblStudent s
ON s.seqCourse = c.seq;


-- 교사 개인정보 테이블에 레코드 추가
CREATE OR REPLACE Procedure procTeacher_ins(
    pname varchar2,
    pphone varchar2,
    pid varchar2,
    ppw varchar2,
    pstatus varchar2
)
IS
    vcnt number;
BEGIN
    SELECT count(*) INTO vcnt FROM tblTeacher WHERE id = pid;
    IF vcnt > 0
        THEN raise_application_error(-21001, '이미 등록된 교사ID가 있습니다.');
    ELSE
        INSERT INTO tblTeacher (seq, name, phone, id, pw, status)
            VALUES ((SELECT max(seq) FROM tblTeacher) + 1, pname, pphone, pid, ppw, pstatus);
    END IF;
    COMMIT;
    dbms_output.put_line('교사 정보가 성공적으로 입력되었습니다.');
END procTeacher_ins;
/
CALL procTeacher_ins('나교사', '010-1234-5678', 'SST014', '5678', '대기');

-- 교사 레코드 수정
UPDATE tblTeacher
SET name = '나강사'
WHERE id = 'SST014';

-- 교사 개인정보 테이블의 레코드 삭제
CREATE OR REPLACE Procedure procTeacher_del(
    pid varchar2
)
IS
BEGIN
    IF pid NOT LIKE 'SST___'
        THEN raise_application_error(-21002, '잘못된 교사ID입니다.');
    ELSE
    DELETE FROM tblTeacher WHERE id = pid;
    dbms_output.put_line(pid || '교사 정보가 성공적으로 삭제되었습니다.');
    COMMIT;
    END IF;
END procTeacher_del;
/
CALL procTeacher_del('SST014');

-- 추천도서 뷰 생성
CREATE OR REPLACE View vwTeBookSearch
AS
SELECT
    s.id as StudentID,
    s.seqCourse as StudentCourseNum,
    c.name as CourseName,
    t.name as TeacherName,
    b.name as BookName
FROM tblStudent s
INNER JOIN tblCourse c
ON c.seq = s.seqCourse
INNER JOIN tblTeacher t
ON t.seq = c.seqTeacher
INNER JOIN tblRecBook b
ON b.seqTeacher = t.seq;

-- 추천도서 조회 프로시저
CREATE OR REPLACE Procedure procBook(pid varchar2)
IS
    cursor pBCursor IS SELECT * FROM vwTeBookSearch WHERE studentId = pid; 
    vrow vwTeBookSearch%rowtype;
    pTName vwTeBookSearch%rowtype;
BEGIN

    dbms_output.put_line('---------------------------------------------------------------------------------------------');
    dbms_output.put_line(pid || ' 교육생께서 참고하실 추천도서 이름은 아래와 같습니다.');
    dbms_output.put_line('');
    OPEN pBCursor;
    
    LOOP
    FETCH pBCursor INTO vrow;
    EXIT WHEN pBCursor%NOTFOUND;
    dbms_output.put_line(vrow.BookName);
    END LOOP;
    CLOSE pBCursor;
    dbms_output.put_line('추천도서와 관련된 과정은 [' || vrow.coursename || '] 입니다.');
    dbms_output.put_line('---------------------------------------------------------------------------------------------');
END;
/

CALL procBook('SS23001');

-- 추천도서 입력
CREATE OR REPLACE Procedure procTeBook_ins(
    pseqT number,
    pseqC number,
    pname varchar2,
    pauthor varchar2,
    ppublisher varchar2
)
IS
BEGIN
    IF pseqC <= 0 THEN raise_application_error(-21101, '올바른 과정번호가 아닙니다.');
    ELSE
    INSERT INTO tblRecBook (seq, seqTeacher, seqCourse, name, author, publisher, time) VALUES ((SELECT max(seq) FROM tblRecBook) + 1, pseqT, pseqC, pname, pauthor, ppublisher, SYSDATE);
    dbms_output.put_line('추천도서 [' || pname || '] 등록이 완료되었습니다.');
    END IF;
    COMMIT;
END procTeBook_ins;
/
CALL procTeBook_ins(8, 14, '책제목', '책저자', '출판사');

-- 추천도서 수정
UPDATE tblRecBook
SET author = '저자'
WHERE seq = 56;

-- 추천도서 삭제
DELETE FROM tblRecBook WHERE seqCourse = 14;



-- 질답게시판-질문 테이블용 Trigger
CREATE OR REPLACE Trigger trgQuestion
    AFTER
    INSERT OR UPDATE OR DELETE
    ON tblQuestion
    FOR EACH ROW
BEGIN
    If Inserting Then
        dbms_output.put_line('질문게시판에서 ' || :new.seqStudent || '번 교육생의 제목: [' || :new.title || '], 내용: [' || :new.content || ']인 질문이 등록되었습니다.');
    ElsIf Updating Then
        dbms_output.put_line('질문게시판에서 ' || :old.seq || '번 질문이 수정되었습니다.');
    ElsIf Deleting Then
        dbms_output.put_line('질문게시판에서 ' || :old.seqStudent || '번 교육생의 제목: [' || :old.title || '], 내용: [' || :old.content || ']인 질문이 삭제되었습니다.');
    End If;
END trgQuestion;
/


-- 질답게시판 질문 추가
CREATE OR REPLACE Procedure procStQuestion_ins(
    pCnum IN number,
    pSnum IN number,
    pTitle IN varchar2,
    pContent IN varchar2
)
IS
BEGIN
    INSERT INTO tblQuestion (seq, seqClass, seqStudent, title, content, time) VALUES ((SELECT max(seq) FROM tblQuestion) + 1, pCnum, pSnum, pTitle, pContent, SYSDATE);
    COMMIT;
END;
/
CALL procStQuestion_ins(6, 273, '질문', '내용');


-- 질답게시판 질문 수정
UPDATE tblQuestion
SET title = '제목', time = sysdate
WHERE seq = 102;


-- 질답게시판 질문 삭제
CREATE OR REPLACE Procedure procStQuestion_del(
    pSnum IN number,
    pTitle IN varchar2
)
IS
BEGIN

    DELETE FROM tblQuestion
    WHERE seqStudent = pSnum and title = pTitle;
    COMMIT;
END procStQuestion_del;
/ 

CALL procStQuestion_del(273, '제목');


-- 질답게시판 답변 추가
CREATE OR REPLACE Procedure procTeReply_ins(
    qno IN number,
    preply IN varchar2
)
IS
BEGIN
    INSERT INTO tblReply (seq, seqQuestion, content, time) VALUES ((SELECT max(seq) FROM tblReply) + 1, qno, preply, sysdate);
    dbms_output.put_line(qno || '번 질문에 답변이 성공적으로 추가되었습니다.');
    COMMIT;
END;
/
CALL procTeReply_ins(102, '답변');

-- 질답게시판 답변 수정
CREATE OR REPLACE Procedure procTeReply_upd (
    pnum tblReply.seqQuestion%type,
    pcontent tblReply.content%type
)
IS
BEGIN
    UPDATE tblReply
    SET content = pcontent, time = sysdate
    WHERE seqQuestion = pnum;
    dbms_output.put_line(pnum || '번 질문에 대한 답변이 성공적으로 수정되었습니다.');
    COMMIT;
END;
/
CALL procTeReply_upd(102, '답변입니다.');

-- 질답게시판 답변 삭제
CREATE OR REPLACE Procedure procTeReply_del (pseq number)
IS
BEGIN
    DELETE FROM tblReply WHERE seqQuestion = pseq;
    dbms_output.put_line(pseq || '번 질문에 대한 답변이 성공적으로 삭제되었습니다.');
    COMMIT;
END procTeReply_del;
/
CALL procTeReply_del(102);

-- 강의실 공지사항 조회
CREATE OR REPLACE Procedure procClassNotice(pnum number)
IS
    cursor pNCursor IS SELECT * FROM tblTeaNotice WHERE seqCourse = pnum;
    vrow tblTeaNotice%rowtype;
BEGIN
    dbms_output.put_line('--------------------------------------------------------------------------------------------');
    dbms_output.put_line('                          ' || pnum || ' 번 과정의 강의실 공지사항을 출력합니다.');
    dbms_output.put_line('--------------------------------------------------------------------------------------------');
    
    OPEN pNCursor;
    LOOP
    FETCH pNCursor INTO vrow;
    EXIT WHEN pNCursor%NOTFOUND;
    dbms_output.put_line('--------------------------------------------------------------------------------------------');
    dbms_output.put_line('');
    dbms_output.put_line('                                ' || vrow.title);
    dbms_output.put_line('                                                                           작성일: ' || vrow.time);
    dbms_output.put_line('                                                                           조회수: ' || vrow.viewCount);
    dbms_output.put_line('');
    dbms_output.put_line(vrow.content);
    dbms_output.put_line('');
    dbms_output.put_line('--------------------------------------------------------------------------------------------');
    END LOOP;
    CLOSE pNCursor;
    
END procClassNotice;
/
CALL procClassNotice(1);

-- 강의실 공지사항 전체 삭제
DELETE FROM tblNotice;

-- 교사 로그인
CREATE OR REPLACE Procedure procTeacherLogin(pid varchar2, ppw varchar2)
IS
    vid tblTeacher.id%TYPE;   
BEGIN

    SELECT
        id INTO vid
    FROM tblTeacher
    WHERE id = pid AND pw = ppw;
    
    -- 로그인 성공시 --
        dbms_output.put_line('로그인에 성공하였습니다.');
        dbms_output.put_line('환영합니다!' ||' [ '|| vid ||' ] '|| '님');

    -- 로그인 실패시 예외처리 --
    exception
        when NO_DATA_FOUND then
        dbms_output.put_line('로그인에 실패하였습니다.');
   
END procTeacherLogin;
/
CALL procTeacherLogin('SST001', '1935');


create or replace PROCEDURE procStRecord(pstudent_ID IN VARCHAR2) 
IS
    v_count NUMBER := 0;
BEGIN
    FOR vrow IN (
        SELECT
            s.ID AS 교육생ID,
            s.name AS 교육생명,
            c.name AS 과정명,
            sc.writtentest AS 필기점수,
            sc.practicaltest AS 실기점수,
            sc.attendance AS 출결점수,
            t.name AS 담당교사명
        FROM tblscore sc
        INNER JOIN tblstudent s ON sc.seqstudent = s.seq
        INNER JOIN tblcourse c ON sc.seqcourse = c.seq
        INNER JOIN tblteacher t ON c.seqteacher = t.seq
        WHERE s.ID = pstudent_ID
    )
    LOOP
        v_count := v_count + 1;
        dbms_output.put_line('==========================================================');
        dbms_output.put_line('학생 이름: ' || vrow.교육생명);
        dbms_output.put_line('학생 ID: ' || vrow.교육생ID);
        dbms_output.put_line('----------------------------------------------------------');
        dbms_output.put_line('과정 이름: ' || vrow.과정명);
        dbms_output.put_line('필기 점수: ' || vrow.필기점수 || '점');
        dbms_output.put_line('실기 점수: ' || vrow.실기점수 || '점');
        dbms_output.put_line('출결 점수: ' || vrow.출결점수 || '점');
        dbms_output.put_line('----------------------------------------------------------');
        dbms_output.put_line('담당교사 이름: ' || vrow.담당교사명);
        dbms_output.put_line('==========================================================');
    END LOOP;

    IF v_count = 0 THEN
        dbms_output.put_line('해당 학생 ID에 대한 데이터가 없습니다.');
    END IF;
END procStRecord;
/
exec procStRecord('SS23001');