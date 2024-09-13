 SERVEROUTPUT ON;

SELECT * FROM tblTeacher;

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

SELECT * FROM tblTeacher;

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

SELECT * FROM tblTeacher;

SELECT * FROM tblRecBook;

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

SELECT * FROM tblRecBook;   

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

SELECT * FROM tblQuestion;

CREATE OR REPLACE Procedure procStQuestion_ins(
    pCnum IN number,
    pSnum IN number,
    pTitle IN varchar2,
    pContent IN varchar2
)
IS
BEGIN
    INSERT INTO tblQuestion (seq, seqClass, seqStudent, title, content, time)
    VALUES ((SELECT max(seq) FROM tblQuestion) + 1, pCnum, pSnum, pTitle, pContent, SYSDATE);
    COMMIT;
END;
/

SELECT * FROM tblQuestion;

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

SELECT * FROM tblReply;

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

SELECT * FROM tblReply;

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

SELECT * FROM tblReply;

CREATE OR REPLACE Procedure procTeReply_del (pseq number)
IS
BEGIN
    DELETE FROM tblReply WHERE seqQuestion = pseq;
    dbms_output.put_line(pseq || '번 질문에 대한 답변이 성공적으로 삭제되었습니다.');
    COMMIT;
END procTeReply_del;
/

SELECT * FROM tblScore;

create or replace PROCEDURE procTeScore_insert (
    p_seq          IN NUMBER,
    p_seqStudent    IN NUMBER,
    p_seqCourse     IN NUMBER,
    p_writtenTest   IN NUMBER,
    p_practicalTest IN NUMBER,
    p_attendance    IN NUMBER
)
IS    
    v_count number; 
BEGIN
    select count(*) -- 중복값 확인하기 위해
    into v_count
    from tblscore
    where seqStudent = p_seqStudent and seqCourse = p_seqCourse; -- 학생과, 과정은 중복값 입력이 안됨

    IF v_count = 0 and p_seq > 0 THEN
        INSERT INTO tblScore (
            seq,
            seqStudent,
            seqCourse,
            writtenTest,
            practicalTest,
            attendance
        ) VALUES (
            p_seq,
            p_seqStudent,
            p_seqCourse,
            p_writtenTest,
            p_practicalTest,
            p_attendance
        );
        commit;
        DBMS_OUTPUT.PUT_LINE('인서트 완료');
    ELSE
        DBMS_OUTPUT.PUT_LINE('해당 학생의 동일한 과정번호가 이미 존재합니다.');
    END IF;

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('오류: 해당 학생의 정보가 이미 있습니다.'); -- 예외처리 학생중복x
END procTeScore_insert;
/

SELECT * FROM tblScore;

create or replace PROCEDURE procTeScore_update (
    p_seq          IN NUMBER,
    p_seqStudent    IN NUMBER,
    p_seqCourse     IN NUMBER,
    p_writtenTest   IN NUMBER,
    p_practicalTest IN NUMBER,
    p_attendance    IN NUMBER
)
IS
    v_count NUMBER;
BEGIN
    -- 레코드 존재 여부 확인
    SELECT COUNT(*)
    INTO v_count
    FROM tblScore;

    IF v_count > 0 THEN
        -- 레코드가 존재하면 업데이트
        UPDATE tblScore
        SET seqStudent    = p_seqStudent,
            seqCourse     = p_seqCourse,
            writtenTest   = p_writtenTest,
            practicalTest = p_practicalTest,
            attendance    = p_attendance
        where seq = p_seq;
        dbms_output.put_line('데이터가 성공적으로 수정되었습니다.');
    ELSE
        dbms_output.put_line('데이터가 성공적으로 입력되었습니다.'); -- 해당 레코드가 없을 때 새로 입력
    END IF;
    COMMIT;
END procTeScore_update;
/

SELECT * FROM tblScore;

create or replace PROCEDURE procTeScore_delete (
    p_seq          IN NUMBER
)
is
    v_count number;
BEGIN
    select count(*)  
    into v_count
    from tblScore
    where seq = p_seq;

    if p_seq > 0 then
        delete from tblscore where seq = p_seq; 
        commit;
        dbms_output.put_line('데이터 삭제가 완료되었습니다.');
    else 
        dbms_output.put_line('존재하지 않는 데이터입니다.');
    end if;
end procTescore_delete;  
/

SELECT * FROM tblConsultation;

create or replace procedure procTeCon_insert (
    p_seq in number,
    p_seqStudent in varchar2,
    p_seqTeacher in varchar2,
    p_time in date,
    p_content in varchar2,
    p_status in varchar2
)
is
    v_count number;
