package com.dhu.aml.controller;


import com.dhu.aml.entity.SubjectSpaceResponse;
import com.dhu.aml.entity.UserNode;
import com.dhu.aml.entity.UserRelation;
import com.dhu.aml.entity.categories.Category;
import com.dhu.aml.entity.categories.ItemStyle;
import com.dhu.aml.service.Neo4jSubjectSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SubjectSpaceController {

    @Autowired
    private Neo4jSubjectSpaceService neo4jService;

    @PostMapping("/reqSubjectSpace")
    public Object getSubjectSpace() {
        // 从Neo4j数据库读取数据
        List<Map<String, Object>> nodesFromDb = neo4jService.getNodes();
        List<Map<String, Object>> linksFromDb = neo4jService.getLinks();

        // 构建返回的JSON格式数据
        SubjectSpaceResponse response = new SubjectSpaceResponse();
        List<Category> categories = new ArrayList<>();
        List<UserNode> nodes = new ArrayList<>();
        List<UserRelation> links = new ArrayList<>();

        // 构建Category列表
        categories.add(new Category(0, "实体", "", new ItemStyle("#E99D42","")));
        categories.add(new Category(1, "行为", "circle", new ItemStyle("#4095E5","4095E5")));
        categories.add(new Category(2, "超行为", "rect", new ItemStyle("#fff","81B337")));


        // 构建Node列表
        for (Map<String, Object> nodeFromDb : nodesFromDb) {
            Long id = (Long) nodeFromDb.get("id");
            String name = (String) nodeFromDb.get("name");
            String category = nodeFromDb.get("category").toString();
            String index = nodeFromDb.get("index").toString();
            nodes.add(new UserNode(id, name, category, index));
            //System.out.println("category:"+category);
        }

        // 构建Link列表
        for (Map<String, Object> linkFromDb : linksFromDb) {
            Long id = Long.parseLong(linkFromDb.get("id").toString());
            String source = (String) linkFromDb.get("source");
            UserNode sourceNode = new UserNode();
            sourceNode.setName(source);
            String target = (String) linkFromDb.get("target");
            UserNode targetNode = new UserNode();
            targetNode.setName(target);
            if(!source.contains("超行为") && !target.contains("超行为"))
                links.add(new UserRelation(id, sourceNode, targetNode));
        }

        // 设置响应数据
        response.setCategories(categories);
        response.setNodes(nodes);
        response.setLinks(links);

        System.out.println(nodes.size()+"size");

        return response;
    }
}
