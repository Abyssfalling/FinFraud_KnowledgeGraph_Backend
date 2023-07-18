package com.example.neo4jback.model;

import java.util.List;

public class SubjectSpaceResponse {
    private List<Category> categories;
    private List<Node> nodes;
    private List<Link> links;

    public SubjectSpaceResponse() {
    }

    public SubjectSpaceResponse(List<Category> categories, List<Node> nodes, List<Link> links) {
        this.categories = categories;
        this.nodes = nodes;
        this.links = links;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    // 省略构造函数、Getter和Setter
}

