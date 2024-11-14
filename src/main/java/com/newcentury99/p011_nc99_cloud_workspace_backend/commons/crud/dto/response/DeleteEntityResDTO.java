package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.response;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteEntityResDTO extends GeneralResDTO {
    protected List<UUID> deletedIds;
}
