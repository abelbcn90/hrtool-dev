package com.wedonegood.roles.rest.common;

import java.security.Principal;

import com.wedonegood.roles.api.model.entity.Client;

public abstract class RestController {

    protected Client getClient(Principal principal) {
        Client client = new Client();
        client.setId(-13L);
        return client;
    }
}
