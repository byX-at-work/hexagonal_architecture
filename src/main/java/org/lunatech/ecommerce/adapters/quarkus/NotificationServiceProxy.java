package org.lunatech.ecommerce.adapters.quarkus;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.lunatech.ecommerce.Product;
import org.lunatech.ecommerce.ports.NotificationServicePort;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

/**
 * NotificationServiceProxy
 */
@Path("/api/notification")
@RegisterRestClient(configKey = "notification")
public interface NotificationServiceProxy extends NotificationServicePort {

    @POST
    @Path("/newproduct")
    public void notifyNewProduct(Product product);

    @POST
    @Path("/discount")
    public void notifyDiscount(Product product);

}
