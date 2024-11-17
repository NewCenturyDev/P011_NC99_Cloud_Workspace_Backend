package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityCreateDTO;

import java.io.IOException;
import java.util.List;

public interface EntityCreateServ<T, ID, DTO extends EntityCreateDTO.SingleRequest<T>, bDTO extends EntityCreateDTO.BulkRequest<T>> {
    T create(DTO reqDTO);
    List<T> bulkCreate(bDTO reqDTO);
    List<T> bulkCreate(List<T> entities);
    void createStorage(ID entityID) throws IOException;
}
