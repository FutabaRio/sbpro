package com.example.sbpro.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] URL_WHITELIST = {
            "/login",
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //关闭CSRF,设置无状态连接
        http.csrf(AbstractHttpConfigurer::disable)
                //不通过Session获取SecurityContext
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //允许匿名访问的接口，如果是测试想要方便点就把这段全注释掉
        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers(URL_WHITELIST).anonymous()
                        .anyRequest().authenticated());
        //添加过滤器


        //开启跨域请求
        http.cors(withDefaults());
        return http.build();
    }
}
