package com.example.sbpro.service.jwt;


import com.example.sbpro.config.external.Jwt;
import com.example.sbpro.config.external.SbCommonProperties;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JwtService {
    private final Jwt jwtProperties;
    private final byte[] key;

    public JwtService(SbCommonProperties properties) {
        jwtProperties = properties.getJwt();
        key = jwtProperties.getSecret().getBytes();
    }

    public JwtAuthToken issueAuthToken(String subject, @Nullable String jwtId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireAt = now.plusSeconds(jwtProperties.getExpire());

        return new JwtAuthToken(subject, jwtId, now, expireAt, now, key);
    }
}