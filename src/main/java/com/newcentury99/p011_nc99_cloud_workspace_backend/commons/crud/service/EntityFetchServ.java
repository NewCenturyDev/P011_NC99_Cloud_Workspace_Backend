package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import org.springframework.data.domain.Page;

import java.rmi.NoSuchObjectException;
import java.util.List;

public interface EntityFetchServ<T> {
    T fetchById(String id) throws NoSuchObjectException;
    List<T> fetchByIds(List<String> ids);
    Page<T> fetchByNameSearch(String name, int pageIdx, int pageSize);
}
