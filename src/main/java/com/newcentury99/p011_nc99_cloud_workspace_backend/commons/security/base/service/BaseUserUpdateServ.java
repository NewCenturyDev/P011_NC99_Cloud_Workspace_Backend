package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityUpdateServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.repo.BaseUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BaseUserUpdateServ implements EntityUpdateServ<BaseUserProfile> {
    private BaseUserRepo baseUserRepo;

    @Override
    public BaseUserProfile update(BaseUserProfile target) {
        return baseUserRepo.save(target);
    }

    @Override
    public List<BaseUserProfile> bulkUpdate(List<BaseUserProfile> targets) {
        throw new UnsupportedOperationException();
    }
}
