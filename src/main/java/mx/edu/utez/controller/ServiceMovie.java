package mx.edu.utez.controller;

import mx.edu.utez.model.movie.DaoMovie;
import mx.edu.utez.model.movie.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.time.LocalDateTime;
import java.util.List;

@Path("/movie")
public class ServiceMovie {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovie(){
        return new DaoMovie().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getMovieById(@PathParam("id") int id){
        return new DaoMovie().findMovieById(id);
    }
    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Movie updateMovie(MultivaluedMap<String, String> formParams, @PathParam("id") int id){
        if(new DaoMovie().movies(false,getParams(id,formParams),id)){
            return new DaoMovie().findMovieById(id);
        }
        return null;
    }
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public String createMovie(MultivaluedMap<String, String> formParams){
        if(new DaoMovie().movies(true,getParams(0,formParams),0)){
            return "Succesful";
        }
        return null;
    }
    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> deleteMovie(@PathParam("id") int id){
        if(new DaoMovie().deleteMovie(id)){
            return new DaoMovie().findAll();
        }
        return null;
    }
    private Movie getParams(int id, MultivaluedMap<String, String> formParams){
        int rating = Integer.parseInt(formParams.get("rating").get(0));
        int state = Integer.parseInt(formParams.get("state").get(0));
        int category = Integer.parseInt(formParams.get("category").get(0));
        String registeredDate = String.valueOf(LocalDateTime.now());
        String updatedDate = String.valueOf(LocalDateTime.now());
        Movie movie = new Movie(id,formParams.get("title").get(0),formParams.get("description").get(0),formParams.get("synopsis").get(0),rating,registeredDate,updatedDate,state,category);
        return movie;
    }
}

