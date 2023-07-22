package com.example.neo4jback.dao;

import com.example.neo4jback.model.EntityRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface EntityRelationshipRepository extends Neo4jRepository<EntityRelationship,Long > {

//    @Query("match (n:Entity{name:{0}}),(m:Entity{name:{2}})" +
//    "create (n)-[:属于{relation:{1}}]-(m)")
//    void createRelation(String from,String relation,String to);
}
