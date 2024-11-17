package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityFetchDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class BaseUserFetchDTO extends EntityFetchDTO {
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class BaseUserIDFetchReqDTO extends EntityFetchDTO.IDRequest<String> {
    }

    @Data
    public static class BaseUserEmailFetchReqDTO implements EntityFetchDTO.SingleRequest {
        @Email
        private String email;
    }

    @Data
    public static class BaseUserUsernameFetchReqDTO implements EntityFetchDTO.SingleRequest {
        @Size(max = 50, message = "valid.user.username.size")
        private String username;
    }

    @Data
    public static class BaseUserPhoneFetchReqDTO implements EntityFetchDTO.SingleRequest {
        @Size(min = 9, max = 13, message = "valid.user.phone.size")
        @Pattern(regexp = "(^02|^\\\\d{3})-(\\\\d{3}|\\\\d{4})-\\\\d{4}", message = "valid.user.phone.phone")
        private String phone;
    }
}
