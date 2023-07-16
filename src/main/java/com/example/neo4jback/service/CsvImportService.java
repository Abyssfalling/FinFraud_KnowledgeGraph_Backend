package com.example.neo4jback.service;

import org.apache.commons.csv.*;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CsvImportService {

    private final Driver neo4jDriver;

    @Autowired
    public CsvImportService(Driver neo4jDriver) {
        this.neo4jDriver = neo4jDriver;
    }

    public void importCsvToNeo4j(String filePath) {
        try {
            // 读取CSV文件
            Path path = Paths.get(filePath);
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(Files.newBufferedReader(path));

            // 创建Neo4j会话
            try (Session session = neo4jDriver.session()) {
                // 创建索引以加快实体合并和查询速度
                session.run("CREATE INDEX ON :Entity(name)");
                session.run("CREATE INDEX ON :Action(name)");

                // 遍历CSV文件中的每一行
                for (CSVRecord record : csvParser) {
                    String action = record.get("action");
                    String subject = record.get("subject");
                    String object = record.get("object");

                    if (!subject.equalsIgnoreCase("none") && !object.equalsIgnoreCase("none")) {
                        // 创建或合并实体节点
                        String mergeSubjectQuery = "MERGE (s:Entity {name: $subject})";
                        String mergeObjectQuery = "MERGE (o:Entity {name: $object})";
                        session.run(mergeSubjectQuery, Values.parameters("subject", subject));
                        session.run(mergeObjectQuery, Values.parameters("object", object));

                        // 创建或合并动作节点
                        String mergeActionQuery = "MERGE (a:Action {name: $action})";
                        session.run(mergeActionQuery, Values.parameters("action", action));

                        // 创建关系
                        String createRelationshipQuery = """
                            MATCH (a:Action {name: $action}), (s:Entity {name: $subject}), (o:Entity {name: $object})
                            WHERE NOT (a)-[:属于]->(s) AND NOT (a)-[:属于]->(o)
                            CREATE (a)-[:属于]->(s), (a)-[:属于]->(o)
                        """;
                        session.run(createRelationshipQuery, Values.parameters("action", action, "subject", subject, "object", object));
                    }
                }
            }

            // 关闭CSV解析器
            csvParser.close();
        } catch (IOException e) {
            // 处理IO异常
            e.printStackTrace();
        }
    }
}
