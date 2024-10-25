package org.lunatech.ecommerce.ports;

import org.lunatech.ecommerce.Product;

/**
 * NotificationServicePort
 */
public interface NotificationServicePort {

    public void notifyNewProduct(Product product);

    public void notifyDiscount(Product product);
}
