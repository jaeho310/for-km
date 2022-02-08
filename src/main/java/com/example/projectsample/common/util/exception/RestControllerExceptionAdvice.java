package com.example.projectsample.common.util.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object bindExceptionHandler(BindException e) {
        log.error(e.getMessage());
        return e;
    }

    /**
     * @author 최재호
     * MethodArgumentNotValidException은 @Valid 어노테이션과 dto에 @Nonnull 조건 등에 맞지않을때 발생한다.
     * @param e
     * @return Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return e;
    }

    /**
     * @author 최재호
     * BusinessException은 Customising한 Exception 클래스로 서비스 로직에서의 예외를 처리한다.
     * @param e
     * @return Exception
     */
    @ExceptionHandler(BusinessException.class)
    public Object businessExceptionHandler(BusinessException e) {
        log.error("BusinessException[ {} ]", e.getMessage());
        return e;
    }

//    /**
//     * @author 최재호
//     * 스프링 시큐리티의 인증, 인가관련 Exception을 핸들링한다.
//     * @param e
//     * @return Exception
//     */
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseBody
//    public Object accessDeniedExceptionHandler(AccessDeniedException e) {
//        log.error(e.getMessage());
//        return e;
//    }

    /**
     * @author 최재호
     * 핸들링하지 않은 예외를 상위클래스인 Exception 클래스로 핸들링한다.
     * @param e
     * @return Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object unknownExceptionHandler(Exception e) {
        log.error(e.getMessage());
        return e;
    }
}
