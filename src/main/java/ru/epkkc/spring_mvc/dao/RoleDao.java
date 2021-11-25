package ru.epkkc.spring_mvc.dao;

import org.springframework.stereotype.Repository;
import ru.epkkc.spring_mvc.model.Role;
import ru.epkkc.spring_mvc.model.RolesEnum;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDao implements RoleDaoInt {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Role> getAllRoles() {
        return manager.createQuery("from Role r", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        manager.persist(role);
    }

    @Override
    public Role findRoleByRoleType(RolesEnum roleType) {
        TypedQuery<Role> query = manager.createQuery("from Role r where r.roleType = :role", Role.class);
        query.setParameter("role", roleType);
        return query.getSingleResult();
    }
}
