package org.lunatech.ecommerce.adapters.quarkus;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.lunatech.ecommerce.ports.UserServicePort;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * UserServiceQuarkusAdapter
 */
@ApplicationScoped
public class UserServiceQuarkusAdapter implements UserServicePort {

    @RestClient
    UserServiceProxy proxy;

    public boolean checkUserPrivilege(String userId, String operation) {
        return proxy.checkUserPrivilege(userId, operation);
    }

}
