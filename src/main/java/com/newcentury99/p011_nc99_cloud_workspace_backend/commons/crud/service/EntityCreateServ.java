package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import java.io.IOException;
import java.util.List;

public interface EntityCreateServ<T> {
    T create(T entity);
    List<T> bulkCreate(List<T> entities);
    void createStorage(String entityId) throws IOException;
}
