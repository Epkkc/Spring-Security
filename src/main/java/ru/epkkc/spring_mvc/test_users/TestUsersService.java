package ru.epkkc.spring_mvc.test_users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.epkkc.spring_mvc.model.Role;
import ru.epkkc.spring_mvc.model.RolesEnum;
import ru.epkkc.spring_mvc.model.User;
import ru.epkkc.spring_mvc.services.RoleServiceInt;
import ru.epkkc.spring_mvc.services.UserServiceInt;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class TestUsersService {

    private final UserServiceInt userService;
    private final RoleServiceInt roleService;

    @Autowired
    public TestUsersService(UserServiceInt userService, RoleServiceInt roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void postConstruct() {
        Role roleUser = new Role(RolesEnum.USER);
        Role roleAdmin = new Role(RolesEnum.ADMIN);

        roleService.addRole(roleUser);
        roleService.addRole(roleAdmin);

        userService.addUser(new User("name1", "lastname1",
                (short) 2001,
                new HashSet<>(Arrays.asList(roleUser)),
                "username1",
                "password1",
                true));
        userService.addUser(new User("name2", "lastname2",
                (short) 2002,
                new HashSet<>(Arrays.asList(roleUser,
                        roleAdmin)),
                "username2",
                "password2",
                true));
        userService.addUser(new User("name3", "lastname3",
                (short) 2003,
                new HashSet<>(Arrays.asList(roleAdmin)),
                "username3",
                "password3",
                true));
        userService.addUser(new User("name4", "lastname4",
                (short) 2004,
                new HashSet<>(
                Arrays.asList(roleUser)),
                "username4",
                "password4",
                false));
    }
}
