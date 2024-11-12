package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryClass;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@LibraryClass
public class AsyncUtil implements ApplicationContextAware {
    @Getter
    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext appContext) throws BeansException {
        AsyncUtil.appContext = appContext;
    }
}
