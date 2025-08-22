package ma.srm.srm.backend.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import ma.srm.srm.backend.model.User;

@Stateless
public class UserDAO {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

  public User findByUsername(String username) {
    List<User> results = em.createQuery(
        "SELECT u FROM User u WHERE UPPER(u.username) = UPPER(:username)", User.class)
        .setParameter("username", username)
        .getResultList();
    return results.isEmpty() ? null : results.get(0);
}



    public void update(User user) {
        em.merge(user);
    }

    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            em.remove(user);
        }
    }
}
