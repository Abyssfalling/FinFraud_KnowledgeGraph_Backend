package com.example.neo4jback.repository;

import com.example.neo4jback.model.Action;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends Neo4jRepository<Action,Long> {

}
