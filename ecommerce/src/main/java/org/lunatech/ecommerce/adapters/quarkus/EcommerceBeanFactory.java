package org.lunatech.ecommerce.adapters.quarkus;

import org.lunatech.ecommerce.EcommerceService;
import org.lunatech.ecommerce.ports.EcommercePersistencePort;
import org.lunatech.ecommerce.ports.StreamOutputPort;
import org.lunatech.ecommerce.ports.UserServicePort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 * EcommerceBeanFactory
 */
@ApplicationScoped
public class EcommerceBeanFactory {

    @Inject
    EcommercePersistencePort storage;

    @Inject
    StreamOutputPort kafka;

    @Inject
    UserServicePort userService;

    @Produces
    public EcommerceService getEcommerceService() {
        return new EcommerceService(storage, kafka, userService);
    }
}
