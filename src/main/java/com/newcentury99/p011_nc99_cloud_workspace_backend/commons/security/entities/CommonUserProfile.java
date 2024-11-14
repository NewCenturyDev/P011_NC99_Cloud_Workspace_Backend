package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.entities;

import org.springframework.security.core.Authentication;

public interface CommonUserProfile<T extends CommonUserProfile<?>> {
    T fromAuthentication(Authentication authentication);
    Boolean hasPrivilege(CommonUserACL required);
    Boolean hasPrivilege(String role);
    void checkPrivilege(CommonUserACL required);
    void checkPrivilege(String role);
    String getCurrentServicePrivilege();
}
