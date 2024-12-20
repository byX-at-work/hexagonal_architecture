package org.lunatech.ecommerce.adapters.quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * UserServiceProxy
 */
@Path("/api/users")
@RegisterRestClient(configKey = "user")
public interface UserServiceProxy {

    @GET
    @Path("/privilege")
    public boolean checkUserPrivilege(@QueryParam("userId") String userId,
            @QueryParam("operation") String operation);
}
