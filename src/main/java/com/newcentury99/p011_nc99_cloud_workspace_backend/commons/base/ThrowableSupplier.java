package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base;

public interface ThrowableSupplier<T> {
    T get() throws Exception;
}
