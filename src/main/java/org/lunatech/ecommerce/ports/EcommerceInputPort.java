package org.lunatech.ecommerce.ports;

import org.lunatech.ecommerce.Product;

/**
 * EcommerceInputPort
 */
public interface EcommerceInputPort {

    public Product getProduct(String productId);

    /**
     * @return ID of the product in string
     */
    public String addProduct(Product product);

    public void setProductDiscout(String productId, double discout);

    public void renameProduct(String productId, String name);
}
