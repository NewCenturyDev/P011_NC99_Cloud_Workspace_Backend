package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.entity;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryClass;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.UUID;

@LibraryClass
public class CRUDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "eid")
    protected UUID eid;

    @NotBlank(message = "valid.entity.contributor.blank")
    @Size(max = 250, message = "valid.entity.contributor.size")
    @Column(nullable = false)
    protected String contributor;

    @NotNull(message = "valid.entity.created.null")
    @Column
    protected LocalDateTime createdAt;

    @NotNull(message = "valid.entity.modified.null")
    @Column
    protected LocalDateTime modifiedAt;
}
