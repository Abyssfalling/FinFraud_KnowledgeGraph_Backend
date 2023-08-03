package com.dhu.aml.entity;

import com.dhu.aml.service.Neo4jService;
import lombok.Data;
import org.neo4j.ogm.annotation.*;
import scala.Int;

@Data
@NodeEntity(label = "Person")
public class UserNode {

    @Id
    @GeneratedValue
    private Long identity; // Change property name to "identity"
    @Property(name = "name") // Set property name as "name"
    private String name;
    @Property(name = "cate") // Set property name as "category"
    private String cate;
    @Property(name = "index") // Set property name as "id"
    private String index; // Change property name to "index"
    @Property(name = "id")
    private int id;
    @Property
    private int category;

    public UserNode(Long identity, String name, String cate, String index) {
        this.identity = identity;
        this.name = name;
        this.cate = cate;
        this.index = index;
    }

    public UserNode(Long identity, String name, int category, String index, int id) {
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

    public UserNode(String subjectName, int cate) {
        this.name = subjectName;
        this.cate = Integer.toString(cate);
    }

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
