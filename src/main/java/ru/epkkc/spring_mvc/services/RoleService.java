package ru.epkkc.spring_mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.epkkc.spring_mvc.dao.RoleDaoInt;
import ru.epkkc.spring_mvc.model.Role;
import ru.epkkc.spring_mvc.model.RolesEnum;

import java.util.List;
@Transactional
@Service
public class RoleService implements RoleServiceInt{

    private final RoleDaoInt roleDao;

    @Autowired
    public RoleService(RoleDaoInt roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public Role findRoleByRoleType(RolesEnum roleType) {
        return roleDao.findRoleByRoleType(roleType);
    }
}
