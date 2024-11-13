package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.pagenation;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NonNull
@EqualsAndHashCode(callSuper = true)
public class GeneralPageableResDTO extends GeneralResDTO {
    boolean isPageable = false;
    Integer pageIdx = null;
    Integer totalPage = null;
    Long pageElementSize = null;
}
