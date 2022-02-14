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

## 2. 상세내용
- 회원가입
```
비밀번호 암호화는 스프링 시큐리티에서 제공하는 비크립트 인코더를 사용
로그인은 암호화된 비밀번호를 만료시간이 20분인 세션을 발급해주며

스프링 인터셉터에서 로그인되지 않은 사용자는 vue router를 사용하여 login 컴포넌트를 랜더링 시켜줍니다.
```
- 상품 조회 및 상품 주문
```
로그인된 사용자는 모든 상품을 조회할 수 있으며
장바구니를 따로 구축하지 않아서 order-product 등의 엔티티 없이 하나의 주문과 하나의 product와 매핑하였습니다.
```
- 회원주문내역 조회
```
사용자 정보를 세션을 통해 확인한 후 사용자 : 주문 = 1 : n 관계의 주문내역을 내려줍니다.
```

- response model
```
response는 해당 모델을 통해 내립니다.
```
```java
@AllArgsConstructor
@Getter
@ToString
public class JsonResult {
    private boolean success = false;
    private Object result = null;
}

@AllArgsConstructor
@Getter
public class JsonResultFail {
  private String message;
}

@AllArgsConstructor
@Getter
public class JsonResultSuccess {
  private Object contents;
}

```

- response annotation
```
@ResponseJsonResult 어노테이션을 만들어 컨트롤러에 적용시켜주며
RestControllerExceptionAdvice에서 예외를 잡아와 500에러보다는 200에 에러메시지나 코드를 리턴하여
클라이언트단과 의사전달을 명확하게 하도록 구현하였습니다.
```
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@ResponseBody
public @interface ResponseJsonResult {
}

@Aspect
@Component
public class ResponseJsonResultAdvice {

  @Around("@annotation(com.example.projectsample.common.util.aop.ResponseJsonResult)")
  private Object responseJsonResult(ProceedingJoinPoint point) throws Throwable {
    Object value = point.proceed();

    return new JsonResult( true, new JsonResultSuccess( value ) );
  }

  @Around("execution(* com.example.projectsample.common.util.exception.RestControllerExceptionAdvice.*(..))")
  public JsonResult responseJsonFail( ProceedingJoinPoint point ) throws Throwable {
    Object results = point.proceed();
    if( results instanceof BusinessException) {
      return new JsonResult( false, new JsonResultFail(((BusinessException) results).getMessage()));
    } else if ( results instanceof BindException  ) {
      return new JsonResult( false, new JsonResultFail("올바르지 못한 요청입니다."));
    } else if ( results instanceof MethodArgumentNotValidException ) {
      return new JsonResult(false, new JsonResultFail("비어있는 값이 있는지 확인하세요"));
    }
    return new JsonResult(false, new JsonResultFail(((Exception) results).getMessage()));
  }
}
```
- dockerizing
```
프론트엔드도 같은 포트를 사용합니다.  
메이븐 플러그인을 추가하여 maven 빌드시 yarn 빌드를 통해 프론트엔드 빌드도 함께 진행하며   
필드 결과물로 resources/static 에 spa 프레임워크로 구축된 index.html파일이 생성됩니다.  
멀티스테이지를 사용하여 빌드시에는 maven-jdk-11을 베이스 이미지로 사용하고 런타임에는 jdk11-slim를 사용하여
이미지를 보다 가볍게 구축하였습니다.
```
```dockerfile
ARG BUILD_IMAGE=maven:3.8-jdk-11
ARG RUNTIME_IMAGE=openjdk:11-jdk-slim

#############################################################################################

#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM ${BUILD_IMAGE} AS build

COPY src ./src
COPY frontend ./frontend
COPY pom.xml ./

RUN mvn -B clean package -DskipTests

#############################################################################################

#############################################################################################
### Stage where Docker is running a java process to run a service built in previous stage ###
#############################################################################################
FROM ${RUNTIME_IMAGE}

COPY --from=build /target/kmong-project-*.jar /app/service.jar

CMD ["java", "-jar", "/app/service.jar"]
#############################################################################################

