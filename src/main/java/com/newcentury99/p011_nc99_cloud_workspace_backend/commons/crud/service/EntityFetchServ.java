package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.rmi.NoSuchObjectException;
import java.util.List;

public interface EntityFetchServ<T> {
    T fetchEntityById(Long entityId) throws NoSuchObjectException;

    List<T> byId(List<Long> entityIds) throws NoSuchObjectException;

    Page<T> listAllWithPage(int pageIdx, int pageLimit);

    List<T> listAllWithSort(Sort sort);
}
