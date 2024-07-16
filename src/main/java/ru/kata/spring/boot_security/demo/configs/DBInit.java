package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DBInit {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBInit(UserRepository userRepository, UserServiceImpl userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void createAdminAndUser() {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        userRepository.deleteAll();
        roleRepository.deleteAll();
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        User first = new User("", "", (byte) 0, "1", "1", Set.of(adminRole));
        User user = new User("admin", "adminovich", (byte) 17, "admin@mail.ru", "1", Set.of(adminRole));
        User admin = new User("user", "userovich", (byte) 18, "user@mail.ru", "1", Set.of(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        first.setPassword(passwordEncoder.encode(first.getPassword()));

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(first);
    }
}
