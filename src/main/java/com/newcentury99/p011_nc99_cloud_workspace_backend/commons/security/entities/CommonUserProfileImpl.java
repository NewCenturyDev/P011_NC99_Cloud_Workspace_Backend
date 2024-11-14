package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils.ObjectMapperUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonUserProfileImpl implements OAuth2User, CommonUserProfile<CommonUserProfileImpl> {
    private Long id;
    private Locale language;
    protected String email;
    protected String password;
    protected String username;
    protected Locale country;
    protected UserGender gender;
    protected String phone;
    protected LocalDateTime createdAt;
    protected LocalDateTime lastLoginAt;
    protected List<CommonUserACL> privileges;
    protected Boolean verified;
    protected Boolean active;
    protected Boolean locked;

    @Override
    public CommonUserProfileImpl fromAuthentication(Authentication authentication) {
        return (CommonUserProfileImpl) authentication.getPrincipal();
    }

    @Override
    public Boolean hasPrivilege(CommonUserACL required) {
        return this.privileges.contains(required);
    }

    @Override
    public Boolean hasPrivilege(String role) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkPrivilege(CommonUserACL required) {
        if (!hasPrivilege(required)) {
            throw new AccessDeniedException("ERR_MSG");
        }
    }

    @Override
    public void checkPrivilege(String role) {
        if (!hasPrivilege(role)) {
            throw new AccessDeniedException("ERR_MSG");
        }
    }

    @Override
    public String getCurrentServicePrivilege() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Object> getAttributes() {
        ObjectMapper objectMapper = ObjectMapperUtil.getInstance();
        return objectMapper.convertValue(this, new TypeReference<>() {});
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.privileges;
    }

    @Override
    public String getName() {
        return this.username;
    }
}
