package com.newcentury99.p011_nc99_cloud_workspace_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class P011Nc99CloudWorkspaceBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(P011Nc99CloudWorkspaceBackendApplication.class, args);
    }
}
