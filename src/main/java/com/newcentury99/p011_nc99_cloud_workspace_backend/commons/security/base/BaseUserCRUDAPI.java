package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityDeleteDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.dto.BaseUserCreateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.dto.BaseUserFetchDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.dto.BaseUserUpdateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.service.BaseUserCRUDServicesFacade;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils.APIUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController(value = "/users")
public class BaseUserCRUDAPI {
    private BaseUserCRUDServicesFacade baseUserCRUDServicesFacade;

    @PostMapping
    public ResponseEntity<?> createAPI(Authentication auth, BaseUserCreateDTO.BaseUserCreateReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> baseUserCRUDServicesFacade.create(auth, reqDTO), "res.user.create.success");
    }

    @GetMapping
    public ResponseEntity<?> fetchAPI(Authentication auth, BaseUserFetchDTO.BaseUserIDFetchReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> baseUserCRUDServicesFacade.fetch(auth, reqDTO), "res.user.fetch.success");
    }

    @GetMapping(value = "/myself")
    public ResponseEntity<?> fetchAPI(Authentication auth) {
        return APIUtil.executeAPI(() -> baseUserCRUDServicesFacade.fetchMyself(auth), "res.user.fetch.success");
    }

    @GetMapping(value = "/email")
    public ResponseEntity<?> fetchAPI(Authentication auth, BaseUserFetchDTO.BaseUserEmailFetchReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> baseUserCRUDServicesFacade.fetch(auth, reqDTO), "res.user.fetch.success");
    }

    @GetMapping(value = "/username")
    public ResponseEntity<?> fetchAPI(Authentication auth, BaseUserFetchDTO.BaseUserUsernameFetchReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> baseUserCRUDServicesFacade.fetch(auth, reqDTO), "res.user.fetch.success");
    }

    @GetMapping(value = "/phone")
    public ResponseEntity<?> fetchAPI(Authentication auth, BaseUserFetchDTO.BaseUserPhoneFetchReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> baseUserCRUDServicesFacade.fetch(auth, reqDTO), "res.user.fetch.success");
    }

    @PatchMapping
    public ResponseEntity<?> updateAPI(Authentication auth, BaseUserUpdateDTO.BaseUserUpdateReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> baseUserCRUDServicesFacade.update(auth, reqDTO), "res.user.update.success");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAPI(Authentication auth, EntityDeleteDTO.SingleRequest<String> reqDTO) {
        return APIUtil.executeAPI(() -> baseUserCRUDServicesFacade.delete(auth, reqDTO), "res.user.delete.success");
    }
}
