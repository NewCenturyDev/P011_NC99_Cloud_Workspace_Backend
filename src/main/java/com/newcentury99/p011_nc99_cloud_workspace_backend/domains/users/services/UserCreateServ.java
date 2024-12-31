package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.services;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityCreateServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity.UserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
@Service
public class UserCreateServ implements EntityCreateServ<UserProfile> {
    private UserRepo workspaceUserRepo;

    @Override
    public UserProfile create(UserProfile entity) {
        return workspaceUserRepo.save(entity);
    }

    @Override
    public List<UserProfile> bulkCreate(List<UserProfile> entities) {
        return workspaceUserRepo.saveAll(entities);
    }

    @Override
    public void createStorage(String entityId) throws IOException {
        Files.createDirectory(Path.of(UserFileIOServ.rootPath, "users", entityId));
    }
}
