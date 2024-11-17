package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BaseUserFileIOServ {
    public static String rootPath;

    @Value("${server.rootDir}")
    public void setRootPath(String rootPath) {
        BaseUserFileIOServ.rootPath = rootPath;
    }
}
