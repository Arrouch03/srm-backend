package ma.srm.srm.backend.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.srm.srm.backend.model.Planification;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PlanificationDAO {

    private static final Logger LOGGER = Logger.getLogger(PlanificationDAO.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    /**
     * Récupère la planification d'un agent pour une date donnée.
     * Retourne null si aucune planification n'est trouvée.
     */
    public Planification findByAgentAndDate(Long agentId, Date date) {
        // Début et fin de la journée
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startOfDay = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date endOfDay = cal.getTime();

        LOGGER.info("==> DAO: Recherche planification pour agent " + agentId
                + " entre " + startOfDay + " et " + endOfDay);

        List<Planification> plans = em.createQuery(
                "SELECT p FROM Planification p WHERE p.agent.id = :agentId AND p.datePlan BETWEEN :start AND :end",
                Planification.class)
                .setParameter("agentId", agentId)
                .setParameter("start", startOfDay, jakarta.persistence.TemporalType.TIMESTAMP)
                .setParameter("end", endOfDay, jakarta.persistence.TemporalType.TIMESTAMP)
                .getResultList();

        if (plans.isEmpty()) {
            LOGGER.info("==> DAO: Aucune planification trouvée pour aujourd'hui pour l'agent " + agentId);
            return null;
        }

        Planification plan = plans.get(0);
        if (plan.getSecteur() != null) {
            LOGGER.info("==> DAO: Secteur récupéré: " + plan.getSecteur().getNom());
        } else {
            LOGGER.info("==> DAO: Secteur null pour cette planification");
        }

        return plan;
    }

    /**
     * Sauvegarde une planification en base de données.
     */
    public Planification save(Planification planification) {
        em.persist(planification);
        em.flush();
        LOGGER.info("==> DAO: Planification sauvegardée pour agent " + planification.getAgent().getId());
        return planification;
    }
}
