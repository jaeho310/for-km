# kmong-prodjct


## 1. 개발환경
- back-end
  - java 11
  - spring-boot 2.5.6
  - junit5(jupiter), mockito, assertj
  - maven
  - h2
- frontend
  - vue2
  - vuex
  - yarn
- dockerizing(Dockerfile)
  - build image: maven:3.8-jdk-11
  - runtime image: openjdk:11-jdk-slim

## 2. 주요기능
- 회원가입
```
비밀번호 암호화는 스프링 시큐리티에서 제공하는 비크립트 인코더를 사용
만료시간이 20분인 세션을 발급해주며
스프링 인터셉터에서 로그인되지 않은 사용자는 vue router를 사용하여 login 컴포넌트를 랜더링
```
- 상품 조회 및 상품 주문
```
로그인된 사용자는 모든 상품을 조회할 수 있으며
장바구니를 따로 구축하지 않아서 order-product 등의 엔티티 없이 하나의 주문과 하나의 product와 매핑
```
- 회원주문내역 조회
```
사용자 정보를 세션을 통해 확인한 후 사용자 : 주문 = 1 : n 관계의 주문내역을 내려준다.
```

## 3. 테이블 정보
<img src="">
## 4. 엔티티 정보

## 5. 실행방법
```
docker build -t test:v1 .
docker run -d -p 8395:8395 test:v1
```
프론트엔드도 같은 포트를 사용합니다.  
메이븐 플러그인을 추가하여 빌드시 yarn 빌드를 통해 프론트엔드 빌드를 함께 진행되어   
resources/static 에 spa 프레임워크로 구축된 index.html파일이 생성됩니다.  
컨테이너를 실행한 후 웹브라우저에서 127.0.0.1:8395에 접근합니다.

