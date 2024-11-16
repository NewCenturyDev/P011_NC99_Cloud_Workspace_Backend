package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.auths.profiles.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum AppUserRole implements GrantedAuthority {
    USER("USER"), ADMIN("ADMIN");

    private final String value;

    @Override
    public String getAuthority() {
        return this.value;
    }
}
