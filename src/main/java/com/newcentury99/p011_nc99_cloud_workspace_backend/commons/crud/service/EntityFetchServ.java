package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityFetchDTO;
import org.springframework.data.domain.Page;

import java.rmi.NoSuchObjectException;
import java.util.List;

public interface EntityFetchServ<T> {
    <Q extends EntityFetchDTO.SingleRequest> T fetch(Q reqDTO) throws NoSuchObjectException;
    <Q extends EntityFetchDTO.BulkRequest> List<T> bulkFetch(Q reqDTO);
    <Q extends EntityFetchDTO.PageRequest> Page<T> pageFetch(Q reqDTO);
}
