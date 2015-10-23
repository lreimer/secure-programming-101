package de.qaware.campus.secpro.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Our simple Hello resources.
 */
@Path("/hello")
@RequestScoped
public class HelloResource {

    @Inject
    private Greeting greeter;

    @GET
    @Produces("text/plain")
    public String sayHello(@QueryParam("name") @Size(min = 3) String name) {
        return greeter.getMessage(name);
    }
}