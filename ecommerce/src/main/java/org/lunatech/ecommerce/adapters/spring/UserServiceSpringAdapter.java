package org.lunatech.ecommerce.adapters.spring;

import org.lunatech.ecommerce.ports.UserServicePort;
import org.springframework.stereotype.Component;

/**
 * UserServiceSpringAdapter
 */
@Component
public class UserServiceSpringAdapter implements UserServicePort {

    @Override
    public boolean checkUserPrivilege(String userId, String operation) {
        // let's keep it simple here..
        return true;
    }
}
