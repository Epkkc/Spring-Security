package ru.epkkc.spring_mvc.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String s) {
        RolesEnum rolesEnum = RolesEnum.valueOf(s);
        return new Role(rolesEnum);
    }
}
