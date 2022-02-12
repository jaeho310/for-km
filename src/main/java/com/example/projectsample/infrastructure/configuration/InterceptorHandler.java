package com.example.projectsample.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InterceptorHandler extends HttpServlet implements HandlerInterceptor {

    private final String[] routerList = new String[]{"/product", "/order-list"};
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
        // url을 직접 입력하는 경우 home 컴포넌트로 랜더링
        if (Arrays.asList(this.routerList).contains(request.getRequestURI())) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

}
