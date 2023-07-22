package com.example.neo4jback.dao;

import com.example.neo4jback.model.Entity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface EntityRepository extends Neo4jRepository<Entity,Long> {
}
