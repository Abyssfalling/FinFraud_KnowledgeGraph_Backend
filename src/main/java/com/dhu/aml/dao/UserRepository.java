package com.dhu.aml.dao;
import com.dhu.aml.entity.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserNode, Long> {
    UserNode findByNameAndCategory(String name, String category);
}
