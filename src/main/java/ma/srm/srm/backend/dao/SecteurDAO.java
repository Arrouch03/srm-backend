package ma.srm.srm.backend.dao;

import ma.srm.srm.backend.model.Secteur;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class SecteurDAO {

    @PersistenceContext
    private EntityManager em;

    // Récupère tous les secteurs
    public List<Secteur> findAll() {
        return em.createQuery("SELECT s FROM Secteur s", Secteur.class).getResultList();
    }

    // Récupère un secteur par ID
    public Secteur findById(Long id) {
        return em.find(Secteur.class, id);
    }

    // Sauvegarde un nouveau secteur
    public Secteur save(Secteur secteur) {
        em.persist(secteur);
        return secteur;
    }

    // Mettre à jour un secteur
    public void update(Secteur secteur) {
        em.merge(secteur);
    }

    // Supprimer un secteur
    public void delete(Long id) {
        Secteur s = em.find(Secteur.class, id);
        if (s != null) em.remove(s);
    }
}
