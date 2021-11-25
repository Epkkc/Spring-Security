package ru.epkkc.spring_mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.epkkc.spring_mvc.dao.UserDaoInt;
import ru.epkkc.spring_mvc.model.User;

import java.util.List;
@Transactional
@Service
public class UserService implements UserServiceInt{

    private final UserDaoInt userDao;

    @Autowired
    public UserService(UserDaoInt userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUserWithId(long id) {
        return userDao.getUserWithId(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public void updateUser(User user) {
        User userForUpdate = getUserWithId(user.getId());
        if (!user.getName().isEmpty()) userForUpdate.setName(user.getName());
        if (!user.getLastname().isEmpty()) userForUpdate.setLastname(user.getLastname());
        if (user.getYearOfBirth() > 0) userForUpdate.setYearOfBirth(user.getYearOfBirth());
        if (!user.getUsername().isEmpty()) userForUpdate.setUsername(user.getUsername());
        if (!user.getPassword().isEmpty()) userForUpdate.setPassword(user.getPassword());
        if (!user.getRoles().isEmpty()) userForUpdate.setRoles(user.getRoles());
        if (user.getIsActive() != null) userForUpdate.setIsActive(user.getIsActive());

        userDao.updateUser(userForUpdate);
    }

    @Override
    public void removeUserWithId(long id) {
        userDao.removeUserWithId(id);
    }

    @Override
    public User findUserWithUsername(String username) {
        return userDao.findUserWithUsername(username);
    }
}
