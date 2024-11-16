package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.verifications.emails.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerifyLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_verify_log_id")
    private Long id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private LocalDateTime validUntil;
    @Column(nullable = false)
    private Boolean verified;
}
