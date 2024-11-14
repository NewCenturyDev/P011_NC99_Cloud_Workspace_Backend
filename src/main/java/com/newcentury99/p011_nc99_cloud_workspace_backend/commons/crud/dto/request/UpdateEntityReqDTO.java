package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.UUID;

@Data
public class UpdateEntityReqDTO {
    @Size(max = 100, message = "valid.entity.id.size")
    @NotEmpty(message = "valid.entity.id.empty")
    @UniqueElements(message = "valid.entity.id.unique")
    protected UUID eid;
}
