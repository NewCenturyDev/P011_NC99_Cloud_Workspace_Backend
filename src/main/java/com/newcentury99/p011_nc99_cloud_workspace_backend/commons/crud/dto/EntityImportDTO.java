package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class EntityImportDTO {
    public interface Request {
        // TODO: Implement CSV Import Request Base Class
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static abstract class Response extends GeneralResDTO {
        // TODO: Implement CSV Import Response Base Class
    }
}
