package ma.srm.srm.backend.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.srm.srm.backend.model.CompteurEau;

import java.util.List;

@Stateless
public class CompteurEauDAO {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public List<CompteurEau> findAll() {
        return em.createQuery("SELECT c FROM CompteurEau c", CompteurEau.class)
                 .getResultList();
    }

    public CompteurEau findById(Long id) {
        return em.find(CompteurEau.class, id);
    }

    public void save(CompteurEau compteur) {
        em.persist(compteur);
    }

  public void update(CompteurEau compteur) {
    CompteurEau existing = findById(compteur.getId());
    if (existing != null) {
        // On met à jour seulement les champs modifiés
        existing.setLatitude(compteur.getLatitude());
        existing.setLongitude(compteur.getLongitude());

        // ⚠️ ici tu peux aussi mettre à jour les autres champs si besoin
        if (compteur.getNumero() != null) existing.setNumero(compteur.getNumero());
        if (compteur.getDiametre() != null) existing.setDiametre(compteur.getDiametre());
        if (compteur.getDatePose() != null) existing.setDatePose(compteur.getDatePose());
        if (compteur.getType() != null) existing.setType(compteur.getType());
        if (compteur.getUser() != null) existing.setUser(compteur.getUser());

        em.merge(existing);
    }
}       
  
     public void updatePosition(Long id, double latitude, double longitude) {
        CompteurEau existing = findById(id);
        if (existing != null) {
            existing.setLatitude(latitude);
            existing.setLongitude(longitude);
            em.merge(existing);
        }
    }


    public void delete(Long id) {
        CompteurEau c = findById(id);
        if (c != null) {
            em.remove(c);
        }
    }
}
