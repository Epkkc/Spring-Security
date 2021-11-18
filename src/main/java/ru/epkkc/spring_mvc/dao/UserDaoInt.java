package ru.epkkc.spring_mvc.dao;

import org.springframework.stereotype.Repository;
import ru.epkkc.spring_mvc.model.User;

import java.util.List;

public interface UserDaoInt {

    void addUser(User user);
    User getUserWithId(long id);
    List<User> findAllUsers();
    void updateUser(User user);
    void removeUserWithId(long id);
    User findUserWithUsername(String username);

}
