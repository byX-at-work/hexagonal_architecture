package org.lunatech.ecommerce.ports;

import org.lunatech.ecommerce.Product;

/**
 * EcommercePersistencePort
 */
public interface EcommercePersistencePort {

    public void saveProduct(Product product);

    public Product getProduct(String productId);

    // some other DB operations...
}
