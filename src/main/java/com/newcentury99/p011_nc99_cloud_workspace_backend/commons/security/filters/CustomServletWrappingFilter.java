package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

public class CustomServletWrappingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappingReq = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappingRes = new ContentCachingResponseWrapper(response);
        wrappingReq.getParameterMap();

        filterChain.doFilter(wrappingReq, wrappingRes);
        wrappingRes.copyBodyToResponse();
    }
}
