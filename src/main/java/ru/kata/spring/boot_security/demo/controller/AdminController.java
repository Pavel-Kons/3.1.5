package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allusers")
    public List<User> getAllUsers() {
        return new ArrayList<>(userService.getAllUsers());
    }

    @GetMapping("/getUser")
    public User getUserById(@RequestParam long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/newuser")
    public void addNewUser(@RequestBody UserDTO userDTO) {


       /* Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        Set<String> objects = new HashSet<>();
        objects.add("ADMIN");
        objects.add("USER");
        User user1 = new User("Test", "Tester", (byte) 99, "asd@122", "1", Set.of(adminRole));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));*/

        userService.saveUser(userDTO);
    }

    @DeleteMapping("/deleteuser")
    public void deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
    }
//editUser


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
