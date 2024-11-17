package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service;

import java.rmi.NoSuchObjectException;

public interface EntityFetchServ<T> {
    T fetchByID(String id) throws NoSuchObjectException;
}
