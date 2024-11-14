package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.request.UpdateEntityReqDTO;

import java.util.List;

public interface EntityUpdateServ<T, Q extends UpdateEntityReqDTO> {
    void update(T target, Q reqDTO);
    void reset(T target, List<String> fields);
    T commit(T target);
    List<T> commitAll(List<T> targets);
}
