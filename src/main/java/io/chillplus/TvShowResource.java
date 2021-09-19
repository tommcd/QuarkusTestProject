package io.chillplus;

import io.chillplus.model.TvShow;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/tv")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TvShowResource {

    @POST
    @Transactional
    public Response create(@Valid TvShow tvShow) {
        if (tvShow.id != null) {
            throw new WebApplicationException("A new entity cannot already have an ID", Response.Status.BAD_REQUEST);
        }
        tvShow.persist();
        //return Response.created(URI.create("/persons/" + person.id)).build();
        return Response.status(Response.Status.CREATED).entity(tvShow).build();
    }

    @PUT
    @Transactional
    public Response update(@Valid TvShow tvShow) {
        if (tvShow.id == null) {
            throw new WebApplicationException("Invalid ID", Response.Status.BAD_REQUEST);
        }
        TvShow updated = TvShow.findById(tvShow.id);
        updated.id = tvShow.id;
        updated.title = tvShow.title;
        updated.category= tvShow.category;
        return Response.ok(updated).build();
    }

    @GET
    public List<TvShow> getAll() {
        return TvShow.listAll();
    }

    @GET
    @Path("/{id}")
    public TvShow getOneById(@PathParam("id") long id) {
        TvShow entity = TvShow.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Entity does not exist. ", Response.Status.NOT_FOUND);
        }
        return entity;
    }

    @DELETE
    @Transactional
    public Response deleteAll() {
        TvShow.deleteAll();
        return Response.ok().build();
    }

/*    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        TvShow entity = TvShow.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }*/

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteOne(@PathParam("id") long id) {
        TvShow.deleteById(id);
        return Response.ok().build();
    }
}
