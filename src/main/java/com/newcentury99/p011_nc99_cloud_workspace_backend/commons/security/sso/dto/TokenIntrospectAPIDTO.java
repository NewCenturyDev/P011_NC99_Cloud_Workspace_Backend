package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.sso.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.CommonUserProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TokenIntrospectAPIDTO extends GeneralResDTO {
    private Boolean active;
    private String clientId;
    private String email;
    private List<String> scope;
    private String sub;
    private String iss;
    private Instant exp;
    private Instant iat;
    private String error;
    private CommonUserProfile profile;
}
