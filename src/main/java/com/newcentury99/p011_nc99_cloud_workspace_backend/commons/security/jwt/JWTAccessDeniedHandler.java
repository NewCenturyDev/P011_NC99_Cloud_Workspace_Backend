package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.jwt;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.SecurityResManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {
    // If user doesn't have enough ACL
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        PrintWriter rawResponseWriter = response.getWriter();
        SecurityResManager securityResponseManager = new SecurityResManager();
        securityResponseManager.setResponseHeader(response);
        rawResponseWriter.write(securityResponseManager.makeResponseBody(e));
        rawResponseWriter.flush();
        rawResponseWriter.close();
    }
}
