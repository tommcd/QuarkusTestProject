package io.chillplus;

import io.chillplus.model.Greeting;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/")
public class GreetingEndpoint {

    private static final String template = "Hello, %s!";

    @GET
    @Path("/greeting")
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting greeting(@QueryParam("name") String name) {
        System.out.println(new SimpleDateFormat("HH:mm:ss.SSS").format(new java.util.Date(System.currentTimeMillis())));
        String suffix = name != null ? name : "World";
        return new Greeting(String.format(template, suffix));
    }

    void onStart(@Observes StartupEvent startup) {
        System.out.println(new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));
    }
}

