package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.jwt;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.SecurityResManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    // Token is not valid or requested without token
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        PrintWriter rawResWriter = response.getWriter();
        SecurityResManager securityResManager = new SecurityResManager();
        securityResManager.setResponseHeader(response);
        if (request.getRequestURL().toString().contains("admin")) {
            request.setAttribute("msg", "로그인 되지 않은 사용자이거나, 어드민 권한이 없거나, 로그인 토큰이 만료되었습니다.");
            request.setAttribute("nextPage", "/admin");
            request.getRequestDispatcher("/error/denied").forward(request, response);
        } else {
            rawResWriter.write(securityResManager.makeResponseBody(e));
            rawResWriter.flush();
            rawResWriter.close();
        }

    }
}
