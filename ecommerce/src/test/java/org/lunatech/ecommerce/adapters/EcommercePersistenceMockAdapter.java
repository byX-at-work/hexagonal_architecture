package org.lunatech.ecommerce.adapters;

import org.lunatech.ecommerce.Product;
import org.lunatech.ecommerce.ports.EcommercePersistencePort;

/**
 * EcommercePersistenceMockAdapter
 */
public class EcommercePersistenceMockAdapter implements EcommercePersistencePort {

    private String assignedProductId;
    private Product assignedProduct;

    public EcommercePersistenceMockAdapter(String assignedProductId, Product assignedProduct) {
        this.assignedProductId = assignedProductId;
        this.assignedProduct = assignedProduct;
    }

    public void saveProduct(Product product) {
        product.setId(this.assignedProductId);
    }

    public Product getProduct(String productId) {
        return assignedProduct;
    }
}