begin
    -- 중복 체크
    select count(*)
    into v_count
    from tblConsultation
    where seq = p_seq;

    if v_count = 0 then
        if p_seq > 0 then
            insert into tblConsultation(
                seq,
                seqStudent,
                seqTeacher,
                time,
                content,
                status)
                values
                (p_seq,
                 p_seqStudent,
                 p_seqTeacher,
                 p_time,
                 p_content,
                 p_status);
                commit;
                dbms_output.put_line('뾰로롱~~!! 데이터 삽입이 완료되었습니다.');
        else
                dbms_output.put_line('저런. 옯바른 데이터가 아니에요.');
        end if;
    else
        dbms_output.put_line('중복된 데이터에유');
    end if;
end procTeCon_insert;
/

SELECT * FROM tblConsultation;

create or replace procedure procTeCon_update(
    p_seq in number,
    p_seqStudent in varchar2,
    p_seqTeacher in varchar2,
    p_time in date,
    p_content in varchar2,
    p_status in varchar2
)
is
    v_count number;
begin   
    -- 레코드 존재 확인하기
    select count(*)
    into v_count
    from tblConsultation
    where seq = p_seq;

    if v_count > 0 then
        -- 레코드가 존재하면 업데이트 하기
        update tblConsultation
            set
                seqStudent = p_seqStudent,
                seqTeacher = p_seqTeacher,
                time = p_time,
                content = p_content,
                status = p_status
            where seq = p_seq;    
                dbms_output.put_line('입력하신 정보가 수정되었습니다.' );
            -- 데이터가 없으면 삽입하기
    else    
        insert into tblConsultation(
            seq,
            seqStudent,
            seqTeacher,
            time,
            content,
            status)
            values
            (p_seq,
             p_seqStudent,
             p_seqTeacher,
             p_time,
             p_content,
             p_status);
        commit;
            dbms_output.put_line('데이터 수정이 완료되엇어요!');
    end if;
end procTeCon_update;
/

SELECT * FROM tblConsultation;

create or replace procedure procTeCon_delete(
    p_seq in number
)
is
    v_count number;
begin
    select count(*)
    into v_count
    from tblConsultation
    where seq = p_seq;
    
    if p_seq > 0 then
        delete from tblConsultation where seq = p_seq;
        dbms_output.put_line('데이터 삭제가 완료되었어유!');
    else
        dbms_output.put_line('데이터가 없어유!');
    end if;
end procTeCon_delete;
/

SELECT * FROM tblTeaNotice;

create or replace procedure procTeNo_insert(
    p_seq in number,
    p_seqCourse in number,
    p_title in varchar2,
    p_content in varchar2,
    p_time in date,
    p_viewCount in varchar2
)
is
begin
    if p_seq > 0 then
        insert into tblTeaNotice (
        seq,
        seqCourse,
        title,
        content,
        time,
        viewCount
        )
        values (
        p_seq,
        p_seqCourse,
        p_title,
        p_content,
        p_time,
        p_viewCount
        );
        commit;
        dbms_output.put_line('인서트 완료입니다요.');
    else
        dbms_output.put_line('인서트 실패입니다요.');
    end if;
    EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('오류: 중복된 값이 있습니다유.');
end procTeNo_insert;
/

SELECT * FROM tblTeaNotice;

create or replace procedure procTeNo_update (
    p_seq in number,
    p_seqCourse in number,
    p_title in varchar2,
    p_content in varchar2,
    p_time in date,
    p_viewCount in varchar2
)
is
    v_count number; -- 카운트 변수
begin
    select count(*)  --변수 존재 유무 확인 
    into v_count
    from tblTeaNotice
    where seq = p_seq order by seq;
    
    if p_seq > 0 then
        update tblTeaNotice
        set
            seq = p_seq,
            seqCourse = p_seqCourse,
            title = p_title,
            content = p_content,
            time = p_time,
            viewCount = p_viewCount
            where seq = p_seq; 
            dbms_output.put_line('입력하신 데이터가 수정되었습니다.');
    else
        dbms_output.put_line('입력하신 데이터가 새로 추가되었습니다.');
    end if;
end procTeNo_update;
/

SELECT * FROM tblTeaNotice;

create or replace procedure procTeNo_delete (
    p_seq in number
)
is
    v_count number; -- 카운트 변수
begin
    select count(*)
    into v_count
    from tblTeaNotice
    where seq = p_seq;    
    
    if p_seq > 0 then
        delete from tblTeaNotice where seq = p_seq;
        dbms_output.put_line('해당 데이터가 삭제되었습니다.');
    else
        dbms_output.put_line('입력하신 데이터는 없는 데이터 입니다.');
    end if;
end procTeNo_delete;
/
