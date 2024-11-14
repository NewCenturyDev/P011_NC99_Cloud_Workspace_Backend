package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import java.util.List;

public interface EntityDeleteServ<T> {
    void delete(List<T> targets);
    void deleteStorage(List<Long> entityIds) throws Exception;
}
