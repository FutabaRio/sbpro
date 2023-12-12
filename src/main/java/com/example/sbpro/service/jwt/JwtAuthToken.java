package com.example.sbpro.service.jwt;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
public final class JwtAuthToken extends JwtToken implements Authentication {

    public static final String TYPE = "auth";
    private static final String CLAIM_AUTHORITIES = "Authorities";
    private boolean authenticated = false;

    public JwtAuthToken(String token, String requiredType, byte[] key) throws JwtInvalidException {
        super(token, requiredType, key);
    }

    public JwtAuthToken(String sub, String jti, LocalDateTime iat, LocalDateTime exp, LocalDateTime nbf, byte[] key) {
        super(sub, jti, iat, exp, nbf, TYPE, key);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorityStrings = getJwt().getPayloads().getStr(CLAIM_AUTHORITIES);
        return StringUtils.commaDelimitedListToSet(authorityStrings).stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        var authorityStrings = authorities.stream().map(GrantedAuthority::getAuthority).toList();
        var encodedAuthorities = StringUtils.collectionToCommaDelimitedString(authorityStrings);
        getJwt().setPayload(CLAIM_AUTHORITIES, encodedAuthorities);
    }


    @Override
    public Object getCredentials() {
        return getJwtId();
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return getSubject();
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }



    @Override
    public String getName() {
        return getSubject();
    }
}
