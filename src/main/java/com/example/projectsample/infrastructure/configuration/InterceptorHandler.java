package com.example.projectsample.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class InterceptorHandler implements HandlerInterceptor {

    /**
     * 인터셉터에서 세션확인
     * 로그인되지 않은 사용자는 vue router를 사용하여 login 컴포넌트를 랜더링
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object data = session.getAttribute("MemberInfo");
        if (data == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

}
