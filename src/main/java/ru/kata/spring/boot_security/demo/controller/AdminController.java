package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;

    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return new ArrayList<>(userService.getAllUsers());
    }

    @GetMapping("/users/roles")
    public ResponseEntity<Collection<Role>> getAllRoles() {
        return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public UserDTO addNewUser(@RequestBody UserDTO userDTO) {
        userService.saveOrUpdateUser(userDTO);
        return userDTO;
    }

    @PutMapping("/users")
    public UserDTO editUser(@RequestBody UserDTO userDTO) {
        userService.saveOrUpdateUser(userDTO);
        return userDTO;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users/currentUser")
    public User getCurrentUser(Principal principal) {
        return userService.findByEmail(principal.getName());
    }

//    ********************
//    Standard controller
//    ********************

//    @GetMapping(value = "/admin")
//    public String getAdminPage(ModelMap model,
//                               @RequestParam(value = "count", required = false, defaultValue = "100") Integer count,
//                               Principal principal) {
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("user", userService.findByEmail(principal.getName()));
//        model.addAttribute("roles", userService.getAllRolesNames());
//
//        return "/admin";
//    }
//
//    @PostMapping("/admin/newUser")
//    public String createNewUser(@ModelAttribute("user") User user,
//                                @RequestParam(name = "selectedRoles", required = false) Set<String> selectedRoles) {
//        userService.saveUser(user, selectedRoles);
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
