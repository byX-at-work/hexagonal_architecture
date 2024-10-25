package org.lunatech.ecommerce.adapters.quarkus;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.lunatech.ecommerce.ports.PaymentServicePort;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

/**
 * PaymentServiceProxy
 */
@Path("/api/payment")
@RegisterRestClient(configKey = "payment")
public interface PaymentServiceProxy extends PaymentServicePort {

    @POST
    @Path("/newpaymentlink")
    public String requestPaymentLink(String orderId, double price);

}
