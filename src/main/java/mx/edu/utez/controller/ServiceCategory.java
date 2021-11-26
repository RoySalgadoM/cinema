package mx.edu.utez.controller;

import mx.edu.utez.model.category.Category;
import mx.edu.utez.model.category.DaoCategory;
import mx.edu.utez.model.movie.DaoMovie;
import mx.edu.utez.model.movie.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.time.LocalDateTime;
import java.util.List;

@Path("/category")
public class ServiceCategory {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategory(){
        return new DaoCategory().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategoryById(@PathParam("id") int id){
        return new DaoCategory().findCategoryById(id);
    }
    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Category updateCategory(MultivaluedMap<String, String> formParams, @PathParam("id") int id){
        if(new DaoCategory().categories(false,getParams(id,formParams),id)){
            return new DaoCategory().findCategoryById(id);
        }
        return null;
    }
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public String createCategory(MultivaluedMap<String, String> formParams){
        if(new DaoCategory().categories(true,getParams(0,formParams),0)){
            return "Succesful";
        }
        return null;
    }
    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> deleteCategory(@PathParam("id") int id){
        if(new DaoCategory().deleteCategory(id)){
            return new DaoCategory().findAll();
        }
        return null;
    }
    private Category getParams(int id, MultivaluedMap<String, String> formParams){
        Category category = new Category(id,formParams.get("name").get(0));
        return category;
    }
}
