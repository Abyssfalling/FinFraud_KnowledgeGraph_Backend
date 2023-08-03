package com.dhu.aml.dao;
import com.dhu.aml.entity.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

public interface UserNodeRepository extends Neo4jRepository<UserNode, Long> {
    UserNode findByName(String name);

    UserNode findByNameAndCate(String name, int category);

}