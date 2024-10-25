package org.lunatech.ecommerce.ports;

import org.lunatech.ecommerce.Order;
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

    /**
     * @return payment link url
     */
    public String placeOrder(Order order);

    public Order getOrder(String orderId);

    public void onOrderPaid(String orderId);

    public void onOrderDelivered(String orderId);

    public void cancelOrder(String orderId);
}
