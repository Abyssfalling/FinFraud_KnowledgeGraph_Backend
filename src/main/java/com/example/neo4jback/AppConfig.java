package com.example.neo4jback;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// AppConfig.java
@Configuration
@ComponentScan("com.example.neo4jback.controller") // Replace with the actual package containing SubjectSpaceController
public class AppConfig {
    // Additional configuration if needed
}
