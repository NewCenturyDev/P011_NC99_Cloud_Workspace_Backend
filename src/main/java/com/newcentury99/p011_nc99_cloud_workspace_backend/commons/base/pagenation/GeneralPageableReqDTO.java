package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.pagenation;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class GeneralPageableReqDTO {
    @PositiveOrZero(message = "valid.page.positive")
    private Integer pageIdx;

    @PositiveOrZero(message = "valid.page.positive")
    private Integer pageLimit;
}
