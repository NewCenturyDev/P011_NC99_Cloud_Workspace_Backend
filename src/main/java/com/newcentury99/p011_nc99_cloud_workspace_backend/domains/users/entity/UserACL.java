package com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserACL implements GrantedAuthority {
    private String code;
    @Value("${msa.tenancy}")
    private String tenancy;
    @Value("${msa.group}")
    private String group;
    @Value("${msa.service}")
    private String service;
    @Value("${msa.defaultRole}")
    private String role;
    @Value("${msa.description}")
    private String description;

    public UserACL(String code) {
        String[] args = code.split("_");
        this.code = code;
        this.tenancy = args[0];
        this.group = args[1];
        this.service = args[2];
        this.role = args[3];
    }

    @Override
    public String getAuthority() {
        return this.code;
    }

    public static UserACL of(String tenancy, String group, String service, String role) {
        String code = tenancy + "_" + group + "_" + service + "_" + role;
        return new UserACL(code, tenancy, group, service, role, null);
    }

    public static UserACL of(String group, String service, String role) {
        UserACL acl = new UserACL();
        acl.setGroup(group);
        acl.setService(service);
        acl.setRole(role);
        acl.setCode(acl.getTenancy() + "_" + group + "_" + service + "_" + role);
        return acl;
    }

    public static UserACL of(String service, String role) {
        UserACL acl = new UserACL();
        acl.setService(service);
        acl.setRole(role);
        acl.setCode(acl.getTenancy() + "_" + acl.getGroup() + "_" + service + "_" + role);
        return acl;
    }

    public static UserACL of(String role) {
        UserACL acl = new UserACL();
        acl.setRole(role);
        acl.setCode(acl.getTenancy() + "_" + acl.getGroup() + "_" + acl.getService() + "_" + role);
        return acl;
    }
}
