package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityUpdateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserRole;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.UserGender;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils.PasswordEncoderUtil;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Locale;

public class BaseUserUpdateDTO extends EntityUpdateDTO {
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class BaseUserUpdateReqDTO extends EntityUpdateDTO.SingleRequest<BaseUserProfile, String> {
        @Size(max = 50, message = "valid.user.username.size")
        private String username;
        @Size(min = 9, max = 13, message = "valid.user.phone.size")
        @Pattern(regexp = "(^02|^\\\\d{3})-(\\\\d{3}|\\\\d{4})-\\\\d{4}", message = "valid.user.phone.phone")
        private String phone;
        private UserGender gender;
        private String language;
        private String country;

        @Override
        public BaseUserProfile toEntity(BaseUserProfile target) {
            target.setUsername(username == null ? target.getUsername() : username);
            target.setPhone(phone == null ? target.getPhone() : phone);
            target.setGender(gender == null ? target.getGender() : gender);
            target.setLanguage(language == null ? target.getLanguage() : Locale.forLanguageTag(language));
            target.setCountry(country == null ? target.getCountry() : Locale.of("", country));
            return target;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class BaseUserLanguageUpdateReqDTO extends EntityUpdateDTO.SingleRequest<BaseUserProfile, String> {
        private String language;

        @Override
        public BaseUserProfile toEntity(BaseUserProfile target) {
            target.setLanguage(language == null ? target.getLanguage() : Locale.forLanguageTag(language));
            return target;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class BaseUserPasswordUpdateReqDTO extends EntityUpdateDTO.SingleRequest<BaseUserProfile, String> {
        @Size(min = 4, max = 20, message = "valid.user.password.size")
        private String password;

        @Override
        public BaseUserProfile toEntity(BaseUserProfile target) {
            target.setPassword(PasswordEncoderUtil.encode(password));
            return target;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class BaseUserRoleUpdateReqDTO extends EntityUpdateDTO.SingleRequest<BaseUserProfile, String> {
        private BaseUserRole role;

        @Override
        public BaseUserProfile toEntity(BaseUserProfile target) {
            target.setRole(role == null ? target.getRole() : role);
            return target;
        }
    }
}
