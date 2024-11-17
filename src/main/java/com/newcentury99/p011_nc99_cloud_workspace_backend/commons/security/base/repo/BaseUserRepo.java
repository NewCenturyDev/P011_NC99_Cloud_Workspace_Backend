package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.repo;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.repo.CRUDEntityRepo;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserProfile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRepo extends CRUDEntityRepo<BaseUserProfile, String> {
    Optional<BaseUserProfile> findByEmail(String email);
    Optional<BaseUserProfile> findByUsername(String username);
    Optional<BaseUserProfile> findByPhone(String phone);

    Boolean existsByEmail(String email);
    Boolean existsByPhone(String username);
}
