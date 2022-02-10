package com.example.projectsample.infrastructure.configuration;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 스프링 시큐리티에서 제공하는 비크립트인코더를 사용
     * 해당 인코더는 복호화를 지원하지 않으며
     * 매번 salt를 바꿔 같은 비밀번호가 들어와도 다른 값이 나온다
     * 대신 암호화하기 이전의 비밀번호가 암호화 이후 같은지를 비교하는 기능을 제공한다.
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 인터셉터에서 세션을 통한 인증을 진행하므로 필터관련은 permitAll
     *
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/**").access("permitAll")
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .csrf()
                .ignoringAntMatchers("/h2/**").disable()
                .httpBasic();
    }

    public void configure(WebSecurity web)throws Exception{
        web.ignoring().antMatchers("/h2/**");
    }

}
