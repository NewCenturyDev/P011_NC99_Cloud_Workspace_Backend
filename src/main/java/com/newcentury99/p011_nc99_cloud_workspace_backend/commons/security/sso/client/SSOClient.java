package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.sso.client;

import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "DMTLABS-AUTH-BACKEND")
@Qualifier("SSOClient")
public interface SSOClient {
    @PostMapping("/oauth2/introspect")
    Response introspectTokenAPI(@SpringQueryMap Map<String, Object> reqDTO);

    @PostMapping("/oauth2/token")
    Response exchangeTokenAPI(@SpringQueryMap Map<String, Object> reqDTO);

    @GetMapping("/users/profiles")
    Response fetchBaseUserProfileByEmail(@RequestHeader("Authorization") String token, @SpringQueryMap Map<String, Object> reqDTO);
}
