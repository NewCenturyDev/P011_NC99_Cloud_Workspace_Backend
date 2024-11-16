package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class EntityDeleteDTO {
    public interface Request { }
    public static abstract class Response extends GeneralResDTO { }

    @Data
    public static class SingleRequest<ID> implements Request {
        @NotNull(message = "valid.id.null")
        private final ID id;
    }

    @Data
    public static class BulkRequest<ID> implements Request {
        @Valid
        @Size(min = 1, max = 100, message = "valid.id.size")
        @NotEmpty(message = "valid.id.empty")
        @UniqueElements(message = "valid.id.unique")
        private List<@NotNull(message = "valid.id.null") ID> ids;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SingleResponse<ID> extends Response {
        private final ID deletedID;
        public SingleResponse(ID entityID) {
            this.deletedID = entityID;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class BulkResponse<ID> extends Response {
        private final List<ID> deletedIDs;
        public BulkResponse(List<ID> deletedIDs) {
            this.deletedIDs = deletedIDs;
        }
    }
}
