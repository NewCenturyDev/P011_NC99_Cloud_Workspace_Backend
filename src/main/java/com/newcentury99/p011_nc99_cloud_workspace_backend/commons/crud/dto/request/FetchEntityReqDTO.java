package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.crud.dto.request;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.pagenation.GeneralPageableReqDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class FetchEntityReqDTO extends GeneralPageableReqDTO {
    @Size(max = 100, message = "valid.entity.id.size")
    @NotEmpty(message = "valid.entity.id.empty")
    @UniqueElements(message = "valid.entity.id.unique")
    protected List<UUID> eids;
}
