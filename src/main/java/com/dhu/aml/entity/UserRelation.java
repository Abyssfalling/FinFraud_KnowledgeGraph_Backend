package com.dhu.aml.entity;

import com.dhu.aml.entity.categories.lineStyle;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type = "LINKED_TO")
public class UserRelation {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private UserNode sourceNode;

    @EndNode
    private UserNode targetNode;

    @Property
    private String source;

    @Property
    private String target;

    @Property
    private lineStyle linestyle;


    public UserRelation() {
    }

    public UserRelation(Long id, UserNode startNode, UserNode endNode) {
        this.id = id;
        this.sourceNode = startNode;
        this.targetNode = endNode;
    }

    public UserRelation(Long id, String source, String target) {
        this.id = id;
        this.source = source;
        this.target = target;
    }

    public UserRelation(Long id, String source, String target, lineStyle linestyle) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.linestyle = linestyle;
    }
}
