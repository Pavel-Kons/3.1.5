package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MyImpl {
    private final EntityManager entityManager;

    //
    public MyImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
