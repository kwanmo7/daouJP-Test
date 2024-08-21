# daouJP-Test

https://github.com/kwanmo7/daouJP-Test

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
- '실행폴더/input/' : inputFile 경로

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

소스 코드
  https://github.com/kwanmo7/daouJP-Test
  - 커맨드
  ```
  git clone https://github.com/kwanmo7/daouJP-Test
  ```

- scheduler
 - application.properties 설정
   # database
    spring.datasource.username=root // MySQL ID 설정
    spring.datasource.password=1111 // MySQL password 설정
    spring.datasource.url=jdbc:mysql://localhost:3306/daoujp // MySQL DB 설정
 - 실행 커맨드
   ```
   ./gradlew bootRun
   ```
 - 테스트코드 실행
   ```
   ./gradlew test
   ```


- restfulAPI
 - application.properties 설정
   # database
    spring.datasource.username=root // MySQL ID 설정
    spring.datasource.password=1111 // MySQL password 설정
    spring.datasource.url=jdbc:mysql://localhost:3306/daoujp // MySQL DB 설정
   # server
    server.port=8080 // 사용할 포트번호 설정(기본포트 : 8080)

   # allow ip
    allowed-ips=127.0.0.1,0:0:0:0:0:0:0:1 // 접근 허용할 IP 설정(127.0.0.1(IPv4), 0:0:0:0:0:0:0:1(IPv6) <-- LocalHost 접근)

  - 실행 커맨드
    ```
    ./gradlew bootRun
    ```
  - 테스트코드 실행
    ```
    ./gradlew test
    ```

    * 시간대별 전체항목 조회에서만 레코드 등록 및 수정 , 삭제 가능
  
  - Log File Directory : 실행폴더/log

- restfulAPI-front
  - API-Key
    .env 파일에 'VITE_API_KEY=사용할 API-Key 값' 을 설정
    * api_key 테이블에 미리 값을 입력해 주세요.(client_name은 아무렇게나 해도 무방)

  1.
  ```
  npm install
  ```
  2.
  ```
  npm run dev (기본 포트 : 5173)
  ```
  
# DB 설정
```
CREATE database daouJP

CREATE TABLE record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    time DATETIME,
    subscribers INT,
    dropouts INT,
    payment_amount INT,
    amount_used INT,
    sales_amount INT
);

CREATE TABLE api_key (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    api_key VARCHAR(255) NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

# 프로젝트 구조

- scheduler
```
project-root/
├── java/
│   └── scheduler/
│       ├── App.java
│       ├── Scheduler.java
│       ├── mapper/
│       │   └── SchedulerMapper.java
│       ├── model/
│       │   └── Record.java
│       ├── service/
│       │   ├── impl/
│       │   │   └── SchedulerServiceImpl.java
│       │   └── SchedulerService.java
│       ├── util/
│       │   └── RecordUtil.java
│       └── vo/
│           └── ConstVO.java
├── resources/
│   ├── application.properties
│   └── scheduler/
│       └── mapper/
│           └── schedulerMapper.xml
└── test/
    ├── java/
    │   └── scheduler/
    │       ├── SchedulerServiceImplTest.java
    │       └── SchedulerTest.java
    └── resources/
        └── application-test.properties
```



- restfulAPI
```
project-root/
├── java/
│   └── restfulapi/
│       ├── App.java
│       ├── config/
│       │   └── ApiKeyFilter.java
│       │   └── IpFilter.java
│       │   └── LoggingFilter.java
│       │   └── RateLimitFilter.java
│       │   └── WebConfig.java
│       ├── controller/
│       │   └── RecordController.java
│       ├── dto/
│       │   └── ApiKey.java
│       │   └── RecordDTO.java
│       ├── exception/
│       │   └── ErrorResponse.java
│       │   └── GlobalExceptionHandler.java
│       │   └── InvalidFormatException.java
│       ├── mapper/
│       │   └── ApiKeyMapper.java
│       │   └── RestfulApiMapper.java
│       └── service/
│           ├── impl/
│           │   └── RecordServiceImpl.java
│           └── RecordService.java
├── resources/
│   ├── application.properties
│   └── restfulapi/
│       └── mapper/
│           ├── apiKeyMapper.xml
│           └── restfulApiMapper.xml
└── test/
    ├── java/
    │   └── restfulapi/
    │       ├── ApiKeyFilterTest.java
    │       ├── RateLimitFilterTest.java
    │       └── RecordController.java
    └── resources/
        └── application-test.properties
```


- restfulAPI-front

```
restfulAPI-front/
├── jsconfig.json
├── package-lock.json
├── package.json
├── vite.config.js
├── .env
├── src/
│   ├── api/
│   │   └── recordApi.js
│   ├── components/
│   │   ├── Amountused.vue
│   │   ├── Dropouts.vue
│   │   ├── List.vue
│   │   ├── Paymentamount.vue
│   │   ├── Salesamount.vue
│   │   └── Subscriber.vue
│   ├── router/
│   │   └── index.js
│   ├── App.vue
│   └── main.js
└── (기타 프로젝트 관련 파일들)
```
