package ru.epkkc.spring_mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.epkkc.spring_mvc.dao.UserDaoInt;
import ru.epkkc.spring_mvc.model.User;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDaoInt userDao;

    @Autowired
    public UserDetailServiceImpl(UserDaoInt userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Username is null");
        }
        User user = userDao.findUserWithUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("There are no user with username = " + username);
        }
    }
}
