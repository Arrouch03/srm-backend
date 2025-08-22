package ma.srm.srm.backend.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.srm.srm.backend.model.CompteurElectricite;

import java.util.List;

@Stateless
public class CompteurElectriciteDAO {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public List<CompteurElectricite> findAll() {
        return em.createQuery("SELECT c FROM CompteurElectricite c", CompteurElectricite.class)
                 .getResultList();
    }

    public CompteurElectricite findById(Long id) {
        return em.find(CompteurElectricite.class, id);
    }

    public CompteurElectricite save(CompteurElectricite compteur) {
        em.persist(compteur);
        em.flush();
        return compteur;
    }

    public void update(CompteurElectricite compteur) {
        CompteurElectricite existing = findById(compteur.getId());
        if (existing != null) {
            if (compteur.getNumero() != null) existing.setNumero(compteur.getNumero());
            if (compteur.getNbFils() != null) existing.setNbFils(compteur.getNbFils());
            if (compteur.getNbRoues() != null) existing.setNbRoues(compteur.getNbRoues());
            if (compteur.getCalibre() != null) existing.setCalibre(compteur.getCalibre());
            if (compteur.getDatePose() != null) existing.setDatePose(compteur.getDatePose());
            if (compteur.getType() != null) existing.setType(compteur.getType());
            if (compteur.getUser() != null) existing.setUser(compteur.getUser());
            if (compteur.getLatitude() != null) existing.setLatitude(compteur.getLatitude());
            if (compteur.getLongitude() != null) existing.setLongitude(compteur.getLongitude());
            if (compteur.getPhoto() != null) existing.setPhoto(compteur.getPhoto());
            em.merge(existing);
        }
    }

    public void updatePosition(Long id, double latitude, double longitude) {
        CompteurElectricite existing = findById(id);
        if (existing != null) {
            existing.setLatitude(latitude);
            existing.setLongitude(longitude);
            em.merge(existing);
        }
    }

    public void updatePhoto(Long id, byte[] photo) {
        CompteurElectricite existing = findById(id);
        if (existing != null) {
            existing.setPhoto(photo);
            em.merge(existing);
        }
    }

    public void delete(Long id) {
        CompteurElectricite c = findById(id);
        if (c != null) em.remove(c);
    }
}
