package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityCreateDTO;

import java.io.IOException;
import java.util.List;

public interface EntityCreateServ<T, ID> {
    <Q extends EntityCreateDTO.SingleRequest<?>> T create(Q reqDTO);
    <Q extends EntityCreateDTO.BulkRequest<?>> List<T> bulkCreate(Q reqDTO);
    List<T> bulkCreate(List<T> entities);
    void createStorage(ID entityID) throws IOException;
}
