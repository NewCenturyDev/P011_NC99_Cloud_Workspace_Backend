package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import java.util.List;

public interface EntityDeleteServ {
    String delete(String targetId);
    List<String> bulkDelete(List<String> targetIds);
    void deleteStorage(String targetId) throws Exception;
}
