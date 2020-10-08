# 환경구축
1. sts 설치 - https://spring.io/
2. spring boot 프로젝트 생성
3. fostgresQL 설치 (https://www.postgresql.org/download/)
4. fostgresQL Tool 설치 : https://tableplus.com/blog/2018/04/getting-started-with-tableplus.html  

<BR/>
<BR/>
<BR/>

# 개발미션
## 회원관리 - backend ( TDD, RestAPI, JPA)
- 회원가입
- 회원조회
- 로그인
- 회원수정
- 회원삭제

<BR/>
<BR/>
<BR/>

## Docker로 DB 띄우기
C:\Users\loveb>docker pull postgres  
C:\Users\loveb>docker run -d -p 5432:5432 --name pgsql -it --rm -v pgdata:/d/dev/docker/db/postgres -e POSTGRES_PASSWORD=1234 postgres  
c97063385728a5b08f1af547e9f44a4cc42e372081d8cd97686f4e41f39f6c1a  

C:\Users\loveb>docker ps  
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
c97063385728        postgres            "docker-entrypoint.s…"   6 seconds ago       Up 5 seconds        0.0.0.0:5432->5432/tcp   pgsql
  
C:\Users\loveb>docker exec -it pgsql bash  
root@c97063385728:/# psql -U postgres  
psql (13.0 (Debian 13.0-1.pgdg100+1))  
Type "help" for help.  

postgres=# CREATE DATABASE radius;  
CREATE DATABASE  
postgres=# \q  