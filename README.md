# BookSpud Server
> 2024-1 CAU CapstoneDesign
<br/>


## 🥔 Service Info

<img width="650" alt="image" src="https://github.com/24-2-CapstoneDesign/Back_Spring/assets/80567210/38eae57f-3b4a-41f0-adde-69f8eb1b520a"/>

<br/>

#### ✨ 감정을 기반으로 북마크를 기록하고, 독후감을 써봐요! ✨

<br/>

- **서비스 소개 영상** : 유튜브 링크~!

<br/>

## 👩‍💻 Contributors

| 한정민 | 이수은 |
|:--:|:--:|
|<img width="325" alt="image" src="https://github.com/24-2-CapstoneDesign/Back_Spring/assets/80567210/66ce2d03-95c6-4dc9-9e1d-bd469e0cb722" /> | <img width="325" alt="image" src="https://github.com/24-2-CapstoneDesign/Back_Spring/assets/80567210/55cd2230-cfda-46f5-892a-8e2908b3c5c1" />|
| Server Developer | Server Developer|
| - 프로젝트 구조 설계 <br/>- 소셜 로그인 관련 기능 구현 <br/>- 북마크 관련 기능 구현|- 프로젝트 구조 설계 <br/>- 프로젝트 배포 <br/>- 책, 독후감 관련 기능 구현|

<br/>


## ⚒️ Tech Stack
- Java, Spring Boot
- MySQL, Spring Data JPA
- AWS : EC2, RDS, S3
- Swagger
- ChatGPT API

<br/>


## 🌻 Database Architecture

![image](https://github.com/24-2-CapstoneDesign/Back_Spring/assets/80567210/28166a1c-d98a-459d-b3a7-dd1fced6c3ea)

<br/>


## 🍀 Infra Architecture

![image](https://github.com/24-2-CapstoneDesign/Back_Spring/assets/80567210/e8eac59e-6167-4889-9539-99fe4e7a6225)

<br/>

## 📝 Project Architecture

BookSpud 서버는 MVC 패턴을 기반으로 개발했습니다.
<br/><br/>

![image](https://github.com/24-2-CapstoneDesign/Back_Spring/assets/80567210/c9a04da8-8cab-48ee-89a7-e72496e512fb)


### Controller
- 사용자의 요청이 진입하는 지점
- 클라이언트가 API로 요청을 보내면 서버에서 요청을 처리한 후 API를 통해 결과를 반환합니다.

### Service
- 비즈니스 로직을 수행하는 계층
- Repository에서 받아온 데이터를 가공하여 Controller에 반환합니다.

### Repository
- Entity에 의해 생성된 DB에 접근하는 메서드들을 이용하기 위한 인터페이스
- JPA interface method를 활용하여 기본적인 CRUD 연산을 수행합니다.

<br/>
