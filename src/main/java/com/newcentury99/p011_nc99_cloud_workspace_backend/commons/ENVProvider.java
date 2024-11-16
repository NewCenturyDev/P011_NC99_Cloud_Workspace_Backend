package com.newcentury99.p011_nc99_cloud_workspace_backend.commons;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;

@AllArgsConstructor
public class ENVProvider {
    private final Environment env;

    public String get(String key) {
        return this.env.getProperty(key);
    }
}
