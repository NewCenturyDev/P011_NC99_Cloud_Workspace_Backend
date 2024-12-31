package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.domain.Page;

import java.util.List;

public class EntityFetchDTO {
    public interface Request { }
    public interface SingleRequest extends Request { }
    public interface BulkRequest extends Request { }
    public static abstract class Response extends GeneralResDTO { }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdRequest implements SingleRequest {
        @NotNull(message = "valid.id.null")
        protected String id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdsRequest implements BulkRequest {
        @Valid
        @Size(min = 1, max = 100, message = "valid.id.size")
        @NotEmpty(message = "valid.id.empty")
        @UniqueElements(message = "valid.id.unique")
        protected List<@NotNull(message = "valid.id.null") String> ids;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NameSearchRequest extends PageRequest {
        @NotEmpty(message = "valid.name.empty")
        @Size(min = 1, max = 250, message = "valid.name.size")
        protected String name;
    }

    @Data
    public static class PageRequest implements Request {
        @PositiveOrZero(message = "valid.page.positive")
        protected Integer pageIdx;

        @PositiveOrZero(message = "valid.page.positive")
        protected Integer pageSize;
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class SingleResponse<T> extends Response {
        private T result;
        public SingleResponse(T entity) {
            this.result = entity;
        }
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class BulkResponse<T> extends Response {
        private List<T> results;
        public BulkResponse(List<T> entities) {
            this.results = entities;
        }
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class PageResponse<T> extends Response {
        protected Page<T> results;
        protected Integer pageIdx;
        protected Integer totalPages;
        protected Integer pageSize;
        protected Integer elementCnt;
        protected Long elementTotalCnt;
        public PageResponse(Page<T> entityPages) {
            this.results = entityPages;
            this.pageIdx = entityPages.getNumber();
            this.totalPages = entityPages.getTotalPages();
            this.pageSize = entityPages.getSize();
            this.elementCnt = entityPages.getNumberOfElements();
            this.elementTotalCnt = entityPages.getTotalElements();
        }
    }
}
