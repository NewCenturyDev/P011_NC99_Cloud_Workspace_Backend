package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.request.CreateEntityReqDTO;

import java.util.List;

public interface EntityCreateServ<T, Q extends CreateEntityReqDTO> {
    T create(Q reqDTO);
    List<T> createAll(List<T> entities);
    void createStorage(Long entityId) throws Exception;
}
