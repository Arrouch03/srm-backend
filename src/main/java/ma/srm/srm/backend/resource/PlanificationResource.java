package ma.srm.srm.backend.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.srm.srm.backend.dao.PlanificationDAO;
import ma.srm.srm.backend.model.Planification;
import ma.srm.srm.backend.model.Secteur;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/planification")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON)
public class PlanificationResource {

    private static final Logger LOGGER = Logger.getLogger(PlanificationResource.class.getName());

    @Inject
    private PlanificationDAO dao;

    /**
     * Retourne le secteur à contrôler pour aujourd'hui pour un agent donné.
     * Retourne 404 si aucune planification n'est trouvée.
     */
    @GET
    @Path("/aujourdhui/{agentId}")
    public Response getSecteurDuJour(@PathParam("agentId") Long agentId) {
        LOGGER.log(Level.INFO, "==> Resource: Requête pour l'agent {0} pour aujourd'hui", agentId);

        Planification plan = dao.findByAgentAndDate(agentId, new Date());
        if (plan == null) {
            LOGGER.log(Level.INFO, "==> DAO: Aucune planification trouvée pour aujourd'hui pour l'agent {0}", agentId);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Aucune planification trouvée pour l'agent " + agentId)
                    .build();
        }

        Secteur secteur = plan.getSecteur();
        LOGGER.log(Level.INFO, "==> DAO: Planification trouvée pour l'agent {0}, secteur: {1}", 
                   new Object[]{agentId, secteur.getVille()});

        return Response.ok(secteur).build();
    }
}
