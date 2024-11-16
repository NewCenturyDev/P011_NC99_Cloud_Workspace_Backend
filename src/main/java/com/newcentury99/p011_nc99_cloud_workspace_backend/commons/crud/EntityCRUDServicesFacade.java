package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.*;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.entity.CRUDEntity;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityCreateServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityDeleteServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityFetchServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.service.EntityUpdateServ;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.messages.MessageConfig;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.auths.profiles.entity.AppUserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.auths.profiles.entity.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Locale;

@AllArgsConstructor
public abstract class EntityCRUDServicesFacade<T extends CRUDEntity<ID>, ID> {
    private final EntityCreateServ<T, ID> createServ;
    private final EntityFetchServ<T> fetchServ;
    private final EntityUpdateServ<T> updateServ;
    private final EntityDeleteServ<T, ID> deleteServ;
    private final MessageSource msgSrc = MessageConfig.getErrorMsgSrc();

    public <Q extends EntityCreateDTO.Request> EntityCreateDTO.Response create(Authentication auth, Q reqDTO) throws Exception {
        // Check Permission
        AppUserProfile user = AppUserProfile.fromAuthentication(auth);
        user.checkPrivilege(AppUserRole.ADMIN);

        // Create Entity
        return switch (reqDTO) {
            case EntityCreateDTO.SingleRequest<?> singleRequest ->  {
                // Create Entity DB Record
                T created = createServ.create(singleRequest);
                if (created.getHasStaticFile()) {
                    // Create Entity Static File Storage
                    createServ.createStorage(created.getId());
                }
                yield new EntityCreateDTO.SingleResponse<>(created);
            }
            case EntityCreateDTO.BulkRequest<?> bulkRequest -> {
                // Create Entity DB Record
                List<T> created = createServ.bulkCreate(bulkRequest);
                // Create Entity Static File Storage
                List<ID> storageNeededEntityIDs = created.stream()
                        .filter(CRUDEntity::getHasStaticFile)
                        .map(CRUDEntity::getId)
                        .toList();
                for (ID id : storageNeededEntityIDs) {
                    createServ.createStorage(id);
                }
                yield new EntityCreateDTO.BulkResponse<>(created);
            }
            case null, default -> throw new IllegalArgumentException(msgSrc.getMessage(
                    "error.req.type", null, Locale.ENGLISH
            ));
        };
    }

    public <Q extends EntityFetchDTO.Request> EntityFetchDTO.Response fetch(Authentication auth, Q reqDTO) throws Exception {
        // Fetch Entity Records
        return switch (reqDTO) {
            case EntityFetchDTO.SingleRequest singleRequest -> new EntityFetchDTO.SingleResponse<>(fetchServ.fetch(singleRequest));
            case EntityFetchDTO.BulkRequest bulkRequest -> new EntityFetchDTO.BulkResponse<>(fetchServ.bulkFetch(bulkRequest));
            case EntityFetchDTO.PageRequest pageRequest -> new EntityFetchDTO.PageResponse<>(fetchServ.pageFetch(pageRequest));
            case null, default -> throw new IllegalArgumentException(msgSrc.getMessage(
                    "error.req.type", null, Locale.ENGLISH
            ));
        };
    }

    public <Q extends EntityUpdateDTO.Request> EntityUpdateDTO.Response update(Authentication auth, Q reqDTO) throws Exception {
        // Check Permission
        AppUserProfile user = AppUserProfile.fromAuthentication(auth);
        user.checkPrivilege(AppUserRole.ADMIN);

        // Update Entity Records
        return switch (reqDTO) {
            case EntityUpdateDTO.SingleRequest<?, ?> singleRequest -> {
                // Fetch Target
                T target = fetchServ.fetch(new EntityFetchDTO.IDRequest<>(singleRequest.getId()));
                yield new EntityUpdateDTO.SingleResponse<>(updateServ.update(target, singleRequest));
            }
            case EntityUpdateDTO.BulkRequest<?, ?> bulkRequest -> {
                // Fetch Targets
                List<T> targets = fetchServ.bulkFetch(new EntityFetchDTO.IDsRequest<>(bulkRequest.getIds()));
                yield new EntityUpdateDTO.BulkResponse<>(updateServ.bulkUpdate(targets, bulkRequest));
            }
            case null, default -> throw new IllegalArgumentException(msgSrc.getMessage(
                    "error.req.type", null, Locale.ENGLISH
            ));
        };
    }

    public <Q extends EntityDeleteDTO.Request> EntityDeleteDTO.Response delete(Authentication auth, Q reqDTO) throws Exception {
        // Check Permission
        AppUserProfile user = AppUserProfile.fromAuthentication(auth);
        user.checkPrivilege(AppUserRole.ADMIN);

        return switch (reqDTO) {
            case EntityDeleteDTO.SingleRequest<?> singleRequest -> {
                // Fetch Target
                T target = fetchServ.fetch(new EntityFetchDTO.IDRequest<>(singleRequest.getId()));
                if (target.getHasStaticFile()) {
                    deleteServ.deleteStorage(target.getId());
                }
                yield new EntityDeleteDTO.SingleResponse<>(deleteServ.delete(target));
            }
            case EntityDeleteDTO.BulkRequest<?> bulkRequest -> {
                // Fetch Targets
                List<T> targets = fetchServ.bulkFetch(new EntityFetchDTO.IDsRequest<>(bulkRequest.getIds()));
                // Delete Entity Static Files
                List<ID> diskRemovalRequired = targets.stream()
                        .filter(CRUDEntity::getHasStaticFile)
                        .map(CRUDEntity::getId)
                        .toList();
                for (ID id : diskRemovalRequired) {
                    deleteServ.deleteStorage(id);
                }
                yield new EntityDeleteDTO.BulkResponse<>(deleteServ.bulkDelete(targets));
            }
            case null, default -> throw new IllegalArgumentException(msgSrc.getMessage(
                    "error.req.type", null, Locale.ENGLISH
            ));
        };
    }

    public <Q extends EntityImportDTO.Request> EntityImportDTO.Response importFromDumpFile(Authentication auth, Q reqDTO) throws Exception {
        // Check Permission
        AppUserProfile user = AppUserProfile.fromAuthentication(auth);
        user.checkPrivilege(AppUserRole.ADMIN);

        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }

    public byte[] exportToDumpFile(Authentication auth) throws Exception {
        // Check Permission
        AppUserProfile user = AppUserProfile.fromAuthentication(auth);
        user.checkPrivilege(AppUserRole.ADMIN);

        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }
}
