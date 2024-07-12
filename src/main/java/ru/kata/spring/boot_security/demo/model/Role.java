package ru.kata.spring.boot_security.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
public class Role /*implements GrantedAuthority */ {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

}
