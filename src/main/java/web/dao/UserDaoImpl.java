package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.users.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements  UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<User> allUsers () {

        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    public void createUser(User user) {
        entityManager.persist(user);

    }
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    public void updateUser (User user) {
        entityManager.merge(user);
    }
    public void deleteUser(long id) {
        entityManager.remove(getById(id));
    }


}
