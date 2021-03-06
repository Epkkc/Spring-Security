package ru.epkkc.spring_mvc.dao;

import org.springframework.stereotype.Repository;
import ru.epkkc.spring_mvc.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao implements UserDaoInt {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void addUser(User user) {
        manager.persist(user);
    }

    @Override
    public User getUserWithId(long id) {
        return manager.find(User.class, id);
    }


    @Override
    public List<User> findAllUsers() {
        List<User> list = manager.createQuery("from User", User.class).getResultList();
        for (User user : list) {
            System.out.println(user);
        }
        return list == null ? new ArrayList<User>() : list;
    }


    @Override
    public void updateUser(User user) {
        manager.merge(user);
    }


    @Override
    public void removeUserWithId(long id) {
        Query query = manager.createQuery("delete from User u where u.user_id = :id")
                .setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public User findUserWithUsername(String username) {
        TypedQuery<User> query = manager.createQuery("from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
