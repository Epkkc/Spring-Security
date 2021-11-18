package ru.epkkc.spring_mvc.model;

public enum RolesEnum {
    ADMIN, USER;

    @Override
    public String toString() {
        return this.name();
    }
}
