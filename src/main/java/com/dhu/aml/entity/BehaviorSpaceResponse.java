package com.dhu.aml.entity;

import com.dhu.aml.entity.categories.Category;

import java.util.List;

public class BehaviorSpaceResponse {
    private List<Category> categories;
    private List<UserNode> nodes;
    private List<UserRelation> links;

    public BehaviorSpaceResponse() {
    }

//    public static List<Category> generateCategories() {
//        List<Category> categories = new ArrayList<>();
//
//        // Create and add "实体" category
//        ItemStyle entityItemStyle = new ItemStyle("#4095E5","4095E5");
//        Category entityCategory1 = new Category(1, "行为","circle",entityItemStyle);
//        categories.add(entityCategory1);
//
//        // Create and add "行为" category
//        ItemStyle behaviorItemStyle = new ItemStyle("#fff","81B337");
//        Category behaviorCategory1 = new Category(2, "","rect",behaviorItemStyle);
//        categories.add(behaviorCategory1);
//
//        return categories;
//    }


    public BehaviorSpaceResponse(List<Category> categories, List<UserNode> nodes, List<UserRelation> links) {
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

    public List<UserNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<UserNode> nodes) {
        this.nodes = nodes;
    }

    public List<UserRelation> getLinks() {
        return links;
    }

    public void setLinks(List<UserRelation> links) {
        this.links = links;
    }
}