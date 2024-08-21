# daouJP-Test

https://github.com/kwanmo7/daouJP-Test

使用言語およびフレームワーク : Java 17 , SpringBoot 2.7.18 , Vue.js, Mybatis
使用 DB : MySQL
IDE : IntelliJ Community Edition , VSCode

# scheduler - スケジューラ実装問題
- 1番目の問題の実装
- 毎日深夜0時に実行
- 読み込めるファイル形式 : csv, txt
- ファイルごとの区切り文字
  - csv : ,
  - txt : |
- '実行フォルダ/input/' : inputFile パス

# restfulAPI
- 2~3番目の問題に対するフロントエンド実装
- Java , SpringBoot 使用

# restfulAPI-front
- 2~3番目の問題に対するフロントエンド実装
- VITE(Vue.js) 使用

# 実行環境
ビルドツール : Gradle , VITE
使用 IDE
 - scheduler , restfulAPI : IntelliJ Community Edition
 - restfulAPI-front : VSCode

ソースコード
  https://github.com/kwanmo7/daouJP-Test
  - コマンド : `git clone https://github.com/kwanmo7/daouJP-Test`

- scheduler
 - application.properties 設定
   # database
    spring.datasource.username=root // MySQL ID 設定
    spring.datasource.password=1111 // MySQL password 設定
    spring.datasource.url=jdbc:mysql://localhost:3306/daoujp // MySQL DB 設定
 - 実行コマンド : `./gradlew bootRun`
 - テストコード実行 : `./gradlew test`


- restfulAPI
 - application.properties 設定
   # database
    spring.datasource.username=root // MySQL ID 設定
    spring.datasource.password=1111 // MySQL password 設定
    spring.datasource.url=jdbc:mysql://localhost:3306/daoujp // MySQL DB 設定
   # server
    server.port=8080 // 使用するポート番号設定(基本ポート: 8080)

   # allow ip
    allowed-ips=127.0.0.1,0:0:0:0:0:0:0:1 // アクセスを許可するIP設定(127.0.0.1(IPv4), 0:0:0:0:0:0:0:1(IPv6) <-- ローカルホストアクセス)

  - 実行コマンド : `./gradlew bootRun`
  - テストコード実行 : `./gradlew test`

    * 時間帯別全項目の照会でのみレコードの登録および修正、削除が可能
  
  - Log File Directory : 実行フォルダ/log

- restfulAPI-front
  - API-Key
    .env ファイルに 'VITE_API_KEY=使用するAPIキーの値' を設定
    * api_key テーブルにあらかじめ値を入力してください。(client_nameは任意で構いません)

  1. npm install
  2. npm run dev (基本ポート : 5173)
  

# DB 設定

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


# プロジェクト構造

- scheduler
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



- restfulAPI
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


- restfulAPI-front

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
└── (その他のプロジェクト関連ファイル)
