package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.databases.converters;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryClass;
import jakarta.persistence.Converter;

@LibraryClass
@Converter
public class StringArrayColumnConverter extends JsonArrayColumnConverter<String> {
}
