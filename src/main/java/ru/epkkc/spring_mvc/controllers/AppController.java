package ru.epkkc.spring_mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.epkkc.spring_mvc.dao.UserDaoInt;
import ru.epkkc.spring_mvc.model.Role;
import ru.epkkc.spring_mvc.model.RolesEnum;
import ru.epkkc.spring_mvc.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
public class AppController {

    private final UserDaoInt dao;

    @Autowired
    public AppController(UserDaoInt dao) {
        this.dao = dao;
    }

    @GetMapping("/admin")
    public String usersTable(ModelMap model) {
        List<User> allUsers = dao.findAllUsers();

        for (User user : allUsers) {
            System.out.println(user);
        }

        User user = new User();
        user.getRoles().add(new Role());
        model.addAttribute("users_list", allUsers);
        model.addAttribute("user_add", user);
        model.addAttribute("user_update", user);
        model.addAttribute("roles", RolesEnum.values());
        return "users_table";
    }

    @PostMapping("/admin")
    public RedirectView addUser(@ModelAttribute(name = "user_add") User user){
        dao.addUser(user);
        return new RedirectView("http://localhost:8080/admin");
    }

    @PatchMapping("/admin")
    public RedirectView patchUser(@ModelAttribute(name = "user_update") User user){
        dao.updateUser(user);
        return new RedirectView("http://localhost:8080/admin");
    }

    @DeleteMapping("/admin")
    public RedirectView removeUser(@RequestParam(name = "user_id") Long id){
        dao.removeUserWithId(id);
        return new RedirectView("http://localhost:8080/admin");
    }

    @GetMapping("/user")
    public String userPage(ModelMap model){
        System.out.println("method: userPage");
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(currentUser);
        model.addAttribute("name",currentUser.getName());
        model.addAttribute("lastname",currentUser.getLastname());
        model.addAttribute("year_of_birth",currentUser.getYearOfBirth());
        model.addAttribute("username",currentUser.getUsername());
        model.addAttribute("password",currentUser.getPassword());
        return "user_page";
    }

    @GetMapping(value = "/login")
    public String loginPage(){
        return "login_page";
    }

}
