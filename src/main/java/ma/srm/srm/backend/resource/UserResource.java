package ma.srm.srm.backend.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import ma.srm.srm.backend.dao.UserDAO;
import ma.srm.srm.backend.model.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserDAO userDAO;

    @GET
    public List<User> getAll() {
        return userDAO.findAll();
    }
    
 @POST
@Path("/login")
public User login(User user) {
    System.out.println("Received: username=" + user.getUsername() + ", password=" + user.getPassword());
    User u = userDAO.findByUsername(user.getUsername());
    if (u == null) {
        System.out.println("User not found for username: " + user.getUsername());
        throw new WebApplicationException("Invalid credentials", 401);
    }
    System.out.println("Found: username=" + u.getUsername() + ", password=" + u.getPassword());
    if (u.getPassword().equals(user.getPassword())) {
        System.out.println("Login successful");
        return u;
    }
    System.out.println("Password mismatch");
    throw new WebApplicationException("Invalid credentials", 401);
}



    @GET
    @Path("/{id}")
    public User getById(@PathParam("id") Long id) {
        return userDAO.findById(id);
    }

    @POST
    public void create(User user) {
        userDAO.save(user);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, User user) {
        user.setId(id);
        userDAO.update(user);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        userDAO.delete(id);
    }
}
