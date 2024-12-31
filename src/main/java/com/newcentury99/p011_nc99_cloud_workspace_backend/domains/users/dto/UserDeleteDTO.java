package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityDeleteDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class UserDeleteDTO extends EntityDeleteDTO {
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class UserDeleteReqDTO extends EntityDeleteDTO.SingleRequest {
    }
}
