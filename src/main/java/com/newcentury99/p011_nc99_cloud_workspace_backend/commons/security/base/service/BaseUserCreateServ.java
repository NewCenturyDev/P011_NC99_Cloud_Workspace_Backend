package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityCreateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityCreateServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.dto.BaseUserCreateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.repo.BaseUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
@Service
public class BaseUserCreateServ implements EntityCreateServ<BaseUserProfile, String,
        BaseUserCreateDTO.BaseUserCreateReqDTO, EntityCreateDTO.BulkRequest<BaseUserProfile>> {
    private BaseUserRepo baseUserRepo;

    @Override
    public BaseUserProfile create(BaseUserCreateDTO.BaseUserCreateReqDTO reqDTO) {
        return baseUserRepo.save(reqDTO.toEntity());
    }

    @Override
    public List<BaseUserProfile> bulkCreate(EntityCreateDTO.BulkRequest<BaseUserProfile> reqDTO) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BaseUserProfile> bulkCreate(List<BaseUserProfile> entities) {
        return baseUserRepo.saveAll(entities);
    }

    @Override
    public void createStorage(String entityID) throws IOException {
        Files.createDirectory(Path.of(BaseUserFileIOServ.rootPath, "users", entityID));
    }
}
