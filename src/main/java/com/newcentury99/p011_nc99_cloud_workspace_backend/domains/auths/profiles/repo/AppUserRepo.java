package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.auths.profiles.repo;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.repo.CRUDEntityRepo;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.auths.profiles.entity.AppUserProfile;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends CRUDEntityRepo<AppUserProfile, String> { }
