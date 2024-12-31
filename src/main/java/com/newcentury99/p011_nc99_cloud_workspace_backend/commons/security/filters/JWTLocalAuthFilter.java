package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.filters;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.jwt.JWTLocalTokenProvider;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.services.UserAuthServ;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Order(1)
@AllArgsConstructor
@Component
/* JWT 토큰 필터를 구현하는 클래스 */
public class JWTLocalAuthFilter extends OncePerRequestFilter {
    private final JWTLocalTokenProvider jwtLocalTokenProvider;
    private final UserAuthServ baseUserAuthServ;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // request 헤더에서 Authorization 필드를 검출
        final String requestTokenHeader = request.getHeader("Authorization");

        String email = null;
        String jwtToken = null;
        Cookie authCookie = null;

        if (request.getCookies() != null) {
            authCookie = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("Authorization")).findFirst().orElse(null);
        }

        if (requestTokenHeader != null) {
            jwtToken = requestTokenHeader.substring(7);
            email = extractEmailFromToken(jwtToken);
        } else if (authCookie != null) {
            jwtToken = authCookie.getValue();
            email = extractEmailFromToken(jwtToken);
        } else {
            logger.warn("User requested Ajax but Authorization Header is null");
        }

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = this.baseUserAuthServ.loadUserByUsername(email);
                if (!userDetails.isAccountNonLocked()) {
                    throw new LockedException("Account not verified");
                }
                if (jwtLocalTokenProvider.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (UsernameNotFoundException e) {
                logger.warn("Login failed: User Not Found");
            } catch (LockedException e) {
                logger.warn("Login failed: User Not Verified");
            }
        }
        filterChain.doFilter(request,response);
    }

    private String extractEmailFromToken(String jwtToken) {
        try {
            return jwtLocalTokenProvider.getEmailFromToken(jwtToken);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

    @Override
    // 검증하지 않을 예외 조건
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return JWTFilterRules.getExcludesByRequest(request);
    }
}