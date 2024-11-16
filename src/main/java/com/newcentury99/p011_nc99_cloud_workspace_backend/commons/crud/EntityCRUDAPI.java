package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryAPI;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.*;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.entity.CRUDEntity;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils.APIUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
public class EntityCRUDAPI<T extends CRUDEntity<ID>, ID> {
    private final EntityCRUDServicesFacade<T, ID> entityServices;

    @LibraryAPI
    public <Q extends EntityCreateDTO.Request> ResponseEntity<?> createAPI(Authentication auth, @Valid @RequestBody Q reqDTO) {
        return APIUtil.executeAPI(() -> entityServices.create(auth, reqDTO), "res.entity.create.success");
    }

    @LibraryAPI
    public <Q extends EntityFetchDTO.Request> ResponseEntity<?> fetchAPI(Authentication auth, @Valid @RequestBody Q reqDTO) {
        return APIUtil.executeAPI(() -> entityServices.fetch(auth, reqDTO), "res.entity.fetch.success");
    }

    @LibraryAPI
    public <Q extends EntityUpdateDTO.Request> ResponseEntity<?> updateAPI(Authentication auth, @Valid @RequestBody Q reqDTO) {
        return APIUtil.executeAPI(() -> entityServices.update(auth, reqDTO), "res.entity.update.success");
    }

    @LibraryAPI
    public <Q extends EntityDeleteDTO.Request> ResponseEntity<?> deleteAPI(Authentication auth, @Valid @RequestBody Q reqDTO) {
        return APIUtil.executeAPI(() -> entityServices.delete(auth, reqDTO), "res.entity.delete.success");
    }

    @LibraryAPI
    public <Q extends EntityImportDTO.Request> ResponseEntity<?> importAPI(Authentication auth, @Valid @RequestBody Q reqDTO) {
        return APIUtil.executeAPI(() -> entityServices.importFromDumpFile(auth, reqDTO), "res.entity.import.success");
    }

    @LibraryAPI
    public void exportAPI(Authentication auth, HttpServletResponse response) {
        APIUtil.executeCSVExportAPI(() -> entityServices.exportToDumpFile(auth), response, "entities");
    }
}
