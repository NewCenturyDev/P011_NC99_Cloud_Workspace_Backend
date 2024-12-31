package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.databases.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryClass;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils.ObjectMapperUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@LibraryClass
@Converter
public class JsonArrayColumnConverter<T> implements AttributeConverter<List<T>, String> {
    protected final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    @Override
    public String convertToDatabaseColumn(List<T> objectList) {
        if (objectList != null && !objectList.isEmpty()) {
            try {
                return objectMapper.writeValueAsString(objectList);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return "[]";
        }
    }

    @Override
    public List<T> convertToEntityAttribute(String jsonColumnStr) {
        try {
            return objectMapper.readValue(jsonColumnStr, new TypeReference<>() {});
        } catch (Exception e) {
            return null;
        }
    }
}
