package ma.srm.srm.backend.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import ma.srm.srm.backend.dao.CompteurTypeDAO;
import ma.srm.srm.backend.model.CompteurType;

@Path("/compteur-types")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompteurTypeResource {

    @Inject
    private CompteurTypeDAO compteurTypeDAO;

    @GET
    public List<CompteurType> getAll() {
        return compteurTypeDAO.findAll();
    }

    @GET
    @Path("/{id}")
    public CompteurType getById(@PathParam("id") Long id) {
        return compteurTypeDAO.findById(id);
    }

    @POST
    public void create(CompteurType type) {
        compteurTypeDAO.save(type);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, CompteurType type) {
        type.setId(id);
        compteurTypeDAO.update(type);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        compteurTypeDAO.delete(id);
    }
}
