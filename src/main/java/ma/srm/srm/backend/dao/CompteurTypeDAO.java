package ma.srm.srm.backend.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import ma.srm.srm.backend.model.CompteurType;

@Stateless
public class CompteurTypeDAO {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public void save(CompteurType type) {
        em.persist(type);
    }

    public CompteurType findById(Long id) {
        return em.find(CompteurType.class, id);
    }

    public List<CompteurType> findAll() {
        return em.createQuery("SELECT t FROM CompteurType t", CompteurType.class)
                 .getResultList();
    }

    public void update(CompteurType type) {
        em.merge(type);
    }

    public void delete(Long id) {
        CompteurType type = findById(id);
        if (type != null) {
            em.remove(type);
        }
    }
}
