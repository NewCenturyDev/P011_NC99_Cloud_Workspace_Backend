package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security;

import com.dmtlabs.aidocentserver.commons.http.APIUtil;
import com.dmtlabs.aidocentserver.commons.security.clients.AuthMicroServClient;
import com.dmtlabs.aidocentserver.commons.security.clients.TokenIntrospectAPIDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@RequiredArgsConstructor
/* JWT 토큰을 발행하고 관리하는 클래스 */
public class JWTTokenProvider {
    private final AuthMicroServClient authMicroServClient;
    private final Logger logger = LogManager.getLogger();
    @Value("${auth.clientId}")
    private String COMPATIBLE_CLIENT_ID;
    @Value("${auth.clientSecret}")
    private String COMPATIBLE_CLIENT_SECRET;

    protected TokenIntrospectAPIDTO introspectToken(String token) throws Exception {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("token", token);
        queryParams.put("client_id", COMPATIBLE_CLIENT_ID);
        queryParams.put("client_secret", COMPATIBLE_CLIENT_SECRET);
        return APIUtil.reqMsaAPICall(() -> authMicroServClient.introspectTokenAPI(queryParams), TokenIntrospectAPIDTO.class);
    }
}
