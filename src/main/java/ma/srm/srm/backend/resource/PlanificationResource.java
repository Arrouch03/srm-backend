package ma.srm.srm.backend.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.srm.srm.backend.dao.PlanificationDAO;
import ma.srm.srm.backend.model.Planification;
import ma.srm.srm.backend.model.Secteur;

import java.util.Date;

@Path("/planification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanificationResource {

    @Inject
    private PlanificationDAO dao;

    @GET
    @Path("/aujourdhui/{agentId}")
    public Secteur getSecteurDuJour(@PathParam("agentId") Long agentId) {
        Planification plan = dao.findByAgentAndDate(agentId, new Date());
        return plan.getSecteur();
    }
}
