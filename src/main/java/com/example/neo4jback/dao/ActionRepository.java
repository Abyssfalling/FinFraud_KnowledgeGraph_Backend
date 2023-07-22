package com.example.neo4jback.dao;

import com.example.neo4jback.model.Action;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ActionRepository extends Neo4jRepository<Action,Long> {
}
