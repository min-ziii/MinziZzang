# ssangyongJava

### check the following website:
### https://git-scm.com/book/en/v2/Getting-Started-First-Time-Git-Setup
### https://velog.io/@psk84/.gitignore-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
### https://choiiis.github.io/git/how-to-commit-local-change/
### https://velog.io/@blair-lee/VSCode%EC%97%90%EC%84%9C-Github-%EC%97%85%EB%A1%9C%EB%93%9C%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95%EC%A7%B1%EC%89%AC%EC%9B%80%E3%85%8B%E3%85%8B#%EC%9E%A0%EA%B9%90-%F0%9F%A4%9A-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0-%EC%A0%84%EC%97%90

### $ git config --global user.name "John Doe"
### $ git config --global user.email johndoe@example.com

### git config user.name
### git config user.email

### git 저장소 연결하기


#### 1. 기존 폴더를 Git 저장소로 초기화 (이미 Git 저장소인 경우 생략)
- git init


#### 2. 원격 저장소 추가
- git remote add origin <원격 저장소 URL>


#### 3. 원격 저장소에서 최신 내용 가져오기
- git fetch origin


#### 4. 원격 저장소의 내용을 로컬 폴더에 덮어쓰기 (master 브랜치 사용)
- git reset --hard origin/master


#### 5. 현재 상태 확인
- git status


#### 6. 로그 확인
- git log --oneline




### 로컬 저장소에서 원격 저장소에 올리기

1. git add로 staging area에 올린다
2. git commit으로 로컬 저장소에 저장한다(커밋 메세지 포함)
3. git pull(원격 저장소에 자신의 저장내용을 올리기 전에 원격저장소에 있는 내용을 받아옴)
4. git push(로컬저장소(자신의 컴퓨터)에 있는 내용을 저장함)


#### 1. 로컬  저장소의 내용을 추가 staging area에 올림
- git add .
- 이 때 git status로 본인이 올릴 파일 목록을 확인
- staging area에 올라간 목록을 초기화하고 싶다면 git reset


#### 2. 로컬 저장소에 저장할 소스를 진짜 저장한다
- git commit -m "원하는 메세지"
- 자신의 컴퓨터의 내용을 기록함


#### 3. 원격 저장소의 저장내용을 받아온다
- git pull
- conflict가 나면 해당 파일을 확인해서 꺽쇄안에 내용을 비교해서 저장하고 로컬 저장소에 저장


#### 4. 원격 저장소에 로컬 저장소의 내용을 저장한다
- git push
