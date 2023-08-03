package com.dhu.aml.service;

import com.dhu.aml.entity.UserNode;
import com.dhu.aml.entity.UserRelation;
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
public class Neo4jBehaviorSpaceService {

    private static Driver driver = null;

    // 在构造函数中初始化Neo4j驱动
    public Neo4jBehaviorSpaceService() {
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
                nodeProperties.put("identity", node.id());
                nodeProperties.put("name", node.get("name").asString());
                nodeProperties.put("cate", node.get("cate")); // 请根据实际节点类型进行分类
                nodeProperties.put("index", node.get("index"));
                //nodeProperties.put("identity", node.get("identity"));

                if(Integer.parseInt(node.get("cate").toString()) == 2 || Integer.parseInt(node.get("cate").toString()) == 1) {
                    nodes.add(nodeProperties);
                }
            }
            // System.out.println(nodes.size()+"-----------");
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
            String query = "MATCH (source)-[r]->(target) RETURN ID(r) as id, source.cate as sourceCate, source.index as sourceIndex, target.cate as targetCate, target.index as targetIndex";
            Result result = session.run(query);
            List<Map<String, Object>> links = new ArrayList<>();
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> linkProperties = new HashMap<>();
                linkProperties.put("id", record.get("id").asLong());
                linkProperties.put("sourceCate", record.get("sourceCate"));
                linkProperties.put("sourceIndex", record.get("sourceIndex"));
                linkProperties.put("targetCate", record.get("targetCate"));
                linkProperties.put("targetIndex", record.get("targetIndex"));
                links.add(linkProperties);
            }
            return links;
        } catch (ServiceUnavailableException e) {
            // 处理连接异常
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // 从Neo4j数据库中获取与指定名称相关的节点和关联数据
    public static Map<String, List<Map<String, Object>>> getNodesAndLinksByName(String name) {
        try (Session session = driver.session()) {
            String query = "MATCH (n {name: $name})-[*]-(related) RETURN n, related";
            Result result = session.run(query, Values.parameters("name", name));

            List<Map<String, Object>> nodes = new ArrayList<>();
            List<Map<String, Object>> links = new ArrayList<>();

            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("n").asNode();
                Map<String, Object> nodeProperties = new HashMap<>();
                nodeProperties.put("identity", node.id());
                nodeProperties.put("name", node.get("name").asString());
                nodeProperties.put("cate", node.get("cate")); // 请根据实际节点类型进行分类
                nodeProperties.put("index", node.get("index"));

                nodes.add(nodeProperties);

                Node relatedNode = record.get("related").asNode();
                Map<String, Object> relatedNodeProperties = new HashMap<>();
                relatedNodeProperties.put("identity", relatedNode.id());
                relatedNodeProperties.put("name", relatedNode.get("name").asString());
                relatedNodeProperties.put("cate", relatedNode.get("cate")); // 请根据实际节点类型进行分类
                relatedNodeProperties.put("index", relatedNode.get("index"));

                nodes.add(relatedNodeProperties);

                Map<String, Object> linkProperties = new HashMap<>();
                linkProperties.put("source", node.id());
                linkProperties.put("target", relatedNode.id());
                // Add any other properties related to the link if needed

                links.add(linkProperties);
            }

            Map<String, List<Map<String, Object>>> resultData = new HashMap<>();
            resultData.put("nodes", nodes);
            resultData.put("links", links);
            return resultData;
        } catch (ServiceUnavailableException e) {
            // 处理连接异常
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
