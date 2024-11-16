package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.sso.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TokenExchangeAPIDTO extends GeneralResDTO {
    private String accessToken;
    private String refreshToken;
    private BaseUserProfile profile;
    private String error;
}
