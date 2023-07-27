package com.dhu.aml.service;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.exceptions.ServiceUnavailableException;
import org.neo4j.driver.types.Node;
import org.springframework.stereotype.Service;
import scala.Int;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Neo4jSubjectSpaceService {

    private final Driver driver;

    // 在构造函数中初始化Neo4j驱动
    public Neo4jSubjectSpaceService() {
        // 替换以下参数为您的Neo4j数据库连接信息
        String uri = "bolt://localhost:7687";
        String username = "neo4j";
        String password = "neo4j";
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    // 从Neo4j数据库中获取节点数据
    public List<Map<String, Object>> getNodes() {
        try (Session session = driver.session()) {
            String query = "MATCH (n) RETURN n";
            Result result = session.run(query);
            List<Map<String, Object>> nodes = new ArrayList<>();
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("n").asNode();
                Map<String, Object> nodeProperties = new HashMap<>();
                nodeProperties.put("id", node.id());
                nodeProperties.put("name", node.get("name").asString());
                nodeProperties.put("category", node.get("category")); // 请根据实际节点类型进行分类
                nodeProperties.put("index", node.get("index"));

                if(Integer.parseInt(node.get("category").toString()) == 0 || Integer.parseInt(node.get("category").toString()) == 1) {
                    nodes.add(nodeProperties);
                }
            }
            System.out.println(nodes.size()+"-----------");
            return nodes;
        } catch (ServiceUnavailableException e) {
            // 处理连接异常
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // 从Neo4j数据库中获取链接数据
    public List<Map<String, Object>> getLinks() {
        try (Session session = driver.session()) {
            String query = "MATCH (source)-[r]->(target) RETURN source.name as source, target.name as target";
            Result result = session.run(query);
            List<Map<String, Object>> links = new ArrayList<>();
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> linkProperties = new HashMap<>();
                linkProperties.put("id", links.size());
                linkProperties.put("source", record.get("source").asString());
                linkProperties.put("target", record.get("target").asString());
                links.add(linkProperties);
            }
            return links;
        } catch (ServiceUnavailableException e) {
            // 处理连接异常
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
