package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.auths.profiles.entity;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.databases.converters.JsonColumnListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String id;

    @Convert(converter = JsonColumnListConverter.class)
    @Column
    private List<String> groups;

    @Column
    @Enumerated(EnumType.STRING)
    private AppUserRole role;

    public static AppUserProfile fromAuthentication(Authentication auth) {
        return (AppUserProfile) auth.getPrincipal();
    }

    public Boolean hasPrivilege(AppUserRole required) {
        return this.role.equals(required);
    }

    public void checkPrivilege(AppUserRole required) throws AccessDeniedException {
        if (!hasPrivilege(required)) {
            throw new AccessDeniedException("FORBIDDEN");
        }
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }
}
