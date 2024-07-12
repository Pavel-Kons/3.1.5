package ru.kata.spring.boot_security.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "role")
@Data
public class Role /*implements GrantedAuthority */ {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 45)
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }
}
