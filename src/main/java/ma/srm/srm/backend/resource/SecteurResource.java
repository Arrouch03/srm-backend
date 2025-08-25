package ma.srm.srm.backend.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import ma.srm.srm.backend.dao.SecteurDAO;
import ma.srm.srm.backend.model.Secteur;

@Path("/secteurs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SecteurResource {

    @Inject
    private SecteurDAO dao;

    // -------------------------
    // Récupérer tous les secteurs
    // -------------------------
    @GET
    public List<Secteur> getAll() {
        return dao.findAll();
    }

    // -------------------------
    // Récupérer un secteur par ID
    // -------------------------
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Secteur s = dao.findById(id);
        if (s == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(s).build();
    }

    // -------------------------
    // Créer un secteur
    // -------------------------
    @POST
    public Response create(Secteur secteur) {
        Secteur saved = dao.save(secteur);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    // -------------------------
    // Mettre à jour un secteur
    // -------------------------
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Secteur secteur) {
        secteur.setId(id);
        dao.update(secteur);
        return Response.ok(secteur).build();
    }

    // -------------------------
    // Supprimer un secteur
    // -------------------------
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        dao.delete(id);
        return Response.noContent().build();
    }
}
