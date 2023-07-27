package com.dhu.aml.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@NodeEntity(label = "Person")
public class UserNode {

    @Id
    @GeneratedValue
    private Long identity; // Change property name to "identity"
    @Property(name = "name") // Set property name as "name"
    private String name;
    @Property(name = "category") // Set property name as "category"
    private String category;
    @Property(name = "index") // Set property name as "id"
    private String index; // Change property name to "index"

    @Property(name = "id")
    private String id;

    public UserNode(Long identity, String name, String category, String index) {
        this.identity = identity;
        this.name = name;
        this.category = category;
        this.index = index;
    }

    public UserNode(Long identity, String name, String category, String index, String id) {
        this.identity = identity;
        this.name = name;
        this.category = category;
        this.index = index;
        this.id = id;
    }

    public UserNode(){}

    public UserNode(String name){
        this.name = name;
    }

}
