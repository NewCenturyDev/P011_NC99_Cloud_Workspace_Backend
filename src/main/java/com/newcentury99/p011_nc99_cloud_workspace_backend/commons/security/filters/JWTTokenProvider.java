package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Permission;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JWTTokenProvider {
    private final UserAuthServ userAuthServ;

    // 토큰 생성용 난수 시드값
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // 인증 토큰 유효기간 (한 달)
    public static final long JWT_TOKEN_VALIDITY = 30 * 24 * 60 * 60;

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String createToken(String id, List<Permission> roles, ServiceArea service) {
        // JWT payload 에 저장되는 정보단위, 보통 여기서 user 를 식별하는 값을 넣는다 (claim 의 주체)
        Claims claims = Jwts.claims().setSubject(id);
        // 권한 정보 삽입
        claims.put("roles", roles);
        claims.put("service", service);

        return doCreateToken(id, claims);
    }

    private String doCreateToken(String id, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = getEmailFromToken(token);
        UserProfile profile = (UserProfile) userDetails;
        return (email.equals(profile.getEmail())) && !isTokenExpired(token);
    }
}
