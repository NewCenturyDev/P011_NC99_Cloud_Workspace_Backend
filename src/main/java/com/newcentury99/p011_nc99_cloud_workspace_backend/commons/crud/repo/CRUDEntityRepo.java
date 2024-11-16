package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.repo;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CRUDEntityRepo<T, ID> extends JpaRepository<T, ID> {
    @LibraryAPI
    Boolean existsByEid(ID eid);
}
