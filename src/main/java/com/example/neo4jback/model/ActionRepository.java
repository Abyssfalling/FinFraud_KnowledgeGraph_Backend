package com.example.neo4jback.model;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ActionRepository extends Neo4jRepository<Action, Long> {
}
