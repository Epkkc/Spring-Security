package ru.epkkc.spring_mvc.dao;

import ru.epkkc.spring_mvc.model.Role;
import ru.epkkc.spring_mvc.model.RolesEnum;

import java.util.List;

public interface RoleDaoInt {

    List<Role> getAllRoles();

    void addRole(Role role);

    Role findRoleByRoleType(RolesEnum roleType);
}
