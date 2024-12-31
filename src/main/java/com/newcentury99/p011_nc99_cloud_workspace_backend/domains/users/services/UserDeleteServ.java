package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.services;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityDeleteServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
@Service
public class UserDeleteServ implements EntityDeleteServ {
    private UserRepo workspaceUserRepo;

    @Override
    public String delete(String targetId) {
        workspaceUserRepo.deleteById(targetId);
        return targetId;
    }

    @Override
    public List<String> bulkDelete(List<String> targetIds) {
        workspaceUserRepo.deleteAllById(targetIds);
        return targetIds;
    }

    @Override
    public void deleteStorage(String entityId) throws Exception {
        FileUtils.deleteDirectory(Path.of(UserFileIOServ.rootPath, "users", entityId).toFile());
    }
}
