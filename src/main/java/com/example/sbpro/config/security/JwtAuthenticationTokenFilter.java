package com.example.sbpro.config.security;

import com.example.sbpro.config.external.SbCommonProperties;
import com.example.sbpro.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final SbCommonProperties properties;
    private final AuthenticationHelper helper;

    public JwtAuthenticationTokenFilter(JwtService jwtService, SbCommonProperties properties, AuthenticationHelper helper) {
        this.jwtService = jwtService;
        this.properties = properties;
        this.helper = helper;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = extractToken(request);
            var auth = jwtService.verifyAndParseAuthToken(token);
            helper.setAuthentication(auth);

        } catch (Exception e) {
//            fixme: 不应该在这里自定义response的返回,应该在global中自动捕获
            logger.trace(e.getMessage());
        }finally {
            filterChain.doFilter(request, response);
        }
    }

    @NotNull
    private String extractToken(HttpServletRequest request) throws Exception {
        var head = request.getHeader(properties.getJwt().getHeader());
        if (head == null) throw new Exception("token not found");
        return head;
    }
}
