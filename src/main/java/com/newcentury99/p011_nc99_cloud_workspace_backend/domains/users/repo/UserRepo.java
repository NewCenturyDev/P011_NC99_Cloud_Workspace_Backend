package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.repo;

import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserProfile, String> {
    Optional<UserProfile> findByEmail(String email);
    Optional<UserProfile> findByUsername(String username);
    Optional<UserProfile> findByPhone(String phone);
    Page<UserProfile> findAllByUsernameContains(String username, Pageable pageable);

    Boolean existsByEmail(String email);
    Boolean existsByPhone(String username);
}
