package ru.epkkc.spring_mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.epkkc.spring_mvc.model.Role;
import ru.epkkc.spring_mvc.model.User;
import ru.epkkc.spring_mvc.services.RoleServiceInt;
import ru.epkkc.spring_mvc.services.UserServiceInt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class AppController {

    private final UserServiceInt userService;
    private final RoleServiceInt roleService;

    @Autowired
    public AppController(UserServiceInt userService, RoleServiceInt roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String usersTable(ModelMap model) {
        List<User> allUsers = userService.findAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
        List<Role> allRoles = roleService.getAllRoles();

        User user = new User();
        model.addAttribute("users_list", allUsers);
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("user_add", user);
        model.addAttribute("user_update", user);
        return "users_table";
    }

    @PostMapping("/post_admin")
    public String addUser(@ModelAttribute(name = "user_add") User user) {
        userService.addUser(findRolesInDB(user));
        return "redirect:/admin";
    }

    @PutMapping("/put_admin")
    public String patchUser(@ModelAttribute(name = "user_update") User user) {
        userService.updateUser(findRolesInDB(user));
        return "redirect:/admin";
    }

    @DeleteMapping("/delete_admin")
    public String removeUser(@RequestParam(name = "user_id") Long id) {
        userService.removeUserWithId(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String userPage(ModelMap model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("current_user", currentUser);
        return "user_page";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login_page";
    }

    private User findRolesInDB(User user) {
        user.setRoles(new HashSet<>(user.getRoles()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList())));
        if (!user.getRoles().isEmpty()) {
            List<Role> dbRoles = new ArrayList<>();
            for (Role role : user.getRoles()) {
                Role role1 = roleService.findRoleByRoleType(role.getRoleType());
                dbRoles.add(role1);
            }
            user.setRoles(new HashSet<>(dbRoles));
        }
        return user;
    }

}
