package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class CommonUserProfile implements OAuth2User {
    @Column(name = "user_id")
    protected String id;
    @Column(unique = true, nullable = false)
    protected String email;
    @Column
    protected String password;
    @Column
    protected String username;
    @Column(unique = true)
    protected String phone;
    @Column
    protected UserGender gender;
    @Column
    protected Locale language;
    @Column
    protected Locale country;
    @Column
    protected LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    @Column
    protected LocalDateTime lastLoginAt;
    @Column(nullable = false)
    protected Boolean verified = false;
    @Column(nullable = false)
    protected Boolean active = false;
    @Column(nullable = false)
    protected Boolean locked = false;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public boolean isAccountNonExpired() {
        return this.verified;
    }

    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    public boolean isCredentialsNonExpired() {
        return this.verified;
    }

    public boolean isEnabled() {
        return this.active;
    }

    @Override
    public String getName() {
        return this.email;
    }
}
