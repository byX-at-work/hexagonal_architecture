package org.lunatech.ecommerce.adapters.quarkus;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.lunatech.ecommerce.ports.PaymentServicePort;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * PaymentServiceQuarkusAdapter
 */
@ApplicationScoped
public class PaymentServiceQuarkusAdapter implements PaymentServicePort {

    @RestClient
    PaymentServiceProxy proxy;

    public String requestPaymentLink(String orderId, double price) {
        return proxy.requestPaymentLink(orderId, price);
    }
}
