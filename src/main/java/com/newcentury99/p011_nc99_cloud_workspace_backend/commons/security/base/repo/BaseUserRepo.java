package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.repo;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.repo.CRUDEntityRepo;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.auths.profiles.entity.AppUserProfile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRepo extends CRUDEntityRepo<AppUserProfile, String> {
    Optional<AppUserProfile> findByEmail(String email);
    Optional<AppUserProfile> findByUsername(String username);
    Optional<AppUserProfile> findByPhone(String phone);

    Boolean existsByEmail(String email);
    Boolean existsByPhone(String username);
}
