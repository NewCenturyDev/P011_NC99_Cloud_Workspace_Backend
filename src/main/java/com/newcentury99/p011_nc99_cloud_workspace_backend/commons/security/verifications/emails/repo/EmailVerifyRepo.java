package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.verifications.emails.repo;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.verifications.emails.entity.EmailVerifyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerifyRepo extends JpaRepository<EmailVerifyLog, Long> {
    Optional<EmailVerifyLog> findByEmail(String email);
    Optional<EmailVerifyLog> findByEmailAndVerifiedIsFalse(String email);
    Optional<EmailVerifyLog> findByEmailAndVerifiedIsTrue(String email);
}
