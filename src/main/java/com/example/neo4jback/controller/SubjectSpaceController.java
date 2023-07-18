package com.example.neo4jback.controller;

import com.example.neo4jback.model.*;
import com.example.neo4jback.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SubjectSpaceController {

    @Autowired
    private Neo4jService neo4jService;

    @GetMapping("/reqSubjectSpace")
    public SubjectSpaceResponse getSubjectSpace() {
        // 从Neo4j数据库读取数据
        List<Map<String, Object>> nodesFromDb = neo4jService.getNodes();
        List<Map<String, Object>> linksFromDb = neo4jService.getLinks();

        // 构建返回的JSON格式数据
        SubjectSpaceResponse response = new SubjectSpaceResponse();
        List<Category> categories = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        List<Link> links = new ArrayList<>();

        // 构建Category列表
        categories.add(new Category(0, "实体", new ItemStyle("#E99D42")));
        categories.add(new Category(1, "行为", new ItemStyle("#4095E5")));

        // 构建Node列表
        for (Map<String, Object> nodeFromDb : nodesFromDb) {
            int id = (int) nodeFromDb.get("id");
            String name = (String) nodeFromDb.get("name");
            int category = (int) nodeFromDb.get("category");
            int identity = (int) nodeFromDb.get("identity");
            nodes.add(new Node(id, name, category, identity));
        }

        // 构建Link列表
        for (Map<String, Object> linkFromDb : linksFromDb) {
            int id = (int) linkFromDb.get("id");
            String source = (String) linkFromDb.get("source");
            String target = (String) linkFromDb.get("target");
            links.add(new Link(id, source, target));
        }

        // 设置响应数据
        response.setCategories(categories);
        response.setNodes(nodes);
        response.setLinks(links);

        return response;
    }
}
