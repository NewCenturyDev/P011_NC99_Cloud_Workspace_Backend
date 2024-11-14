package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.jwt;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.entities.CommonUserACL;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.entities.CommonUserProfileImpl;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.sso.client.SSOClient;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.sso.dto.TokenIntrospectAPIDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils.APIUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@RequiredArgsConstructor
/* JWT 토큰을 발행하고 관리하는 클래스 */
public class JWTTokenProvider {
    private final SSOClient client;
    private final Logger logger = LogManager.getLogger();
    @Value("${auth.clientId}")
    private String COMPATIBLE_CLIENT_ID;
    @Value("${auth.clientSecret}")
    private String COMPATIBLE_CLIENT_SECRET;
    // 토큰 생성용 난수 시드값
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    // 인증 토큰 유효기간 (한 달)
    public static final long JWT_TOKEN_VALIDITY = 30 * 24 * 60 * 60;

    protected TokenIntrospectAPIDTO introspectToken(String token) throws Exception {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("token", token);
        queryParams.put("client_id", COMPATIBLE_CLIENT_ID);
        queryParams.put("client_secret", COMPATIBLE_CLIENT_SECRET);
        return APIUtil.reqMsaAPICall(() -> client.introspectTokenAPI(queryParams), TokenIntrospectAPIDTO.class);
    }

    private final UserAuthServ userAuthServ;

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
        return getClaimFromToken(token, Claim::getExpiration);
    }

    public String issue(String id, CommonUserACL privileges) {
        // JWT payload 에 저장되는 정보단위, 보통 여기서 user 를 식별하는 값을 넣는다 (claim 의 주체)
        Claims claims = Jwts.claims().setSubject(id);
        // 권한 정보 삽입
        claims.put("roles", privileges.getRole());
        claims.put("service", privileges.getService());

        return write(id, claims);
    }

    private String write(String id, Map<String, Object> claims) {
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
        CommonUserProfileImpl profile = (CommonUserProfileImpl) userDetails;
        return (email.equals(profile.getEmail())) && !isTokenExpired(token);
    }
}
