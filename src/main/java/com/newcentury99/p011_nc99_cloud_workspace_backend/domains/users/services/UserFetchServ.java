package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.services;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityFetchServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity.UserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;

@AllArgsConstructor
@Service
public class UserFetchServ implements EntityFetchServ<UserProfile> {
    private UserRepo workspaceUserRepo;

    public UserProfile fetchById(String id) throws NoSuchObjectException {
        return workspaceUserRepo.findById(id).orElseThrow(() ->
                new NoSuchObjectException("error.user.notExist")
        );
    }

    @Override
    public List<UserProfile> fetchByIds(List<String> ids) {
        return workspaceUserRepo.findAllById(ids);
    }

    @Override
    public Page<UserProfile> fetchByNameSearch(String username, int pageIdx, int pageSize) {
        return workspaceUserRepo.findAllByUsernameContains(username, PageRequest.of(pageIdx, pageSize));
    }

    public UserProfile fetchByEmail(String email) throws NoSuchObjectException {
        return workspaceUserRepo.findByEmail(email).orElseThrow(() ->
                new NoSuchObjectException("error.user.notExist")
        );
    }

    public UserProfile fetchByUsername(String username) throws NoSuchObjectException {
        return workspaceUserRepo.findByUsername(username).orElseThrow(() ->
                new NoSuchObjectException("error.user.notExist")
        );
    }

    public UserProfile fetchByPhone(String phone) throws NoSuchObjectException {
        return workspaceUserRepo.findByPhone(phone).orElseThrow(() ->
                new NoSuchObjectException("error.user.notExist")
        );
    }
}
