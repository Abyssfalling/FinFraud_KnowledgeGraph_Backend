// Entity.java
package com.example.neo4jback.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
@NodeEntity(label = "Entity")
@Data
@Builder
public class Entity {
    @Property
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
