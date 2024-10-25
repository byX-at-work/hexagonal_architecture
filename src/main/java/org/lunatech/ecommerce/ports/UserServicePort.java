package org.lunatech.ecommerce.ports;

/**
 * UserServicePort
 */
public interface UserServicePort {

    public boolean checkUserPrivilege(String userId, String operation);
}
