package ru.epkkc.spring_mvc.dao;

import org.springframework.stereotype.Repository;
import ru.epkkc.spring_mvc.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDao implements RoleDaoInt {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Role> getAllRoles() {
        return manager.createQuery("from Role, Role.class").getResultList();
    }

    @Override
    public void addRole(Role role) {
        manager.persist(role);
    }
}
