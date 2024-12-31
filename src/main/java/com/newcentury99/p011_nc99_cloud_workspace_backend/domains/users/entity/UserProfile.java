package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity;

import com.github.f4b6a3.uuid.UuidCreator;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.databases.converters.StringArrayColumnConverter;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.CommonUserProfile;
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
public class UserProfile extends CommonUserProfile implements UserDetails {
    @Id
    @Column(name = "user_id", columnDefinition = "char(36)")
    private String id;

    @Setter
    @Column
    protected UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UuidCreator.getTimeBased().toString();
        }
    }

    public static UserProfile fromAuthentication(Authentication auth) {
        return (UserProfile) auth.getPrincipal();
    }

    public Boolean hasPrivilege(UserRole required) {
        return this.role.equals(required);
    }

    public void checkPrivilege(UserRole required) throws AccessDeniedException {
        if (!hasPrivilege(required)) {
            throw new AccessDeniedException("FORBIDDEN");
        }
    }

    public static void checkPrivilege(Authentication auth, UserRole required) throws AccessDeniedException {
        UserProfile user = UserProfile.fromAuthentication(auth);
        user.checkPrivilege(required);
    }
}
