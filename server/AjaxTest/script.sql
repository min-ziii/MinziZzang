-- AjaxTest > script.sql

-- 설문조사
create table tblSurvey (
    seq number primary key,          --번호(PK)
    question varchar2(300) not null, --질문
    item1 varchar2(300) not null,    --항목1
    item2 varchar2(300) not null,    --항목2
    item3 varchar2(300) not null,    --항목3
    item4 varchar2(300) not null,    --항목4
    cnt1 number default 0 not null,  --선택1
    cnt2 number default 0 not null,  --선택2
    cnt3 number default 0 not null,  --선택3
    cnt4 number default 0 not null   --선택4

);


insert into tblSurvey 
        values (1, '가장 자신있는 프로그래밍 언어는 무엇인가요?', 'JAVA', 'C#', 'Python','C++', default, default, default, default);
        
        
select * from tblSurvey;

update tblSurvey set
    cnt1 = 10,
    cnt2 = 3,
    cnt3 = 20,
    cnt4 = 7
        where seq = 1;
    commit;