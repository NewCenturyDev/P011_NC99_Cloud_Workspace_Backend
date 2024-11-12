package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DTOMetadata {
    private Boolean status;
    private String message;
    private String code;
    private String exception;

    public DTOMetadata(String message) {
        this.status = true;
        this.message = message;
        this.code = null;
        this.exception = null;
    }
    public DTOMetadata(String message, String exception) {
        this.status = false;
        this.message = message;
        this.exception = exception;
    }
}
