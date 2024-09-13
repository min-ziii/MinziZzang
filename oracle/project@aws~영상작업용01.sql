CALL procTeacher_ins('나교사', '010-1234-5678', 'SST014', '5678', '대기');

UPDATE tblTeacher
SET name = '나강사'
WHERE id = 'SST014';

CALL procTeacher_del('SST014');

---

CALL procTeBook_ins(8, 14, '책제목', '책저자', '출판사');

UPDATE tblRecBook
SET author = '저자'
WHERE seq = 56;

DELETE FROM tblRecBook WHERE seqCourse = 14;

---

CALL procStQuestion_ins(6, 273, '질문', '내용');

UPDATE tblQuestion
SET title = '제목', time = sysdate
WHERE seq = 102;

CALL procStQuestion_del(273, '제목');

---

CALL procTeReply_ins(102, '답변');

CALL procTeReply_upd(102, '답변입니다.');

CALL procTeReply_del(102);

---

exec procTeScore_insert(228, 254, 11, 30, 40, 15);

exec procTeScore_update(228,254,10,30,40,15);

exec procTeScore_delete(228);

---

exec procTeCon_insert(162, 400, 5, '24-05-23', '상담 예정이에유', '진행 예정');

exec procTeCon_update(161, 400, 5, '24-06-21', '상담을 안해도 될거 같아요', '진행 예정');

exec procTeCon_delete(160);
exec procTeCon_delete(161);
exec procTeCon_delete(162);

---

exec procTeNo_insert(127, 15, '주말 청소 안내', '이번 주 주말에는 청소가 예정되어 있습니다.', '24-03-15', 1234);

exec procTeNo_update(127, 15, '주말 청소', '이번 주 주말에는 청소가 예정되어 있습니다.', '24-03-15', 1234);

exec procTeNo_delete(127);