package ma.srm.srm.backend.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import ma.srm.srm.backend.model.CompteurElectricite;

@Stateless
public class CompteurElectriciteDAO {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public void save(CompteurElectricite compteur) {
        em.persist(compteur);
    }

    public CompteurElectricite findById(Long id) {
        return em.find(CompteurElectricite.class, id);
    }

    public List<CompteurElectricite> findAll() {
        return em.createQuery("SELECT c FROM CompteurElectricite c", CompteurElectricite.class)
                 .getResultList();
    }
    
      public void updatePosition(Long id, double latitude, double longitude) {
        CompteurElectricite existing = findById(id);
        if (existing != null) {
            existing.setLatitude(latitude);
            existing.setLongitude(longitude);
            em.merge(existing);
        }
    }

    public void update(CompteurElectricite compteur) {
        CompteurElectricite existing = findById(compteur.getId());
        if (existing != null) {
            // 🔹 Mise à jour des coordonnées
            existing.setLatitude(compteur.getLatitude());
            existing.setLongitude(compteur.getLongitude());

            // 🔹 Mise à jour conditionnelle des autres champs
            if (compteur.getNumero() != null) existing.setNumero(compteur.getNumero());
            if (compteur.getNbFils() != null) existing.setNbFils(compteur.getNbFils());
            if (compteur.getNbRoues() != null) existing.setNbRoues(compteur.getNbRoues());
            if (compteur.getCalibre() != null) existing.setCalibre(compteur.getCalibre());
            if (compteur.getDatePose() != null) existing.setDatePose(compteur.getDatePose());
            if (compteur.getType() != null) existing.setType(compteur.getType());
            if (compteur.getUser() != null) existing.setUser(compteur.getUser());

            em.merge(existing);
        }
    }

    public void delete(Long id) {
        CompteurElectricite compteur = findById(id);
        if (compteur != null) {
            em.remove(compteur);
        }
    }
}
