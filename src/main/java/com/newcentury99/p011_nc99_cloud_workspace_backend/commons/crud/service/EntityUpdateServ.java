package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityUpdateDTO;

import java.util.List;

public interface EntityUpdateServ<T> {
    <Q extends EntityUpdateDTO.SingleRequest<?, ?>> T update(T target, Q reqDTO);
    <Q extends EntityUpdateDTO.BulkRequest<?, ?>> List<T> bulkUpdate(List<T> targets, Q reqDTO);
}
