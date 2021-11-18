package ru.epkkc.spring_mvc.model;

import org.springframework.security.core.GrantedAuthority;

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
import java.util.Set;

@Entity
@Table
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Enumerated(value = EnumType.STRING)
    @Column
    private RolesEnum roleType;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;

    public Role(RolesEnum roleType) {
        this.roleType = roleType;
    }

    public Role() {
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

    @Override
    public String toString() {
        return "Role{" +
                ", roleType=" + roleType.name() +
                '}';
    }

    @Override
    public String getAuthority() {
        return roleType.name();
    }
}