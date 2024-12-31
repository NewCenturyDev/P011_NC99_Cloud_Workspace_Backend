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
    public static abstract class SingleRequest<T> implements Request {
        @NotNull(message = "valid.id.null")
        protected String id;
        @Size(min = 1, max = 250, message = "valid.name.size")
        protected String name;
        protected List<String> resetColumn;
        public abstract T toEntity(T target);
    }

    @Data
    public static abstract class BulkRequest<T> implements Request {
        @Valid
        @Size(min = 1, max = 100, message = "valid.id.size")
        @NotEmpty(message = "valid.id.empty")
        @UniqueElements(message = "valid.id.unique")
        protected List<@NotNull(message = "valid.id.null") String> ids;
        public abstract List<T> toEntities(List<T> targets);
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class SingleResponse<T> extends Response {
        protected T updated;
        public SingleResponse(T entity) {
            this.updated = entity;
        }
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class BulkResponse<T> extends Response {
        protected List<T> updated;
        public BulkResponse(List<T> entity) {
            this.updated = entity;
        }
    }
}
