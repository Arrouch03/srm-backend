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

    // -------------------------
    // Méthodes CRUD de base
    // -------------------------
    public List<CompteurEau> findAll() {
        return em.createQuery("SELECT c FROM CompteurEau c", CompteurEau.class)
                 .getResultList();
    }

    public CompteurEau findById(Long id) {
        return em.find(CompteurEau.class, id);
    }

    public CompteurEau save(CompteurEau compteur) {
        em.persist(compteur);
        em.flush(); // ⚡ génère l'ID immédiatement
        return compteur;
    }

    public void update(CompteurEau compteur) {
        CompteurEau existing = findById(compteur.getId());
        if (existing != null) {
            if (compteur.getNumero() != null) existing.setNumero(compteur.getNumero());
            if (compteur.getDiametre() != null) existing.setDiametre(compteur.getDiametre());
            if (compteur.getDatePose() != null) existing.setDatePose(compteur.getDatePose());
            if (compteur.getType() != null) existing.setType(compteur.getType());
            if (compteur.getUser() != null) existing.setUser(compteur.getUser());
            if (compteur.getLatitude() != null) existing.setLatitude(compteur.getLatitude());
            if (compteur.getLongitude() != null) existing.setLongitude(compteur.getLongitude());
            if (compteur.getPhoto() != null) existing.setPhoto(compteur.getPhoto());
            if (compteur.getStatut() != null) existing.setStatut(compteur.getStatut());
            if (compteur.getSecteur() != null) existing.setSecteur(compteur.getSecteur()); // ✅ ajout
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

    public void updatePhoto(Long id, byte[] photo) {
        CompteurEau existing = findById(id);
        if (existing != null) {
            existing.setPhoto(photo);
            em.merge(existing);
        }
    }

    public void delete(Long id) {
        CompteurEau c = findById(id);
        if (c != null) em.remove(c);
    }

    // -------------------------
    // Méthodes de filtrage
    // -------------------------
    public List<CompteurEau> findBySecteur(Long secteurId) {
        return em.createQuery(
            "SELECT c FROM CompteurEau c WHERE c.secteur.id = :secteurId", CompteurEau.class)
            .setParameter("secteurId", secteurId)
            .getResultList();
    }

    public List<CompteurEau> findByStatut(String statut) {
        return em.createQuery(
            "SELECT c FROM CompteurEau c WHERE c.statut = :statut", CompteurEau.class)
            .setParameter("statut", statut)
            .getResultList();
    }

    public List<CompteurEau> findBySecteurAndStatut(Long secteurId, String statut) {
        return em.createQuery(
            "SELECT c FROM CompteurEau c WHERE c.secteur.id = :secteurId AND c.statut = :statut", CompteurEau.class)
            .setParameter("secteurId", secteurId)
            .setParameter("statut", statut)
            .getResultList();
    }
}
