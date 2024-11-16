package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.databases.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryClass;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils.ObjectMapperUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@LibraryClass
@Converter
public class JsonColumnConverter<T> implements AttributeConverter<T, String> {
    protected final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    @Override
    public String convertToDatabaseColumn(T object) {
        if (object != null) {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    @Override
    public T convertToEntityAttribute(String jsonColumnStr) {
        try {
            return objectMapper.readValue(jsonColumnStr, new TypeReference<>() {});
        } catch (Exception e) {
            return null;
        }
    }
}
