package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Locale;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonUserProfile {
    private Long id;
    protected String email;
    private Locale language;
    protected String username;
    protected Locale country;
}
