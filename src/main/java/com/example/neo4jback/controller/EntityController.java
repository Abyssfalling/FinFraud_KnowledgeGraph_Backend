package com.example.neo4jback.controller;

import com.example.neo4jback.model.Entity;
import com.example.neo4jback.dao.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntityController {

    private final EntityRepository entityRepository;

    @Autowired
    public EntityController(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @GetMapping("/entities")
    public Iterable<Entity> getAllEntities() {
        return entityRepository.findAll();
    }
}
