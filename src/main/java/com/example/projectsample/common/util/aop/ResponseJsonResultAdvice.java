package com.example.projectsample.common.util.aop;

import com.example.projectsample.common.util.result.JsonResult;
import com.example.projectsample.common.util.result.JsonResultFail;
import com.example.projectsample.common.util.result.JsonResultSuccess;
import com.example.projectsample.common.util.exception.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.net.BindException;

@Aspect
@Component
public class ResponseJsonResultAdvice {

    @Around("execution(* com.example.projectsample.interfaces.controller.api..*(..))")
    public JsonResult responseJsonSuccess(ProceedingJoinPoint point ) throws Throwable {
        Object results = point.proceed();
        return new JsonResult( true, new JsonResultSuccess( results ) );
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
