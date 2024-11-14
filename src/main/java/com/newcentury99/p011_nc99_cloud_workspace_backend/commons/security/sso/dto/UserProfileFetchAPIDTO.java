package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.sso.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.entities.CommonUserProfileImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserProfileFetchAPIDTO extends GeneralResDTO {
    CommonUserProfileImpl userProfile;
    List<CommonUserProfileImpl> userProfiles;
}
