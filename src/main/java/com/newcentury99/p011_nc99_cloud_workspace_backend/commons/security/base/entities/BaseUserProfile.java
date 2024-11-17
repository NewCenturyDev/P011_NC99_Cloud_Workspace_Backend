package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.databases.converters.JsonColumnListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserProfile extends CommonUserProfile implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String id;

    @Convert(converter = JsonColumnListConverter.class)
    @Column
    private List<String> groups;

    @Setter
    @Column
    protected BaseUserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }

    public static BaseUserProfile fromAuthentication(Authentication auth) {
        return (BaseUserProfile) auth.getPrincipal();
    }

    public Boolean hasPrivilege(BaseUserRole required) {
        return this.role.equals(required);
    }

    public void checkPrivilege(BaseUserRole required) throws AccessDeniedException {
        if (!hasPrivilege(required)) {
            throw new AccessDeniedException("FORBIDDEN");
        }
    }

    public static void checkPrivilege(Authentication auth, BaseUserRole required) throws AccessDeniedException {
        BaseUserProfile user = BaseUserProfile.fromAuthentication(auth);
        user.checkPrivilege(required);
    }
}
