package com.example.neo4jback.model.Space;

import com.example.neo4jback.model.Link;
import com.example.neo4jback.model.Node;
import com.example.neo4jback.model.categories.Category1;
import com.example.neo4jback.model.categories.ItemStyle;

import java.util.ArrayList;
import java.util.List;

public class SubjectSpaceResponse {
    private List<Category1> categories;
    private List<Node> nodes;
    private List<Link> links;

    public SubjectSpaceResponse() {
    }

    public static List<Category1> generateCategories() {
        List<Category1> categories = new ArrayList<>();

        // Create and add "实体" category
        ItemStyle entityItemStyle = new ItemStyle("#E99D42");
        Category1 entityCategory1 = new Category1(0, "实体",entityItemStyle);
        categories.add(entityCategory1);

        // Create and add "行为" category
        ItemStyle behaviorItemStyle = new ItemStyle("#4095E5");
        Category1 behaviorCategory1 = new Category1(1, "行为",behaviorItemStyle);
        categories.add(behaviorCategory1);

        return categories;
    }


    public SubjectSpaceResponse(List<Node> nodes, List<Link> links) {
        this.categories = generateCategories();
        this.nodes = nodes;
        this.links = links;
    }

    public List<Category1> getCategories() {
        return categories;
    }

    public void setCategories(List<Category1> categories) {
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
}

