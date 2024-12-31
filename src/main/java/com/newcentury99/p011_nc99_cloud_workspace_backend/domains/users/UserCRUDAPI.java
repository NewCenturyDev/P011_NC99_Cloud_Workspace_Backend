package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityDeleteDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.dto.UserCreateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.dto.UserDeleteDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.dto.UserFetchDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.dto.UserUpdateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils.APIUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController(value = "/users")
public class UserCRUDAPI {
    private UserCRUDServicesFacade workspaceUserCRUDServicesFacade;

    @PostMapping
    public ResponseEntity<?> createAPI(Authentication auth, UserCreateDTO.UserCreateReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> workspaceUserCRUDServicesFacade.create(auth, reqDTO), "res.user.create.success");
    }

    @GetMapping
    public ResponseEntity<?> fetchAPI(Authentication auth, UserFetchDTO.UserIdFetchReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> workspaceUserCRUDServicesFacade.fetch(auth, reqDTO), "res.user.fetch.success");
    }

    @GetMapping(value = "/myself")
    public ResponseEntity<?> fetchAPI(Authentication auth) {
        return APIUtil.executeAPI(() -> workspaceUserCRUDServicesFacade.fetchMyself(auth), "res.user.fetch.success");
    }

    @GetMapping(value = "/email")
    public ResponseEntity<?> fetchAPI(Authentication auth, UserFetchDTO.UserEmailFetchReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> workspaceUserCRUDServicesFacade.fetch(auth, reqDTO), "res.user.fetch.success");
    }

    @GetMapping(value = "/username")
    public ResponseEntity<?> fetchAPI(Authentication auth, UserFetchDTO.UserUsernameFetchReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> workspaceUserCRUDServicesFacade.fetch(auth, reqDTO), "res.user.fetch.success");
    }

    @GetMapping(value = "/phone")
    public ResponseEntity<?> fetchAPI(Authentication auth, UserFetchDTO.UserPhoneFetchReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> workspaceUserCRUDServicesFacade.fetch(auth, reqDTO), "res.user.fetch.success");
    }

    @PatchMapping
    public ResponseEntity<?> updateAPI(Authentication auth, UserUpdateDTO.UserUpdateReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> workspaceUserCRUDServicesFacade.update(auth, reqDTO), "res.user.update.success");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAPI(Authentication auth, UserDeleteDTO.UserDeleteReqDTO reqDTO) {
        return APIUtil.executeAPI(() -> workspaceUserCRUDServicesFacade.delete(auth, reqDTO), "res.user.delete.success");
    }
}
