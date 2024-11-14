package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonUserACL implements GrantedAuthority {
    private String code;
    private String tenancy;
    private String group;
    private String service;
    private String role;
    private String description;

    @Override
    public String getAuthority() {
        return this.code;
    }
}
