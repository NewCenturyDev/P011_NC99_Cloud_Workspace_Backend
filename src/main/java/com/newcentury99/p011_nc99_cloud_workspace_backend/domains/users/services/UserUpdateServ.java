package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.services;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityUpdateServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity.UserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserUpdateServ implements EntityUpdateServ<UserProfile> {
    private UserRepo workspaceUserRepo;

    @Override
    public UserProfile update(UserProfile target) {
        return workspaceUserRepo.save(target);
    }

    @Override
    public List<UserProfile> bulkUpdate(List<UserProfile> targets) {
        throw new UnsupportedOperationException();
    }
}
