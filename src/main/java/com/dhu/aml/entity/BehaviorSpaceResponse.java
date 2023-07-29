package com.dhu.aml.entity;

import com.dhu.aml.entity.categories.Category;
import com.dhu.aml.entity.categories.ItemStyle;

import java.util.ArrayList;
import java.util.List;

public class BehaviorSpaceResponse {
    private List<Category> categories;
    private List<UserNode> nodes;
    private List<UserRelation> links;

    public BehaviorSpaceResponse() {
    }

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