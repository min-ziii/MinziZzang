--  한줄 주석
/*
    다중 라인 주석
    
    Ex01.sql
    계정 우클릭- SQL 워크시트 만들기로 이 문서 만들면 됨
    
    설치한 프로그램들.
    1. Oracle Database 11g Express Edition
        - 데이터베이스(DB)
        - Oracle이 만든 Oracle을 설치한 것.
        - 11g (Version)
        - Express Edition (무료 버전이란 뜻)
        - 실행화면이 없는 프로그램 (콘솔이나 작업표시줄처럼 시각적인 요소가 없음)
            -> Windows: Service / Linux: Demon
            -> 조작 가능한 UI가 없다.
        - 전체 작업용
        
        
    2. SQL Developer
        - DB Client tools (on GUI)
        - 데이터베이스를 조작하는 프로그램
        
    3. SQL*Plus
        - DB Client tools (on CLI)
        - 간단한 작업용
    
    
    Oracle Database 11g Express Edition
    - 오라클 서비스
    - win+R -> services.msc
    
    1. OracleServiceXE
        - 오라클 데이터베이스
        - 메인 서비스
        - 서비스 제어
            - net start 서비스명
            - net stop 서비스명
        
    2. OracleXETNSListener
        - 클라이언트 접속 관리
        
    오라클 설치 중...
    - 암호 입력:(java1234)
    - SYS, SYSTEM 계정 -> 관리자용 기본 계정
        
    SQL Developer 접속
    - 오라클 데이터베이스에 접속
    1. 사용자 이름: systems
    2. 비밀번호 : java1234
    3. 호스트이름 : localhost
    4. 포트: 1521(Listener Port Number)
    5. SID : xe (하나의 컴퓨터에 여러 오라클이 설치됐을 때 구분하는 식별자. orcl1, orcl2 등)
    6. Name : 접속 이력명 -> system@localhost or localhost.system
    
    DB 계정
    1. SYSTEM
        - 로컬 접속 가능
        - 원격 접속 가능
    2. SYS 
        - 로컬 접속 가능
        - 원격 접속 불가 (최고관리자라서 보안 때문에)
        
    3. 일반 계정
        - 생성 후 사용
        3.1 hr, scott
            - 학습용 계정
            - 소량의 데이터 제공
        3.2 직접 생성
            - 수업 중에 hr이 아닌 다른 계정 만들어볼 때.
            - 프로젝트용
            
    hr 계정 사용해보기
    1. 잠긴 계정 풀기
        - alter user 유저명 account unlock;
    2. 계정 암호 바꾸기
        - alter user 유저명 identified by 암호;
        
    show user;
    지금 선택된 계정이 어떤건지 우상단에서 꼭!!! 확인해볼 것
    
    
*/
--블럭을 잡고 Ctrl + enter -> 실행
alter user hr account unlock;

alter user hr identified by java1234;

/*
    DB책 2, 6, 7챕터가 중요 부분
    
    Database = 데이터의 집합
    
    Database Management System (DBMS)
    - 데이터베이스 관리 시스템
    - 데이터 집합 + 조작/관리
    - 오라클
    
    Relational Database Management System (관계형 DBMS)
    - 더 줄이면 RDB
    - 테이블로 데이터를 저장하는 DB
    
    RBDMS의 종류
    - Oracle : 기업용. java에 주로 붙여 씀
    - MS-SQL : 기업용. Microsoft 제작. C++에 많이 붙여 씀
    - DB2 : 기업용. IBM 제작.
    - MYSQL : Oracle이 인수. 무료...에서 유료가 돼버린
    - MariaDB : MySQL과 유사함. 무료.
    - PostreSQL
    - SQLite : 주로 모바일에.
    - H2 : Spring에서 쓰는 초경량 DB
    - MS Access : MS오피스? 개인용 DB.
    
    Oracle DB Versions
    1.0 ~ 11g ~ 23ai
    
    Oracle(DB 서버) <-> SQL Developer(클라이언트 툴) <-> 개발자(클라이언트)
    
    SQL, Structured Query Language
    - Oracle이 사용하는 언어 -> 모든 RDBMS가 사용하는 언어
    - RDB와 대화하는 언어
    
    DB분야 직군
    - 오라클 시스템 + SQL 언어를 다루는 직업
    
    1. 데이터베이스 관리자, DBA
        - Oracle의 모든 것 취급
    
    2. 데이터베이스 개발자
        - Oracle의 거의 모든 것 (관리자의 하위)
    
    3. 응용 프로그램 개발자(=Java 개발자)
        - 일부 작업(대부분 SQL)만
    
    SQL의 특징
    1. DB 제작사와 독립적 (관계가 없음)
        - 모든 DB에서 공통으로 사용
        - 표준 SQL을 제작사들이 자기 제품에 적용시킴
        
    2. 표준SQL, ANSI-SQL
        - 모든 DB에서 사용 가능한 SQL
        
    3. 방언
        - 제작사별로 자기 제품에서만 동작하는 SQL이 존재
        - Oracle: PL/SQL
        - MS-SQL: T-SQL
    
    
        
    Oracle 수업 = 표준SQL(60%) + DB설계(10%) + PL/SQL(30%)
                - 2주 정도
    
    표준 SQL 
    
    1. DDL
        - 데이터 정의어
        - 데이터베이스 오브젝트를 생성/수정/삭제하는 명령어
        - DB 구조를 생성/수정/삭제하는 명령어
        
        a. create: 생성
        b. drop: 삭제
        c. alter: 수정
        
        - 데이터베이스 관리자
        - 데이터베이스 개발자
        - 프로그래머(일부)
    
    2. DML
        - 데이터 조작어
        - 데이터를 추가/수정/삭제/조회하는 명령어
        - 사용 빈도가 가장 높음
        
        CRUD
        a. select: 조회
        b. insert: 생성(추가)
        c. update: 수정
        d. delete: 삭제
        
        - 데이터베이스 관리자
        - 데이터베이스 개발자
        - 프로그래머(*)
    
    3. DCL
        - 데이터 제어어
        - 계정 관리, 보안 관리, 트랜잭션 처리, ...
        
        a. commit
        b. rollback
        c. grant
        d. revoke
        
        - 데이터베이스 관리자
        - 데이터베이스 개발자
        - 프로그래머(일부)
        
    +. DQL
        - Data Query Language
        - DML 중에서 SELECT만 따로 부르는 표현
    
    ++. TCL
        - Transection Query Language
         DCL 중에서 COMMIT, ROLLBACK만 따로 부르는 표현
         
    Oracle 인코딩
    1.0 ~ 8: EUC-KR
    9i ~ 현재: UTF-8
    
    SQL은 대소문자를 구분하지 않는다.
    - 키워드는 대문자, 식별자는 소문자로 구분하는 편.
    
*/

SELECT * FROM tabs;
