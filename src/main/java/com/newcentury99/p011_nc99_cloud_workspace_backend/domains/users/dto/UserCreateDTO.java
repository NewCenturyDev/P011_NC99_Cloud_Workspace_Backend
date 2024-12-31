package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityCreateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity.UserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity.UserGender;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils.PasswordEncoderUtil;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;


public class UserCreateDTO extends EntityCreateDTO {
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class UserCreateReqDTO extends EntityCreateDTO.SingleRequest<UserProfile> {
        @Email
        @Size(max = 100, message = "valid.user.email.size")
        private String email;
        @Size(min = 4, max = 20, message = "valid.user.password.size")
        private String password;
        @Size(max = 50, message = "valid.user.username.size")
        private String username;
        @Size(min = 9, max = 13, message = "valid.user.phone.size")
        @Pattern(regexp = "(^02|^\\\\d{3})-(\\\\d{3}|\\\\d{4})-\\\\d{4}", message = "valid.user.phone.phone")
        private String phone;
        private UserGender gender;
        private String language;
        private String country;

        @Override
        public UserProfile toEntity() {
            return UserProfile.builder()
                    .email(email)
                    .password(PasswordEncoderUtil.encode(password))
                    .username(username)
                    .phone(phone)
                    .gender(gender)
                    .language(Locale.forLanguageTag(language))
                    .country(Locale.of("", country.toUpperCase()))
                    .createdAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                    .role(UserRole.USER)
                    .active(true)
                    .verified(true)
                    .build();
        }
    }
}
