package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityFetchServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.repo.BaseUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;

@AllArgsConstructor
@Service
public class BaseUserFetchServ implements EntityFetchServ<BaseUserProfile> {
    private BaseUserRepo baseUserRepo;

    public BaseUserProfile fetchByID(String id) throws NoSuchObjectException {
        return baseUserRepo.findById(id).orElseThrow(() ->
                new NoSuchObjectException("error.user.notExist")
        );
    }

    public BaseUserProfile fetchByEmail(String email) throws NoSuchObjectException {
        return baseUserRepo.findByEmail(email).orElseThrow(() ->
                new NoSuchObjectException("error.user.notExist")
        );
    }

    public BaseUserProfile fetchByUsername(String username) throws NoSuchObjectException {
        return baseUserRepo.findByUsername(username).orElseThrow(() ->
                new NoSuchObjectException("error.user.notExist")
        );
    }

    public BaseUserProfile fetchByPhone(String phone) throws NoSuchObjectException {
        return baseUserRepo.findByPhone(phone).orElseThrow(() ->
                new NoSuchObjectException("error.user.notExist")
        );
    }
}
