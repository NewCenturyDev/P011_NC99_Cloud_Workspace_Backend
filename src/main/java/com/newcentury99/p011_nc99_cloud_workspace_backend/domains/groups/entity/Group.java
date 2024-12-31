package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.groups.entity;

import com.github.f4b6a3.uuid.UuidCreator;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.entity.CRUDEntity;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.databases.converters.StringArrayColumnConverter;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Group extends CRUDEntity {
    @Id
    @Column(name = "group_id", columnDefinition = "char(36)")
    private String id;

    @Column
    private String owner;

    @Convert(converter = StringArrayColumnConverter.class)
    @Column
    private List<String> participants;


    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UuidCreator.getTimeBased().toString();
        }
    }
}
