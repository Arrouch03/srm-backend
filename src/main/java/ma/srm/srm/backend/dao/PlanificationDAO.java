package ma.srm.srm.backend.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.srm.srm.backend.model.Planification;

import java.util.Date;

@Stateless
public class PlanificationDAO {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Planification findByAgentAndDate(Long agentId, Date date) {
        return em.createQuery(
                "SELECT p FROM Planification p WHERE p.agent.id = :agentId AND p.datePlan = :date", Planification.class)
                .setParameter("agentId", agentId)
                .setParameter("date", date)
                .getSingleResult();
    }

    public Planification save(Planification planification) {
        em.persist(planification);
        em.flush();
        return planification;
    }
}
