package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserProfile implements UserDetails {
    @Column(name = "user_id")
    protected String id;
    @Column
    protected Locale language;
    @Column(unique = true, nullable = false)
    protected String email;
    @Column
    protected String password;
    @Column
    protected String username;
    @Column
    protected Locale country;
    @Column
    protected UserGender gender;
    @Column(unique = true)
    protected String phone;
    @Column
    protected LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    @Column
    protected LocalDateTime lastLoginAt;
    @Column
    protected BaseUserRole role;
    @Column(nullable = false)
    protected Boolean verified = false;
    @Column(nullable = false)
    protected Boolean active = false;
    @Column(nullable = false)
    protected Boolean locked = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.verified;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.verified;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
