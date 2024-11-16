package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import java.util.List;

public interface EntityDeleteServ<T, ID> {
    ID delete(T target);
    List<ID> bulkDelete(List<T> targets);
    void deleteStorage(ID entityID) throws Exception;
}
