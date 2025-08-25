package ma.srm.srm.backend.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ma.srm.srm.backend.dao.CompteurEauDAO;
import ma.srm.srm.backend.dao.SecteurDAO;
import ma.srm.srm.backend.model.CompteurEau;
import ma.srm.srm.backend.model.PositionUpdate;
import ma.srm.srm.backend.model.Secteur;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

@Path("/compteurs-eau")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompteurEauResource {

    @Inject
    private CompteurEauDAO dao;

    @Inject
    private SecteurDAO secteurDAO;

    // -------------------------
    // Liste de tous les compteurs sans photo
    // -------------------------
    @GET
    public List<CompteurEau> getAll() {
        return makeSafeList(dao.findAll());
    }

    // -------------------------
    // Compteur par ID
    // -------------------------
    @GET
    @Path("/{id}")
    public CompteurEau getById(@PathParam("id") Long id) {
        return dao.findById(id);
    }

    // -------------------------
    // Création d’un compteur
    // -------------------------
    @POST
    public Response create(CompteurEau compteur) {
        // Vérifie que l'ID du secteur est fourni
        if (compteur.getSecteurId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le secteur est obligatoire").build();
        }

        // Récupère le secteur depuis la DB
        Secteur secteurFromDB = secteurDAO.findById(compteur.getSecteurId());
        if (secteurFromDB == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Secteur inexistant").build();
        }

        compteur.setSecteur(secteurFromDB);
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePosition(@PathParam("id") Long id, PositionUpdate pos) {
        CompteurEau existing = dao.findById(id);
        if (existing == null) return Response.status(Response.Status.NOT_FOUND).build();

        dao.updatePosition(id, pos.getLatitude(), pos.getLongitude());
        return Response.ok().build();
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
        if (existing == null) return Response.status(Response.Status.NOT_FOUND).build();

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
        return makeSafeList(dao.findBySecteur(secteurId));
    }

    // -------------------------
    // Filtrage par statut
    // -------------------------
    @GET
    @Path("/statut/{statut}")
    public List<CompteurEau> getByStatut(@PathParam("statut") String statut) {
        return makeSafeList(dao.findByStatut(statut));
    }

    // -------------------------
    // Filtrage par secteur et statut
    // -------------------------
    @GET
    @Path("/secteur/{secteurId}/statut/{statut}")
    public List<CompteurEau> getBySecteurAndStatut(@PathParam("secteurId") Long secteurId,
                                                   @PathParam("statut") String statut) {
        return makeSafeList(dao.findBySecteurAndStatut(secteurId, statut));
    }

    // -------------------------
    // Méthode utilitaire pour éviter les références cycliques JSON
    // -------------------------
    private List<CompteurEau> makeSafeList(List<CompteurEau> list) {
        List<CompteurEau> safeList = new ArrayList<>();
        for (CompteurEau c : list) {
            CompteurEau copy = new CompteurEau();
            copy.setId(c.getId());
            copy.setNumero(c.getNumero());
            copy.setDiametre(c.getDiametre());
            copy.setDatePose(c.getDatePose());
            copy.setLatitude(c.getLatitude());
            copy.setLongitude(c.getLongitude());
            copy.setPhoto(null);
            copy.setStatut(c.getStatut());

            if (c.getSecteur() != null) {
                copy.setSecteur(new Secteur());
                copy.getSecteur().setId(c.getSecteur().getId());
                copy.getSecteur().setNom(c.getSecteur().getNom());
            }

            safeList.add(copy);
        }
        return safeList;
    }
}
