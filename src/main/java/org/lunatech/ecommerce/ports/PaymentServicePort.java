package org.lunatech.ecommerce.ports;

/**
 * PaymentServicePort
 */
public interface PaymentServicePort {

    public String requestPaymentLink(String orderId, double price);
}
