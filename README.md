# daouJP-Test

사용언어 및 프레임워크 : Java 17 , SpringBoot 2.7.18 , Vue.js, Mybatis
사용 DB : MySQL
IDE : IntelliJ Community Edition , VSCode

# scheduler - 스케줄러 구현 문제
- 1번 문제 구현
- 매일 자정에 실행
- 읽을 수 있는 파일 : csv, txt
- 파일별 구분자
  - csv : ,
  - txt : |
- 'scheduler/app/input/' : inputFile 경로

# restfulAPI
- 2~3번 문제에 대한 백엔드 구현
- Java , SpringBoot 사용

# restfulAPI-front
- 2~3번 문제에 대한 프론트엔드 구현
- VITE(Vue.js) 사용

# 실행 환경
빌드도구 : Gradle , VITE
사용 IDE
 - scheduler , restfulAPI : IntelliJ Community Edition
 - restfulAPI-front : VSCode

- scheduler
 - application.properties 설정
   # database
    spring.datasource.username=root // MySQL ID 설정
    spring.datasource.password=1111 // MySQL password 설정
    spring.datasource.url=jdbc:mysql://localhost:3306/daoujp // MySQL DB 설정
 - 실행 커맨드 : ./gradlew bootRun
 - 테스트코드 실행 : ./gradlew test


- restfulAPI
 - application.properties 설정
   # database
    spring.datasource.username=root // MySQL ID 설정
    spring.datasource.password=1111 // MySQL password 설정
    spring.datasource.url=jdbc:mysql://localhost:3306/daoujp // MySQL DB 설정
   # server
    server.port=8080 // 사용할 포트번호 설정(기본포트 : 8080)

   # allow ip
    allowed-ips=127.0.0.1 // 접근 허용할 IP 설정(127.0.0.1 <-- LocalHost 접근)

  - 실행 커맨드 : ./gradlew bootRun
  - 테스트코드 실행 : ./gradlew test

- restfulAPI-front
  1. npm install
  2. npm run dev (기본 포트 : 5173)
  