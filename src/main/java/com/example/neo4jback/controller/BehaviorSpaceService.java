package com.example.neo4jback.controller;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.exceptions.ServiceUnavailableException;
import org.neo4j.driver.types.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BehaviorSpaceService {

    private final Driver driver;

    // 在构造函数中初始化Neo4j驱动
    public BehaviorSpaceService() {
        // 替换以下参数为您的Neo4j数据库连接信息
        String uri = "bolt://localhost:7687";
        String username = "neo4j";
        String password = "your_password";
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    // 从Neo4j数据库中获取行为和超行为节点数据
    public List<Map<String, Object>> getNodes() {
        try (Session session = driver.session()) {
            String query = "MATCH (n:Action) OR (n:HyperAction) RETURN n";
            Result result = session.run(query);
            List<Map<String, Object>> nodes = new ArrayList<>();
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("n").asNode();
                Map<String, Object> nodeProperties = new HashMap<>();
                nodeProperties.put("id", node.id());
                nodeProperties.put("name", node.get("name").asString());
                nodeProperties.put("category", getCategoryForNode(node));
                nodeProperties.put("identity", node.get("identity").asInt());
                nodes.add(nodeProperties);
            }
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
            String query = "MATCH (source)-[r:LINKED_TO]->(target) RETURN source.name as source, target.name as target";
            Result result = session.run(query);
            List<Map<String, Object>> links = new ArrayList<>();
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> linkProperties = new HashMap<>();
                linkProperties.put("id", links.size());
                linkProperties.put("source", record.get("source").asString());
                linkProperties.put("target", record.get("target").asString());
                linkProperties.put("lineStyle", getLineStyleForLink(record.get("source").asString()));
                links.add(linkProperties);
            }
            return links;
        } catch (ServiceUnavailableException e) {
            // 处理连接异常
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // 根据节点类型获取分类
    private int getCategoryForNode(Node node) {
        String label = node.labels().iterator().next();
        // 根据节点的标签（类型）进行分类，并返回对应的分类编号
        if (label.equals("Action")) {
            return 0;
        } else if (label.equals("HyperAction")) {
            return 1;
        } else {
            return -1; // 如果有其他类型的节点，可以根据实际情况添加更多分类
        }
    }

    // 根据链接获取线条样式
    private Map<String, Object> getLineStyleForLink(String sourceNodeName) {
        // 在此处根据实际情况设置边的线条样式
        // 您可以根据sourceNodeName来确定链接的颜色等样式
        Map<String, Object> lineStyle = new HashMap<>();
        lineStyle.put("color", "#4095E5"); // 示例中的颜色值，请根据实际需求设置
        return lineStyle;
    }
}
