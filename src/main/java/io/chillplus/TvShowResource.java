package io.chillplus;

import io.chillplus.model.TvShow;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/tv")
public class TvShowResource {

    List<TvShow> tvShows = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TvShow> getAll() {
        //return new ArrayList<TvShow>(Arrays.asList(new TvShow()));
        return tvShows;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TvShow create(TvShow tvShow) {
        tvShow.setId(counter.incrementAndGet());
        tvShows.add(tvShow);
        return tvShow;
    }
}
