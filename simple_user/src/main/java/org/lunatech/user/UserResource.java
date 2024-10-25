package org.lunatech.user;

import org.jboss.logging.Logger;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/api/users")
public class UserResource {

    @Inject
    Logger logger;

    @GET
    @Path("/privilege")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean hello(@QueryParam("userId") String userId,
            @QueryParam("operation") String operation) {
        // make it simple here
        logger.info("received: userId: " + userId + " operation: " + operation);
        return true;
    }
}
