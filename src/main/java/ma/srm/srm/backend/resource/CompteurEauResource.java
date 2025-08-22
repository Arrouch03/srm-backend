package ma.srm.srm.backend.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import ma.srm.srm.backend.dao.CompteurEauDAO;
import ma.srm.srm.backend.model.CompteurEau;

@Path("/compteurs-eau")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompteurEauResource {

    @Inject
    private CompteurEauDAO dao;

    @GET
    public List<CompteurEau> getAll() {
        return dao.findAll();
    }

    @GET
    @Path("/{id}")
    public CompteurEau getById(@PathParam("id") Long id) {
        return dao.findById(id);
    }

    @POST
    public void create(CompteurEau compteur) {
        dao.save(compteur);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, CompteurEau compteur) {
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
public void updatePosition(@PathParam("id") Long id, CompteurEau data) {
    CompteurEau compteur = dao.findById(id);
    if (compteur != null) {
        compteur.setLatitude(data.getLatitude());
        compteur.setLongitude(data.getLongitude());
        dao.update(compteur);
    }
}


}
