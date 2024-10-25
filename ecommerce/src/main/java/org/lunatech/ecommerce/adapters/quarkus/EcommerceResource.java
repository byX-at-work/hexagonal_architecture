package org.lunatech.ecommerce.adapters;

import org.jboss.resteasy.reactive.RestPath;
import org.lunatech.ecommerce.EcommerceService;
import org.lunatech.ecommerce.Product;
import org.lunatech.ecommerce.ports.EcommerceInputPort;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

/**
 * EcommerceResource
 */

@Path("/api/ecommerce")
public class EcommerceResource implements EcommerceInputPort {

    @Inject
    EcommerceService service;

    @POST
    @Path("/product")
    public String addProduct(Product product) {
        String userId = "someId"; // should be retrieved from a session or something
        return service.onAddProduct(userId, product);
    }

    @PUT
    @Path("/product/rename/{id}")
    public void renameProduct(@RestPath("id") String productId, String name) {
        String userId = "someId"; // should be retrieved from a session or something
        service.onRenameProduct(userId, productId, name);
    }

    @PUT
    @Path("/product/discount/{id}")
    public void setProductDiscout(@RestPath("id") String productId, double discout) {
        String userId = "someId"; // should be retrieved from a session or something
        service.onSetProductDiscout(userId, productId, discout);
    }

    @GET
    @Path("/product/{id}")
    public Product getProduct(@RestPath("id") String productId) {
        String userId = "someId"; // should be retrieved from a session or something
        return service.onGetProduct(userId, productId);
    }
}
