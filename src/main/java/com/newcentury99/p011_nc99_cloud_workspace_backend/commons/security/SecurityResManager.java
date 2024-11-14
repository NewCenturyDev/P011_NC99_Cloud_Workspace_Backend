package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.DTOMetadata;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils.ObjectMapperUtil;
import jakarta.servlet.http.HttpServletResponse;

// Spring Security Exception Response Customization
public class SecurityResManager {
    private static final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    public void setResponseHeader(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    public String makeResponseBody(Exception e) {
        GeneralResDTO rawResponseDTO = new GeneralResDTO();
        rawResponseDTO.set_metadata(new DTOMetadata(e.getMessage(), e.getLocalizedMessage()));
        try {
            return objectMapper.writeValueAsString(rawResponseDTO);
        } catch (JsonProcessingException ex) {
            return "{\"_metadata\": {\n\"status\": false,\n\"message\": \"ERROR_GENERAL_UNKNOWN_FATAL\",\n\"code\": \"000\",\n\"exception\": \"com.fasterxml.jackson.core.JsonProcessingException\"\n}\n}";
        }
    }
}
