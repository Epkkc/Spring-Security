package ru.epkkc.spring_mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.epkkc.spring_mvc.model.User;
import ru.epkkc.spring_mvc.services.UserServiceInt;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserServiceInt userService;

    @Autowired
    public UserDetailServiceImpl(UserServiceInt userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Username is null");
        }
        User user = userService.findUserWithUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("There are no user with username = " + username);
        }
    }
}
