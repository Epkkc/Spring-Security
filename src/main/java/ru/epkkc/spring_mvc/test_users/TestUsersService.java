package ru.epkkc.spring_mvc.test_users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.epkkc.spring_mvc.dao.RoleDaoInt;
import ru.epkkc.spring_mvc.dao.UserDaoInt;
import ru.epkkc.spring_mvc.model.Role;
import ru.epkkc.spring_mvc.model.RolesEnum;
import ru.epkkc.spring_mvc.model.User;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class TestUsersService {

    private final UserDaoInt userDao;
    private final RoleDaoInt roleDao;

    @Autowired
    public TestUsersService(UserDaoInt dao, RoleDaoInt roleDao) {
        this.userDao = dao;
        this.roleDao = roleDao;
    }

    @PostConstruct
    public void postConstruct() {
//        roleDao.addRole(new Role(RolesEnum.USER));
//        roleDao.addRole(new Role(RolesEnum.ADMIN));

        userDao.addUser(new User("name1", "lastname1",
                (short) 2001,
                Arrays.asList(new Role(RolesEnum.USER)),
                "username1",
                "password1",
                true));
        userDao.addUser(new User("name2", "lastname2",
                (short) 2002,
                Arrays.asList(new Role(RolesEnum.USER),
                        new Role(RolesEnum.ADMIN)),
                "username2",
                "password2",
                true));
        userDao.addUser(new User("name3", "lastname3",
                (short) 2003,
                Arrays.asList(new Role(RolesEnum.ADMIN)),
                "username3",
                "password3",
                true));
        userDao.addUser(new User("name4", "lastname4",
                (short) 2004,
                Arrays.asList(new Role(RolesEnum.USER)),
                "username4",
                "password4",
                false));
    }
}
