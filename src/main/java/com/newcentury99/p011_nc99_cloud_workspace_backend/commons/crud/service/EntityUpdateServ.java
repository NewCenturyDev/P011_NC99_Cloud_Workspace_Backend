package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;


import java.util.List;

public interface EntityUpdateServ<T> {
    T update(T target);
    List<T> bulkUpdate(List<T> targets);
}
