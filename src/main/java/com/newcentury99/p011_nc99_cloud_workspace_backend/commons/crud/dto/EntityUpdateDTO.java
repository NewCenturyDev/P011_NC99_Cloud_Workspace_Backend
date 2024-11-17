package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class EntityUpdateDTO {
    public interface Request { }
    public static abstract class Response extends GeneralResDTO { }

    @Data
    public static abstract class SingleRequest<T, ID> implements Request {
        @NotNull(message = "valid.id.null")
        private ID id;
        private List<String> resetColumn;
        public abstract T toEntity(T target);
    }

    @Data
    public static abstract class BulkRequest<T, ID> implements Request {
        @Valid
        @Size(min = 1, max = 100, message = "valid.id.size")
        @NotEmpty(message = "valid.id.empty")
        @UniqueElements(message = "valid.id.unique")
        private List<@NotNull(message = "valid.id.null") ID> ids;
        public abstract List<T> toEntity(List<T> targets);
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class SingleResponse<T> extends Response {
        private T updated;
        public SingleResponse(T entity) {
            this.updated = entity;
        }
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class BulkResponse<T> extends Response {
        private List<T> updated;
        public BulkResponse(List<T> entity) {
            this.updated = entity;
        }
    }
}
