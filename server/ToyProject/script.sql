show user;

-- ToyProject > script.sql

create user toy IDENTIFIED by java1234;
grant connect, resource, dba to toy;


-- 유저 테이블
create table tblUser (
    id varchar2(50) primary key,
    pw varchar2(50) not null,
    name varchar2(50) not null,
    email varchar2(100) not null,
    lv number(1) not null,                              -- 등급(1.일반, 2.관리자)
    pic varchar2(100) default 'pic.png' not null,
    intro varchar2(500) null,
    regdate date default sysdate not null,
    ing number(1) default 1 not null                    -- 탈퇴(1.활동, 0.탈퇴)  
);

-- 회원 데이터
insert into tblUser (id, pw, name, email, lv, pic, intro, regdate, ing)
    values ('hong', '1111', '홍길동', 'hong@gmail.com', 1, default, '안녕하세요. 길동이입니다.', default, default);
    
insert into tblUser (id, pw, name, email, lv, pic, intro, regdate, ing)
    values ('dog', '1111', '강아지', 'dog@gmail.com', 1, default, '뭉뭉이!', default, default);
    
insert into tblUser (id, pw, name, email, lv, pic, intro, regdate, ing)
    values ('cat', '1111', '고양이', 'cat@gmail.com', 2, default, '관리자냥이', default, default);
    
select * from tblUser;


commit;

-- 로그인
select * from tblUser where id = ? and pw = ? ;

/*
- "com.test.toy": 메인 패키지
		- "Index.jajva": 시작 페이지
		- "Template.java": 템플릿 페이지
		
	- "com.test.toy.user": 회원
		- "Register.java": 회원 가입
		- "Unregister.java": 회원 탈퇴
		- "Login.java": 로그인
		- "Logout.java": 로그아웃
	
	- "com.test.toy.user.repository": DB(DAO)
		- "UserDAO.java"
		
	- "com.test.toy.user.model": DB(DTO)
		- "UserDTO.java"
        
	- 뷰(webapp > WEB-INF)
		- "views"
			- "index.jsp"
			- "template.jsp"
		- views > "user"
			- "register.jsp"
			- "unregister.jsp"
			- "login.jsp"
			- "logout.jsp"
            
	- 공통 요소 & 리소스
		- views > "inc"
					- "asset.jsp"
					- "header.jsp"
		- webapp > "asset"
						> "css"
							- "main.css"
						> "js"
							- "main.js"
						>" pic"
                        
    *** lombok.jar 다운로드 > dev 파일로 넣은 후
    방법 1. 터미널 열기
            > java -jar lombok.jar
            > 이클립스.exe install
            > 끝! 이클립스 폴더 들어가서 파일 들어가 있나 확인하고
            > ini 메모장으로 열어서 마지막에 "~ --add-modules=ALL-SYSTEM
-javaagent:C:\class\dev\eclipse\lombok.jar" 이거 있나 확인!
    
    방법 2. 
            > 그냥 이클립스 파일에 복붙하고
            > > ini 메모장으로 열어서 마지막에 "~ --add-modules=ALL-SYSTEM
-javaagent:C:\class\dev\eclipse\lombok.jar" 이거 넣기!
                        
*/

