package ru.epkkc.spring_mvc.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Enumerated(value = EnumType.STRING)
    @Column
    private RolesEnum roleType = RolesEnum.USER;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> users;

    public Role(RolesEnum roleType) {
        this.roleType = roleType;
    }

    public Role() {
    }

    public Role(Long role_id, RolesEnum roleType) {
        this.role_id = role_id;
        this.roleType = roleType;
    }

    public Role(Long role_id, RolesEnum roleType, List<User> users) {
        this.role_id = role_id;
        this.roleType = roleType;
        this.users = users;
    }

    public RolesEnum getRoleType() {
        return roleType;
    }

    public void setRoleType(RolesEnum roleType) {
        this.roleType = roleType;
    }

    public Long getId() {
        return role_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return roleType.toString();
    }

    @Override
    public String getAuthority() {
        return roleType.name();
    }
}
