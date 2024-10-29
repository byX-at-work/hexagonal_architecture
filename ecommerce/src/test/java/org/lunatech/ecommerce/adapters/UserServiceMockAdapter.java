package org.lunatech.ecommerce.adapters;

import org.lunatech.ecommerce.ports.UserServicePort;

/**
 * UserServiceMockAdapter
 */
public class UserServiceMockAdapter implements UserServicePort {

    private boolean assignedValue;

    public UserServiceMockAdapter(boolean assignedValue) {
        this.assignedValue = assignedValue;
    }

    public boolean checkUserPrivilege(String userId, String operation) {
        return assignedValue;
    }
}
