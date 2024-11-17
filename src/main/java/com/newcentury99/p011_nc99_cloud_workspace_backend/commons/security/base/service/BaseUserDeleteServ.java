package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityDeleteServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.repo.BaseUserRepo;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
@Service
public class BaseUserDeleteServ implements EntityDeleteServ<String> {
    private BaseUserRepo baseUserRepo;

    @Override
    public String delete(String targetID) {
        baseUserRepo.deleteById(targetID);
        return targetID;
    }

    @Override
    public List<String> bulkDelete(List<String> targetIDs) {
        baseUserRepo.deleteAllById(targetIDs);
        return targetIDs;
    }

    @Override
    public void deleteStorage(String entityID) throws Exception {
        FileUtils.deleteDirectory(Path.of(BaseUserFileIOServ.rootPath, "users", entityID).toFile());
    }
}
