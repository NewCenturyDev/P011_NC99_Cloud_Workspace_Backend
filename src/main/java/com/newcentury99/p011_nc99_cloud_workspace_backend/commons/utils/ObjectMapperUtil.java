package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryAPI;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryClass;

@LibraryClass
public abstract class ObjectMapperUtil {
    private static ObjectMapper objectMapper;

    @LibraryAPI
    public static ObjectMapper getInstance() {
        if (objectMapper == null) {
            objectMapper = JsonMapper.builder()
                    .addModule(new JavaTimeModule())
                    .configure(DeserializationFeature.USE_LONG_FOR_INTS, true)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .build();
        }
        return objectMapper;
    }

    @LibraryAPI
    public static String encodeToJSONString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = ObjectMapperUtil.getInstance();
        return mapper.writeValueAsString(object);
    }

    @LibraryAPI
    public static  <T> T parseFromJSONString(String jsonStr, Class<T> cls) throws JsonProcessingException {
        ObjectMapper mapper = ObjectMapperUtil.getInstance();
        return mapper.readValue(jsonStr, cls);
    }
}
