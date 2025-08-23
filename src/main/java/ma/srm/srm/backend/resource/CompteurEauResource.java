package ma.srm.srm.backend.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

import ma.srm.srm.backend.dao.CompteurEauDAO;
import ma.srm.srm.backend.model.CompteurEau;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

@Path("/compteurs-eau")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompteurEauResource {

    @Inject
    private CompteurEauDAO dao;

    // -------------------------
    // Liste de tous les compteurs sans photo
    // -------------------------
    @GET
    public List<CompteurEau> getAll() {
        List<CompteurEau> list = dao.findAll();
        list.forEach(c -> c.setPhoto(null)); // Exclure la photo
        return list;
    }

    // -------------------------
    // Compteur par ID
    // -------------------------
    @GET
    @Path("/{id}")
    public CompteurEau getById(@PathParam("id") Long id) {
        return dao.findById(id); // Photo incluse
    }

    // -------------------------
    // Création d’un compteur
    // -------------------------
    @POST
    public Response create(CompteurEau compteur) {
        if (compteur.getSecteur() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le secteur est obligatoire").build();
        }
        CompteurEau saved = dao.save(compteur);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    // -------------------------
    // Mise à jour
    // -------------------------
    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, CompteurEau compteur) {
        compteur.setId(id);
        dao.update(compteur);
    }

    // -------------------------
    // Suppression
    // -------------------------
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        dao.delete(id);
    }

    // -------------------------
    // Mise à jour de la position
    // -------------------------
    @PUT
    @Path("/{id}/position")
    public void updatePosition(@PathParam("id") Long id, CompteurEau data) {
        dao.updatePosition(id, data.getLatitude(), data.getLongitude());
    }

    // -------------------------
    // Upload de photo
    // -------------------------
    @POST
    @Path("/{id}/photo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPhoto(
            @PathParam("id") Long id,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {

        CompteurEau compteur = dao.findById(id);
        if (compteur == null) return Response.status(Response.Status.NOT_FOUND).build();

        try {
            byte[] photo = uploadedInputStream.readAllBytes();
            dao.updatePhoto(id, photo);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de l'upload de la photo").build();
        }
    }

    // -------------------------
    // Mise à jour du statut
    // -------------------------
    @PUT
    @Path("/{id}/statut")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateStatut(@PathParam("id") Long id, String statut) {
        CompteurEau existing = dao.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existing.setStatut(statut);
        dao.update(existing);
        return Response.ok().build();
    }

    // -------------------------
    // Filtrage par secteur
    // -------------------------
    @GET
    @Path("/secteur/{secteurId}")
    public List<CompteurEau> getBySecteur(@PathParam("secteurId") Long secteurId) {
        List<CompteurEau> list = dao.findBySecteur(secteurId);
        list.forEach(c -> c.setPhoto(null));
        return list;
    }

    // -------------------------
    // Filtrage par statut
    // -------------------------
    @GET
    @Path("/statut/{statut}")
    public List<CompteurEau> getByStatut(@PathParam("statut") String statut) {
        List<CompteurEau> list = dao.findByStatut(statut);
        list.forEach(c -> c.setPhoto(null));
        return list;
    }

    // -------------------------
    // Filtrage par secteur et statut
    // -------------------------
    @GET
    @Path("/secteur/{secteurId}/statut/{statut}")
    public List<CompteurEau> getBySecteurAndStatut(@PathParam("secteurId") Long secteurId,
                                                   @PathParam("statut") String statut) {
        List<CompteurEau> list = dao.findBySecteurAndStatut(secteurId, statut);
        list.forEach(c -> c.setPhoto(null));
        return list;
    }
}
