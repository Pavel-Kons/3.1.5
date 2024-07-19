package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Set;

@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(ModelMap model,
                              @RequestParam(value = "count", required = false, defaultValue = "100") Integer count,
                              Principal principal) {
        model.addAttribute("users", userService.getUsers(count));
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        model.addAttribute("roles", userService.getAllRolesNames());

        return "/admin";
    }

//    @GetMapping("/newUser")
//    public String getNewUserPage(ModelMap modelMap, @ModelAttribute("user") User user) {
//        modelMap.addAttribute("roles", userService.getAllRolesNames());
//
//        return "redirect:/admin";
//    }
//
    @PostMapping("/admin/newUser")
    public String createNewUser(@ModelAttribute("user") User user,
                                @RequestParam(name = "selectedRoles", required = false) Set<String> selectedRoles) {
        userService.saveUser(user, selectedRoles);
        return "redirect:/admin";
    }
//
//    @GetMapping("/editUser")
//    public String getUser(ModelMap model,
//                          @RequestParam(value = "id") Long id) {
//        model.addAttribute("user", userService.getUserById(id));
//        model.addAttribute("roles", userService.getAllRolesNames());
//
//        return "redirect:/admin";
//    }
//
//    @PatchMapping(value = "/users")
//    public String updateUser(@ModelAttribute("user") User user,
//                             @RequestParam(name = "selectedRoles", required = false) Set<String> selectedRoles) {
//        userService.saveUser(user, selectedRoles);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/users")
//    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
}
