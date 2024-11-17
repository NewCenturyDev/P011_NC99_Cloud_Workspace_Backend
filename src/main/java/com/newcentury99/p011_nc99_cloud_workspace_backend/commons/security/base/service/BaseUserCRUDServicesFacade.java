package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityCreateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityDeleteDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityFetchDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.EntityUpdateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.messages.MessageConfig;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.dto.BaseUserCreateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.dto.BaseUserFetchDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.dto.BaseUserUpdateDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserRole;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@AllArgsConstructor
@Service
public class BaseUserCRUDServicesFacade {
    private final BaseUserCreateServ createServ;
    private final BaseUserFetchServ fetchServ;
    private final BaseUserUpdateServ updateServ;
    private final BaseUserDeleteServ deleteServ;
    private final MessageSource msgSrc = MessageConfig.getErrorMsgSrc();

    @Transactional
    public EntityCreateDTO.SingleResponse<BaseUserProfile> create(Authentication auth, BaseUserCreateDTO.BaseUserCreateReqDTO reqDTO) throws Exception {
        BaseUserProfile.checkPrivilege(auth, BaseUserRole.ADMIN);

        // Create Entity
        BaseUserProfile created = createServ.create(reqDTO);
        createServ.createStorage(created.getId());
        return new EntityCreateDTO.SingleResponse<>(created);
    }

    @Transactional
    public EntityFetchDTO.SingleResponse<BaseUserProfile> fetchMyself(Authentication auth) {
        return new EntityFetchDTO.SingleResponse<>(BaseUserProfile.fromAuthentication(auth));
    }

    @Transactional
    public EntityFetchDTO.SingleResponse<BaseUserProfile> fetch(Authentication auth, EntityFetchDTO.SingleRequest reqDTO) throws Exception {
        BaseUserProfile.checkPrivilege(auth, BaseUserRole.ADMIN);
        return switch (reqDTO) {
            case BaseUserFetchDTO.BaseUserIDFetchReqDTO idReq -> new EntityFetchDTO.SingleResponse<>(fetchServ.fetchByID(idReq.getId()));
            case BaseUserFetchDTO.BaseUserEmailFetchReqDTO emailReq -> new EntityFetchDTO.SingleResponse<>(fetchServ.fetchByEmail(emailReq.getEmail()));
            case BaseUserFetchDTO.BaseUserUsernameFetchReqDTO usernameReq -> new EntityFetchDTO.SingleResponse<>(fetchServ.fetchByUsername(usernameReq.getUsername()));
            case BaseUserFetchDTO.BaseUserPhoneFetchReqDTO phoneReq -> new EntityFetchDTO.SingleResponse<>(fetchServ.fetchByPhone(phoneReq.getPhone()));
            case null, default -> throw new IllegalArgumentException(msgSrc.getMessage(
                    "error.req.type", null, Locale.ENGLISH
            ));
        };
    }

    @Transactional
    public EntityUpdateDTO.SingleResponse<BaseUserProfile> update(Authentication auth, BaseUserUpdateDTO.BaseUserUpdateReqDTO reqDTO) throws Exception {
        BaseUserProfile.checkPrivilege(auth, BaseUserRole.ADMIN);

        BaseUserProfile target = fetchServ.fetchByID(reqDTO.getId());
        return new EntityUpdateDTO.SingleResponse<>(updateServ.update(reqDTO.toEntity(target)));
    }

    @Transactional
    public EntityDeleteDTO.SingleResponse<String> delete(Authentication auth, EntityDeleteDTO.SingleRequest<String> reqDTO) throws Exception {
        BaseUserProfile.checkPrivilege(auth, BaseUserRole.ADMIN);

        String deletedID = deleteServ.delete(reqDTO.getId());
        deleteServ.deleteStorage(deletedID);
        return new EntityDeleteDTO.SingleResponse<>(deletedID);
    }
}
