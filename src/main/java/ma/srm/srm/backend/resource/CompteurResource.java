package ma.srm.srm.backend.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import ma.srm.srm.backend.dao.CompteurEauDAO;
import ma.srm.srm.backend.dao.CompteurElectriciteDAO;
import ma.srm.srm.backend.model.CompteurEau;
import ma.srm.srm.backend.model.CompteurElectricite;
import ma.srm.srm.backend.dto.CompteurDTO;

@Path("/compteurs")
@Produces(MediaType.APPLICATION_JSON)
public class CompteurResource {

    @Inject
    private CompteurEauDAO eauDAO;

    @Inject
    private CompteurElectriciteDAO elecDAO;

    @GET
    public List<CompteurDTO> getAll() {
        List<CompteurDTO> result = new ArrayList<>();

        // Compteurs d’eau
        List<CompteurEau> eaux = eauDAO.findAll();
        for (CompteurEau e : eaux) {
            result.add(new CompteurDTO(
                    e.getId(),
                    "eau",
                    e.getLatitude(),
                    e.getLongitude()
            ));
        }

        // Compteurs d’électricité
        List<CompteurElectricite> elecs = elecDAO.findAll();
        for (CompteurElectricite c : elecs) {
            result.add(new CompteurDTO(
                    c.getId(),
                    "electricite",
                    c.getLatitude(),
                    c.getLongitude()
            ));
        }

        return result;
    }
}
