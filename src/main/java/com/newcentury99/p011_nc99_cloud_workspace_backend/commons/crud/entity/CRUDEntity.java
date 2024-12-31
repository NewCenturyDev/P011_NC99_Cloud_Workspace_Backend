package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.entity;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@LibraryClass
@MappedSuperclass
@Getter
@Setter
public class CRUDEntity {
    @NotBlank(message = "valid.entity.name.blank")
    @Size(max = 250, message = "valid.entity.name.size")
    protected String name;

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
