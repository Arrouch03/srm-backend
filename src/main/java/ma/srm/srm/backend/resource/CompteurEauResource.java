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

    // 🔹 Liste des compteurs sans la photo
    @GET
    public List<CompteurEau> getAll() {
        List<CompteurEau> list = dao.findAll();
        list.forEach(c -> c.setPhoto(null)); // Exclure la photo
        return list;
    }

    @GET
    @Path("/{id}")
    public CompteurEau getById(@PathParam("id") Long id) {
        return dao.findById(id); // Ici la photo sera incluse si nécessaire
    }

    @POST
    public Response create(CompteurEau compteur) {
        CompteurEau saved = dao.save(compteur);
        return Response.status(Response.Status.CREATED).entity(saved).build();
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
    public void updatePosition(@PathParam("id") Long id, CompteurEau data) {
        dao.updatePosition(id, data.getLatitude(), data.getLongitude());
    }

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
}
