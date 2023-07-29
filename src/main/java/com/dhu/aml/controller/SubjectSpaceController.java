package com.dhu.aml.controller;


import com.dhu.aml.entity.SubjectSpaceResponse;
import com.dhu.aml.entity.UserNode;
import com.dhu.aml.entity.UserRelation;
import com.dhu.aml.entity.categories.Category;
import com.dhu.aml.entity.categories.ItemStyle;
import com.dhu.aml.service.Neo4jService;
import com.dhu.aml.service.Neo4jSubjectSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.dhu.aml.service.Neo4jService.getActionCount;
import static com.dhu.aml.service.Neo4jService.getEntityCount;

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
        categories.add(new Category(0, "实体","circle", new ItemStyle("#E99D42","#E99D42")));
        categories.add(new Category(1, "行为", "circle", new ItemStyle("#4095E5", "#4095E5")));
        categories.add(new Category(2, "超行为", "rect", new ItemStyle("#fff", "#81B337")));


        // 构建Node列表
        for (Map<String, Object> nodeFromDb : nodesFromDb) {
            Long identity = (Long) nodeFromDb.get("identity");
            String name = (String) nodeFromDb.get("name");
            String cate = nodeFromDb.get("cate").toString();
            String index = nodeFromDb.get("index").toString();
            int id = Integer.parseInt(index);
            int category = Integer.parseInt(cate);
            //Long identity = (Long) nodeFromDb.get("identity");
            if(Objects.equals(cate, "0"))
            {
                nodes.add(new UserNode(identity, name, category, index, id));
            }
            // System.out.println("category:"+category);
        }

        for (Map<String, Object> nodeFromDb : nodesFromDb) {
            Long identity = (Long) nodeFromDb.get("identity");
            String name = (String) nodeFromDb.get("name");
            String cate = nodeFromDb.get("cate").toString();
            String index = nodeFromDb.get("index").toString();
            int id = Integer.parseInt(index) + getEntityCount();
            int category = Integer.parseInt(cate);
            //Long identity = (Long) nodeFromDb.get("identity");
            if(Objects.equals(cate, "1"))
            {
                nodes.add(new UserNode(identity, name, category, index, id));
            }
        }

            // 构建Link列表
            for (Map<String, Object> linkFromDb : linksFromDb) {
                Long id = Long.parseLong(linkFromDb.get("id").toString());
                int sourceCate = Integer.parseInt(linkFromDb.get("sourceCate").toString());
                int targetCate = Integer.parseInt(linkFromDb.get("targetCate").toString());

                int sourceIndex = Integer.parseInt(linkFromDb.get("sourceIndex").toString());
                int targetIndex = Integer.parseInt(linkFromDb.get("targetIndex").toString());

                if (sourceCate != 2 && targetCate != 2)
                {
                    if(sourceCate == 0)
                    {
                        int source = sourceIndex;
                        int target = getEntityCount() + targetIndex;
                        links.add(new UserRelation(id, source, target));
                    }

                    else if(sourceCate == 1)
                    {
                        int source = getEntityCount() + sourceIndex;
                        int target = targetIndex;
                        links.add(new UserRelation(id, source, target));
                    }
                }
            }

            // 设置响应数据
            response.setCategories(categories);
            response.setNodes(nodes);
            response.setLinks(links);

            // System.out.println(nodes.size() + "size");

            return response;
        }
    }