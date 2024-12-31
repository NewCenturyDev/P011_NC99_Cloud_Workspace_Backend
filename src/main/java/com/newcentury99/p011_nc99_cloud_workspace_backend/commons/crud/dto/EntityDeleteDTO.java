package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class EntityDeleteDTO {
    public interface Request { }
    public static abstract class Response extends GeneralResDTO { }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleRequest implements Request {
        @NotNull(message = "valid.id.null")
        protected String id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BulkRequest implements Request {
        @Valid
        @Size(min = 1, max = 100, message = "valid.id.size")
        @NotEmpty(message = "valid.id.empty")
        @UniqueElements(message = "valid.id.unique")
        protected List<@NotNull(message = "valid.id.null") String> ids;
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class SingleResponse extends Response {
        private String deletedId;
        public SingleResponse(String entityId) {
            this.deletedId = entityId;
        }
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class BulkResponse extends Response {
        private List<String> deletedIds;
        public BulkResponse(List<String> deletedIds) {
            this.deletedIds = deletedIds;
        }
    }
}
