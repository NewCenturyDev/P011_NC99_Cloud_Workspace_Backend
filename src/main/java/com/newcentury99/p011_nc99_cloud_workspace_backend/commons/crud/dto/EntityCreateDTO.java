package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class EntityCreateDTO {
    public interface Request { }
    public static abstract class Response extends GeneralResDTO { }

    @Data
    public static abstract class SingleRequest<T> implements Request {
        public abstract T toEntity();
    }

    @Data
    public static abstract class BulkRequest<T> implements Request {
        public abstract List<T> toEntities();
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SingleResponse<T> extends Response {
        private final T created;
        public SingleResponse(T entity) {
            this.created = entity;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class BulkResponse<T> extends Response {
        private final List<T> created;
        public BulkResponse(List<T> created) {
            this.created = created;
        }
    }
}