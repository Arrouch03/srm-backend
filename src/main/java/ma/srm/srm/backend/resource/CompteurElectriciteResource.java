package ma.srm.srm.backend.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import ma.srm.srm.backend.dao.CompteurElectriciteDAO;
import ma.srm.srm.backend.model.CompteurElectricite;

@Path("/compteurs-electricite")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompteurElectriciteResource {

    @Inject
    private CompteurElectriciteDAO dao;

    @GET
    public List<CompteurElectricite> getAll() {
        return dao.findAll();
    }

    @GET
    @Path("/{id}")
    public CompteurElectricite getById(@PathParam("id") Long id) {
        return dao.findById(id);
    }

    @POST
    public void create(CompteurElectricite compteur) {
        dao.save(compteur);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, CompteurElectricite compteur) {
        compteur.setId(id);
        dao.update(compteur);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        dao.delete(id);
    }
    
@PUT
@Path("/{id}/position")
@Consumes(MediaType.APPLICATION_JSON)
public void updatePosition(@PathParam("id") Long id, CompteurElectricite data) {
    CompteurElectricite compteur = dao.findById(id);
    if (compteur != null) {
        compteur.setLatitude(data.getLatitude());
        compteur.setLongitude(data.getLongitude());
        dao.update(compteur);
    }
}


}
