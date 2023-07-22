package com.example.neo4jback.model.Space;

import com.example.neo4jback.model.Link;
import com.example.neo4jback.model.Node;
import com.example.neo4jback.model.categories.Category2;
import com.example.neo4jback.model.categories.ItemStyle2;

import java.util.ArrayList;
import java.util.List;

public class BehaviorSpaceResponse {
    private List<Category2> categories;
    private List<Node> nodes;
    private List<Link> links;

    public BehaviorSpaceResponse() {
    }

    public static  List<Category2> generateCategories() {
        List<Category2> categories = new ArrayList<>();

        // 第一个Category
        Category2 category1 = new Category2();
        category1.setId(1);
        category1.setName("行为");
        category1.setSymbol("circle");
        ItemStyle2 itemStyle1 = new ItemStyle2();
        itemStyle1.setColor("#4095E5");
        itemStyle1.setBorderColor("#4095E5");
        category1.setItemStyle(itemStyle1);
        categories.add(category1);

        // 第二个Category
        Category2 category2 = new Category2();
        category2.setId(2);
        category2.setName("超行为");
        category2.setSymbol("rect");
        ItemStyle2 itemStyle2 = new ItemStyle2();
        itemStyle2.setColor("#fff");
        itemStyle2.setBorderColor("#81B337");
        category2.setItemStyle(itemStyle2);
        categories.add(category2);

        return categories;
    }


    public BehaviorSpaceResponse(List<Node> nodes, List<Link> links) {
        this.categories = generateCategories();
        this.nodes = nodes;
        this.links = links;
    }

    public List<Category2> getCategories() {
        return categories;
    }

    public void setCategories(List<Category2> categories) {
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

