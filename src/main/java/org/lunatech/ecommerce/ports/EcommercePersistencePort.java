package org.lunatech.ecommerce.ports;

import org.lunatech.ecommerce.Order;
import org.lunatech.ecommerce.Product;

/**
 * EcommercePersistencePort
 */
public interface EcommercePersistencePort {

    public void saveProduct(Product product);

    public Product getProduct(String productId);

    public void saveOrder(Order order);

    public Order getOrder(String orderId);

    // some other DB operations...
}
