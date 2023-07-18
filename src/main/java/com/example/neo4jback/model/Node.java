package com.example.neo4jback.model;

public class Node {
    private int id;
    private String name;
    private int category;
    private int identity;

    public Node() {
    }

    public Node(int id, String name, int category, int identity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.identity = identity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    // 省略构造函数、Getter和Setter
}
