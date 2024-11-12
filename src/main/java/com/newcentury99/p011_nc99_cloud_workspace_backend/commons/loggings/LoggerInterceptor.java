package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.loggings;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LoggerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger();
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // 스프링 시큐리티 요청 객체의 경우 다시 캐싱 객체로 래핑하면 오류 발생함으로 제외
        if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper") || request.getClass().getName().contains("StandardMultipartHttpServlet")) {
            logger.warn("Spring Security Wrapped Content Cannot Readable on LoggerInterceptor. Check Filter Order Settings");
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        final ContentCachingRequestWrapper wrappingReq = (ContentCachingRequestWrapper) request;
        String log = """
        ========== REQ BEGIN ==========>>>
                Request URI   : [\{request.getMethod()}] \{request.getRequestURI()}
                Request Param : \{request.getParameterMap().entrySet().stream().map(
                e -> e.getKey() + "=" + String.join(", ", e.getValue())
        ).collect(Collectors.joining(" "))}
        ========== REQ BEGIN ==========>>>
        """;
        logger.info(log);
        return HandlerInterceptor.super.preHandle(wrappingReq, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper") || request.getClass().getName().contains("StandardMultipartHttpServlet")) {
            logger.warn("Spring Security Wrapped Content Cannot Readable on LoggerInterceptor. Check Filter Order Settings");
            return;
        }
        final ContentCachingRequestWrapper wrappingReq = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper wrappingRes = (ContentCachingResponseWrapper) response;

        logger.info("=================================================================================================");
        logger.info("Request URI ====> " + request.getRequestURI());
        logger.info("Request Body ===> " + objectMapper.readTree(wrappingReq.getContentAsByteArray()));
        logger.info("Response Body ==> " + objectMapper.readTree(wrappingRes.getContentAsByteArray()));
        logger.info("=============================================REQUEST=============================================");
        logger.info("===============================================END===============================================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
