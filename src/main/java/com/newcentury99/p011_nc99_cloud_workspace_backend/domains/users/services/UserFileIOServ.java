package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserFileIOServ {
    public static String rootPath;

    @Value("${server.rootDir}")
    public void setRootPath(String rootPath) {
        UserFileIOServ.rootPath = rootPath;
    }
}
