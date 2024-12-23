/*
    ex28_modeling.sql
    
    데이터베이스 설계
    1. 요구사항 수집 및 분석
    2. 개념 데이터 모델링
    3. 논리 데이터 모델링
    4. 물리 데이터 모델링
    5. 정규화
  --------- 설계
    6. 데이터베이스 구축(구현)
    
    
    
    데이터 모델링
    - 요구분석 -> 여러가지 데이터가 수집 -> 분석/평가 -> 저장구조 설계어떻게? -> 도식화. 그 산출물을 ERD라고 부름.
    - 데이터를 저장하기 위한 데이터 구조를 설계하는 작업
    - ERD 만드는 작업
    - 개념: 간단한 표현의 설계도 -> 테이블명 + 속성 + 관계 정도만 기술
    - 논리: 속성의 자료형, 길이 + 도메인 정의 + Key 정의 등...
    - 물리: 물리적 식별자 + 실제 DBMS에 맞게 추가 세부 설정
    
    
    Relation -> 관계 -> 테이블
    Relationship -> 관계 -> 테이블 간
    
    1. ERD, Entity Relationship Diagram
    - 엔티티(테이블)간의 관계를 표현한 그림
    - DB모델링 기법 중 하나
    - 손, 오피스, 전문 툴(eXERD, ER_Win, 온라인 툴 등
    
    2. Entity, 개체
    - 다른 엔티티와 분류(구분)될 수 있고
    - 다른 엔티티에 대해 정해진 관계를 맺을 수 있는 데이터 단위
    
    - Relation = Entity = Recorde = Instance = Object > wlqgkq(=테이블, 클래스)
        a. 학생 정보 관리
            - 정보 수집: 아이디, 학생명, 나이, 주소, 연락처 등..
            - 학생(아이디, 학생명, 나이, 주소, 연락처)
        b. 강의실 정보 관리
            - 정부 수집: 강의실명, 크기, 인원수, 용도 등..
            - 강의실(강의실명, 크기, 인원수, 용도)
            
    3. Attribute, 속성
    - Entity를 구성하는 요소
    - 속성(Attribute)의 집합 = Entity
    - 컬럼
    
    4. Relationship, 관계
    - 하나의 레코드(Entity)와 또다른 레코드(Entity)간의 관계
    
    
    
    ERD 그리는 방법
    - 피터첸 표기법 or IE 표기법
    - 학생(아이디, 학생명, 나이, 주소, 연락처)
    - 강의실(강의실명, 크기, 인원수, 용도)
    
    1. Entity
    - 사각형
    - 이름을 작성
    - ERD내의 엔티티명은 유일(중복 불가능)
    
    2. Attribute
    - 원
    - 엔티티와 선으로 연결(소속)
    
    3. Relationship
    - 마름모
    - 엔티티와 엔티티의 관계
    
    
    관계 차수
    - 몇 개의 엔티티와 몇 개의 엔티티가 관계를 맺는지 표현
    - IE(새발) 표기법
    
    1. 1:1
    2. 1:N
    3. N:N
      
    
    
    [비디오 대여점] 모델링 중이다. 산출물을 만들어야 한다.
    
    1. ERD(개념 모델링) 작성 -> draw.io에서 비디오.drawio 제작
    2. 논리 다이어그램 -> eXERD에서 비디오.eXERD 제작 (이 단계에서 절대 N:N이 생기면 안 된다. 1:N:N:1로 바꿔주면 해결)
    
    
    
    정규화, Normalization
    - 자료 손설이나 불필요한 정보를 없애고 데이터의 일관성을 유지하며 데이터 종속성을 최소화하기 위해 ERD를 수정하는 작업
    - 우리가 만든 ERD -> 비정형/비정규화 상태 -> 정규화로 정상화시킴
    - 제1 정규화, 제2 정규화, 제3 정규화, ...
    
    RDBMS가 지향하는 DB 상태
    1. NULL이 최대한 없도록 한다.
    2. 중복값을 가지지 않는다.
    3. 원자값을 가진다.
    
    정규화 목적
    1. NULL 최대한 제거
    2. 중복값 제거
    3. 원자값이 아닌 값 제거
    4. 자료의 삽입/삭제/갱신 이상 현상 제거
    
    이상 현상, Anomaly
    1. 삽입 이상
        - 특정 테이블에 데이터 삽입 시 원하지 않는 데이터까지 같이 넣어야 하는 상황
    2. 갱신 이상
        - 동일한 데이터가 두 개 이상의 테이블에 동시에 저장되는 현상
    3. 삭제 이상
        - 특정 테이블에 데이터를 삭제할 때 원하지 않는 데이터까지 같이 삭제해야 하는 상황
        
    함수 종속
    - 하나의 테이블 안에서 컬럼끼리의 관계
    - 정규화란 '부분 함수 종속'이나 '이행 함수 종속'을 모두 없애고 모든 컬럼의 관계를 '완전 함수 종속'으로 만드는 작업이다.
    
    1. 완전 함수 종속
    2. 부분 함수 종속
    3. 이행 함수 종속
    
    정규화
    - 1NF, 2NF, 3NF, BCNF, 4NF, 5NF
    
    제1 정규화, 1NF
    - 모든 컬럼(속성)은 원자값을 가진다.
    - 여러개로 분리 가능한 값은 하나의 컬럼에 넣지 않는다.
    - 올바른 컬럼과 그렇지 않은 컬럼을 분리
    - 1개짜리 테이블이 1NF를 거치면 2개 이상의 테이블이 된다.
    
    제2 정규화, 2NF
    - 기본 키가 아닌 나머지 컬럼은 기본 키에 완전 함수 종속이어야 한다.
    - 부분 함수 종속이 발견되면 부분 함수 종속을 제거한다.
    - 복합키를 가지는 테이블에서 발견된다.
    
    제3 정규화, 3NF
    - 기본 키가 아닌 나머지 컬럼은 기본 키에 완전 함수 종속이어야 한다.
    - 이행 함수 종속이 발견되면 이행 함수 종속을 제거한다.
*/