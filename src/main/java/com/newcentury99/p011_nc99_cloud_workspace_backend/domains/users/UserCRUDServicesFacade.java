package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityCreateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityDeleteDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityFetchDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityUpdateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.messages.MessageConfig;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.dto.UserCreateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.dto.UserFetchDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.dto.UserUpdateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity.UserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity.UserRole;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.services.UserCreateServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.services.UserDeleteServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.services.UserFetchServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.services.UserUpdateServ;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@AllArgsConstructor
@Service
public class UserCRUDServicesFacade {
    private final UserCreateServ createServ;
    private final UserFetchServ fetchServ;
    private final UserUpdateServ updateServ;
    private final UserDeleteServ deleteServ;
    private final MessageSource msgSrc = MessageConfig.getErrorMsgSrc();

    @Transactional
    public EntityCreateDTO.SingleResponse<UserProfile> create(Authentication auth, UserCreateDTO.UserCreateReqDTO reqDTO) throws Exception {
        UserProfile.checkPrivilege(auth, UserRole.ADMIN);

        // Create Entity
        UserProfile created = createServ.create(reqDTO.toEntity());
        createServ.createStorage(created.getId());
        return new EntityCreateDTO.SingleResponse<>(created);
    }

    @Transactional
    public EntityFetchDTO.SingleResponse<UserProfile> fetchMyself(Authentication auth) {
        return new EntityFetchDTO.SingleResponse<>(UserProfile.fromAuthentication(auth));
    }

    @Transactional
    public EntityFetchDTO.Response fetch(Authentication auth, EntityFetchDTO.Request reqDTO) throws Exception {
        UserProfile.checkPrivilege(auth, UserRole.ADMIN);
        return switch (reqDTO) {
            case UserFetchDTO.UserIdFetchReqDTO idReq -> new EntityFetchDTO.SingleResponse<>(fetchServ.fetchById(idReq.getId()));
            case UserFetchDTO.UserIdsFetchReqDTO idsReq -> new EntityFetchDTO.BulkResponse<>(fetchServ.fetchByIds(idsReq.getIds()));
            case UserFetchDTO.UserEmailFetchReqDTO emailReq -> new EntityFetchDTO.SingleResponse<>(fetchServ.fetchByEmail(emailReq.getEmail()));
            case UserFetchDTO.UserUsernameFetchReqDTO usernameReq -> new EntityFetchDTO.SingleResponse<>(fetchServ.fetchByUsername(usernameReq.getUsername()));
            case UserFetchDTO.UserUsernameSearchReqDTO usernameSearchReq -> new EntityFetchDTO.PageResponse<>(fetchServ.fetchByNameSearch(usernameSearchReq.getName(), usernameSearchReq.getPageIdx(), usernameSearchReq.getPageSize()));
            case UserFetchDTO.UserPhoneFetchReqDTO phoneReq -> new EntityFetchDTO.SingleResponse<>(fetchServ.fetchByPhone(phoneReq.getPhone()));
            case null, default -> throw new IllegalArgumentException(msgSrc.getMessage(
                    "error.req.type", null, Locale.ENGLISH
            ));
        };
    }

    @Transactional
    public EntityUpdateDTO.SingleResponse<UserProfile> update(Authentication auth, UserUpdateDTO.UserUpdateReqDTO reqDTO) throws Exception {
        UserProfile.checkPrivilege(auth, UserRole.ADMIN);

        UserProfile target = fetchServ.fetchById(reqDTO.getId());
        return new EntityUpdateDTO.SingleResponse<>(updateServ.update(reqDTO.toEntity(target)));
    }

    @Transactional
    public EntityDeleteDTO.SingleResponse delete(Authentication auth, EntityDeleteDTO.SingleRequest reqDTO) throws Exception {
        UserProfile.checkPrivilege(auth, UserRole.ADMIN);

        String deletedId = deleteServ.delete(reqDTO.getId());
        deleteServ.deleteStorage(deletedId);
        return new EntityDeleteDTO.SingleResponse(deletedId);
    }
}
