package org.lunatech.ecommerce.adapters.quarkus;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.lunatech.ecommerce.Product;

import org.lunatech.ecommerce.ports.NotificationServicePort;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * NotificationServiceQuarkusAdapter
 */
@ApplicationScoped
public class NotificationServiceQuarkusAdapter implements NotificationServicePort {

    @RestClient
    NotificationServiceProxy proxy;

    public void notifyNewProduct(Product product) {
        proxy.notifyNewProduct(product);
    }

    public void notifyDiscount(Product product) {
        proxy.notifyDiscount(product);
    }
}
