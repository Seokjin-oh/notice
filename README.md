# Spring Boot + Thymeleaf + H2 DB + Maven 이용한 공지 게시판 

## 개발환경

* IDE : IntelliJ
* SpringBoot : 2.3.RELEASE
* Maven
* Java 8
* H2 DB
* Mybatis
* Thymeleaf
* lombok

## 기본정보

서버
> localhost:8080

접속 정보
>username(pw) - test(123), admin(123)

## Building the project
repository:
> https://github.com/Seokjin-oh/notice.git
 
빌드(notice/pom.xml 경로)
> mvn package

실행
> java -jar target/notice-0.0.1-SNAPSHOT.jar
 

## 기능
* spring security 를 이용한 간단한 로그인
* 공지 목록
* 공지 등록
* 공지 수정/삭제
* 파일 첨부 

## Rest API

#### 메뉴 이동

>URL : /moveMenu

>method : GET

>parameter

| param | type | desc |
|:---|:---|:---|
| `view` | String | `이동 할 view 이름(ex. view=notice/list` |

#### 공지 목록 조회
paging 처리 시 row 10개로 지정함
>URL : /notice/list.json

>method : GET

>parameter

| param | type | desc |
|---|:---|:---|
| `pageNo` | int | `페이지 번호` |

>result(json)

| key | type | desc |
|---|:---|:---|
| `code` | String | `성공유무 결과값` |
| `msg` | String | `성공유무의 메시지나 에러내용` |
| `notiTotalCount` | int | `공지 목록 총 갯수` |
| `notiList` | List<Map<String,Object> | `공지 목록` |

### 공지 상세

>URL : /notice/1/detail.json

>method : GET

>parameter

| param | type | desc |
|---|:---|:---|
| `noticeKey` | int | `공지 key` |

>result(json)

| key | type | desc |
|---|:---|:---|
| `code` | String | `성공유무 결과값` |
| `msg` | String | `성공유무의 메시지나 에러내용` |
| `notice` | List<Map<String,Object> | `공지 상세` |

### 공지 등록

>URL : /notice/reg.json

>method : POST

>parameter

| param | type | desc |
|---|:---|:---|
| `title` | String | `공지 제목` |
| `contents` | String | `공지 내용` |
| `files` | MultipartFile[] | `공지 첨부파일` |

>result(json)

| key | type | desc |
|---|:---|:---|
| `code` | String | `성공유무 결과값` |
| `msg` | String | `성공유무의 메시지나 에러내용` |
| `noticeKey` | int | `공지 key` |

### 공지 수정
* 첨부파일 저장된 것은 삭제할 때 removeFileKey 에 저장하여 DB 삭제
* 새로운 파일은 files에 담아 저장

>URL : /notice/1/update.json

>method : POST

>parameter

| param | type | desc |
|---|:---|:---|
| `noticeKey` | int | `공지 key` |
| `title` | String | `공지 제목` |
| `contents` | String | `공지 내용` |
| `removeFileKey` | int[] | `기존 공지 첨부파일 삭제` |
| `files` | MultipartFile[] | `공지 첨부파일` |

>result(json)

| key | type | desc |
|---|:---|:---|
| `code` | String | `성공유무 결과값` |
| `msg` | String | `성공유무의 메시지나 에러내용` |
| `noticeKey` | int | `공지 key` |

### 공지 삭제

>URL : /notice/1

>method : DELETE

>parameter

| param | type | desc |
|---|:---|:---|
| `noticeKey` | int | `공지 key` |

>result(json)

| key | type | desc |
|---|:---|:---|
| `code` | String | `성공유무 결과값` |
| `msg` | String | `성공유무의 메시지나 에러내용` |






