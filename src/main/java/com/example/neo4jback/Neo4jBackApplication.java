package com.example.neo4jback;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Neo4jBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jBackApplication.class, args);
    }

    @Bean
    public Driver neo4jDriver() {
        // 替换为实际的数据库地址、用户名和密码
        String uri = "bolt://localhost:7687";
        String username = "neo4j";
        String password = "neo4j";

        // 创建Neo4j驱动程序
        return GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }
}
