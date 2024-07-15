package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String getAllUsers(ModelMap model,
                              @RequestParam(value = "count", required = false, defaultValue = "100") Integer count) {
        model.addAttribute("users", userService.getUsers(count));
        return "admin/users";
    }

    @GetMapping("/newUser")
    public String getNewUserPage(ModelMap modelMap, @ModelAttribute("user") User user) {
        modelMap.addAttribute("roles", userService.getAllRolesNames());

        return "admin/newUser";
    }

    @PostMapping("/newUser")
    public String createNewUser(@ModelAttribute("user") User user,
                                @RequestParam(name = "selectedRoles", required = false) Set<String> selectedRoles) {
        userService.saveUser(user, selectedRoles);
        return "redirect:/admin/users";
    }

    @GetMapping("/editUser")
    public String getUser(ModelMap model,
                          @RequestParam(value = "id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", userService.getAllRolesNames());

        return "admin/editUser";
    }

    @PatchMapping(value = "/users")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(name = "selectedRoles", required = false) Set<String> selectedRoles) {
        userService.updateUser(user, selectedRoles);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
