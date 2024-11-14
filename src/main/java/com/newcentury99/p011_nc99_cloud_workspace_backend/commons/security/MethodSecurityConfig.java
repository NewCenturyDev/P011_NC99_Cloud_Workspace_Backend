package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig {
    // Enable Method Security with default Config
}
