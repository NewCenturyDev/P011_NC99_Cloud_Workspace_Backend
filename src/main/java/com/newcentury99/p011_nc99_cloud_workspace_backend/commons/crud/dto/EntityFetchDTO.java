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
    public static class IDRequest<ID> implements SingleRequest {
        @NotNull(message = "valid.id.null")
        private ID id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IDsRequest<ID> implements BulkRequest {
        @Valid
        @Size(min = 1, max = 100, message = "valid.id.size")
        @NotEmpty(message = "valid.id.empty")
        @UniqueElements(message = "valid.id.unique")
        private List<@NotNull(message = "valid.id.null") ID> ids;
    }

    @Data
    public static class PageRequest implements Request {
        @PositiveOrZero(message = "valid.page.positive")
        private Integer pageIdx;

        @PositiveOrZero(message = "valid.page.positive")
        private Integer pageSize;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SingleResponse<T> extends Response {
        private final T result;
        public SingleResponse(T entity) {
            this.result = entity;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class BulkResponse<T> extends Response {
        private final List<T> results;
        public BulkResponse(List<T> entities) {
            this.results = entities;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class PageResponse<T> extends Response {
        private final Page<T> results;
        private Integer pageIdx;
        private Integer totalPages;
        private Integer pageSize;
        private Integer elementCnt;
        private Long elementTotalCnt;
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