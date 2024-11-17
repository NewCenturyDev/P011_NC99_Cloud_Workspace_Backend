package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import java.util.List;

public interface EntityDeleteServ<ID> {
    ID delete(ID targetID);
    List<ID> bulkDelete(List<ID> targetIDs);
    void deleteStorage(ID entityID) throws Exception;
}