```

- 아키텍쳐 및 패키지구조
```
앨리스터 코오번의 Hexagonal Architecture 를 적용하였습니다.
스프링은 블락화된 애플리케이션으로 스프링 컨테이너에서 di, ioc를 받아 사용합니다.
aop 등 추상화나 프록시객체가 필요한경우 스프링의 도움을 받아 진행하였습니다. 
layer는 presentation(interface) 계층, application(Business Layer, persistence Layer) 계층, infrastructure(스프링 필터 인터셉터) 계층으로 나눴습니다.
```
```
|-- src
|   |-- main
|   |   |-- java
|   |   |   `-- com
|   |   |       `-- example
|   |   |           `-- projectsample
|   |   |               |-- ProjectSampleApplication.java
|   |   |               |-- application
|   |   |               |   |-- model
|   |   |               |   |   |-- config
|   |   |               |   |   |   |-- ConfigContext.java
|   |   |               |   |   |   |-- JpaConfig.java
|   |   |               |   |   |   `-- LoginUserAuditorAware.java
|   |   |               |   |   |-- dto
|   |   |               |   |   |   |-- MemberResponseDto.java
|   |   |               |   |   |   |-- OrderResponseDto.java
|   |   |               |   |   |   `-- ProductResponseDto.java
|   |   |               |   |   `-- entity
|   |   |               |   |       |-- Member.java
|   |   |               |   |       |-- Order.java
|   |   |               |   |       `-- Product.java
|   |   |               |   |-- repository
|   |   |               |   |   |-- MemberRepository.java
|   |   |               |   |   |-- OrderRepository.java
|   |   |               |   |   `-- ProductRepository.java
|   |   |               |   `-- service
|   |   |               |       |-- MemberService.java
|   |   |               |       |-- OrderService.java
|   |   |               |       `-- ProductService.java
|   |   |               |-- common
|   |   |               |   `-- util
|   |   |               |       |-- aop
|   |   |               |       |   |-- ResponseJsonResult.java
|   |   |               |       |   `-- ResponseJsonResultAdvice.java
|   |   |               |       |-- exception
|   |   |               |       |   |-- BusinessException.java
|   |   |               |       |   `-- RestControllerExceptionAdvice.java
|   |   |               |       `-- result
|   |   |               |           |-- JsonResult.java
|   |   |               |           |-- JsonResultFail.java
|   |   |               |           `-- JsonResultSuccess.java
|   |   |               |-- infrastructure
|   |   |               |   |-- component
|   |   |               |   |   `-- SimpleListener.java
|   |   |               |   `-- configuration
|   |   |               |       |-- InterceptorHandler.java
|   |   |               |       |-- SecurityConfig.java
|   |   |               |       `-- WebConfig.java
|   |   |               `-- interfaces
|   |   |                   |-- controller
|   |   |                   |   `-- api
|   |   |                   |       |-- MemberApiController.java
|   |   |                   |       |-- OrderApiController.java
|   |   |                   |       `-- ProductApiController.java
|   |   |                   `-- dto
|   |   |                       |-- MemberJoinRequestDto.java
|   |   |                       |-- MemberLoginRequestDto.java
|   |   |                       |-- OrderRequestDto.java
|   |   |                       `-- Role.java
|   |   `-- resources
|   |       |-- application.yml
|   |       |-- log4jdbc.log4j2.properties
|   |       `-- logback.xml
|   `-- test
|       `-- java
|           `-- com
|               `-- example
|                   `-- projectsample
|                       |-- AbstractCommonTest.java
|                       |-- ProjectSampleApplicationTests.java
|                       |-- application
|                       |   `-- service
|                       |       |-- MemberServiceTest.java
|                       |       |-- OrderServiceTest.java
|                       |       `-- ProductServiceTest.java
|                       `-- interfaces
|                           `-- controller
|                               `-- api
|                                   |-- MemberApiControllerTest.java
|                                   |-- OrderApiControllerTest.java
|                                   `-- ProductApiControllerTest.java

```

## 3. 테이블 정보
<image src="./table.png" />


## 4. 엔티티 정보
<image src="./entity.png" />


## 5. 실행방법
```
docker build -t test:v1 .
docker run -d -p 8395:8395 test:v1
```

컨테이너를 실행한 후 웹브라우저에서 127.0.0.1:8395에 접근합니다.

