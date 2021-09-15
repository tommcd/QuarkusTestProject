package io.chillplus;

import io.chillplus.model.TvShow;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/tv")
public class TvShowResource {

    //List<TvShow> tvShows = new ArrayList<>();
    private Set<TvShow> tvShows = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    private final AtomicLong counter = new AtomicLong();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid TvShow tvShow) {
        //System.out.println(tvShow);
        if (tvShow.getId() != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("ID must be autogenerated").build();
        }

        tvShow.setId(counter.incrementAndGet());
        tvShows.add(tvShow);
        return Response.status(Response.Status.CREATED).entity(tvShow).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<TvShow> getAll() {
        return tvShows;
    }


    @GET
    @Path("/{id}")
    public TvShow getOneById(@PathParam("id") long id) {
        final Optional<TvShow> entity = tvShows.stream()
                .peek(System.out::println)
                .filter(s -> s.getId().equals(id)).findFirst();
        if (entity.isEmpty() ) {
            throw new WebApplicationException("Entity does not exist. ", Response.Status.NOT_FOUND);
        }
        return entity.get();
    }

    @DELETE
    public Response deleteAll() {
        tvShows.clear();
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOne(@PathParam("id") long id) {
        tvShows.removeIf(existingTvShow -> existingTvShow.getId().equals(id));
        return Response.ok().build();
    }

}
