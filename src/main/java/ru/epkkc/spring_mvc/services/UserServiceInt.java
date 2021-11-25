package ru.epkkc.spring_mvc.services;

import ru.epkkc.spring_mvc.dao.UserDaoInt;
import ru.epkkc.spring_mvc.model.User;

import java.util.List;

public interface UserServiceInt {

    void addUser(User user);

    User getUserWithId(long id);

    List<User> findAllUsers();

    void updateUser(User user);

    void removeUserWithId(long id);

    User findUserWithUsername(String username);
}
