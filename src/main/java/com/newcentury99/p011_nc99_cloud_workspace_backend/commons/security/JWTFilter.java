package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security;

import com.dmtlabs.aidocentserver.commons.security.clients.CommonUserProfile;
import com.dmtlabs.aidocentserver.commons.security.clients.TokenIntrospectAPIDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Order(1)
@Component
/* JWT 토큰 필터를 구현하는 클래스 */
public class JWTFilter extends OncePerRequestFilter {
    private final JWTTokenProvider jwtTokenProvider;


    // 의존성 주입용 생성자
    public JWTFilter(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // request 헤더에서 Authorization 필드를 검출
        final String requestTokenHeader = request.getHeader("Authorization");

        String jwtToken = null;
        Cookie authCookie = null;

        if (request.getCookies() != null) {
            authCookie = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("Authorization")).findFirst().orElse(null);
        }

        if (requestTokenHeader != null) {
            jwtToken = requestTokenHeader.substring(7);
        } else if (authCookie != null) {
            jwtToken = authCookie.getValue();
        } else {
            logger.warn("User requested Ajax but Authorization Header is null");
        }

        try {
            TokenIntrospectAPIDTO tokenBody = jwtTokenProvider.introspectToken(jwtToken);
            if(tokenBody != null && tokenBody.getActive() && SecurityContextHolder.getContext().getAuthentication() == null) {
                CommonUserProfile userDetails = tokenBody.getProfile();
                if (userDetails == null) {
                    logger.warn("Login failed: User Not Found");
                } else if (!userDetails.getVerified()) {
                    logger.warn("Login failed: User Not Verified");
                } else if (!userDetails.getActive()) {
                    logger.warn("Login failed: User is Blocked or Not Activated");
                } else {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, jwtToken, tokenBody.getProfile().getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception ignored) {
        }

        filterChain.doFilter(request,response);
    }

    @Override
    // 검증하지 않을 예외 조건
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return JWTFilterRules.getExcludesByRequest(request);
    }
}