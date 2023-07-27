package com.dhu.aml.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type = "LINKED_TO")
public class UserRelation {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private UserNode source;

    @EndNode
    private UserNode target;

    public UserRelation() {
    }

    public UserRelation(Long id, UserNode startNode, UserNode endNode) {
        this.id = id;
        this.source = startNode;
        this.target = endNode;
    }
}
