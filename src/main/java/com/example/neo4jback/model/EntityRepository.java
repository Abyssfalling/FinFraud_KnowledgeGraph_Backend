package com.example.neo4jback.model;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EntityRepository extends Neo4jRepository<Entity, Long> {
}
