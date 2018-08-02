package com.wedonegood.roles.api;

import com.wedonegood.roles.api.model.entity.Role;

public interface RoleSecurityService {
//    Client getCurrentClient();
    String getCurrentUser();
//    boolean hasACccess(DocumentSet documentSet);
    boolean hasAccess(Role role);
}
