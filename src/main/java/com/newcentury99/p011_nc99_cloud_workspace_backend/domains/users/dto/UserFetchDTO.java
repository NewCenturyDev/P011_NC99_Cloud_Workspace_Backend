package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityFetchDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class UserFetchDTO extends EntityFetchDTO {
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class UserIdFetchReqDTO extends EntityFetchDTO.IdRequest {
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class UserIdsFetchReqDTO extends EntityFetchDTO.IdsRequest {
    }

    @Data
    public static class UserEmailFetchReqDTO implements EntityFetchDTO.SingleRequest {
        @Email
        private String email;
    }

    @Data
    public static class UserUsernameFetchReqDTO implements EntityFetchDTO.SingleRequest {
        @Size(max = 50, message = "valid.user.username.size")
        private String username;
    }

    @Data
    public static class UserPhoneFetchReqDTO implements EntityFetchDTO.SingleRequest {
        @Size(min = 9, max = 13, message = "valid.user.phone.size")
        @Pattern(regexp = "(^02|^\\\\d{3})-(\\\\d{3}|\\\\d{4})-\\\\d{4}", message = "valid.user.phone.phone")
        private String phone;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class UserUsernameSearchReqDTO extends EntityFetchDTO.NameSearchRequest {
    }
}
