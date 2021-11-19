package ru.epkkc.spring_mvc.model;

import org.springframework.core.convert.converter.Converter;


public class RoleConverter implements Converter<String, Role> {


    @Override
    public Role convert(String s) {
        if (s.equals("0")){
            return null;
        }
        RolesEnum rolesEnum = RolesEnum.valueOf(s);
        return new Role(rolesEnum);
    }
}
