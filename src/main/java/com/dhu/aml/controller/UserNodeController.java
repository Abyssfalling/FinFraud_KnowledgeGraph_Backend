package com.dhu.aml.controller;

import com.dhu.aml.entity.UserNode;
import com.dhu.aml.service.Neo4jBehaviorSpaceService;
import com.dhu.aml.service.Neo4jService;
import com.dhu.aml.service.Neo4jSubjectSpaceService;
import com.dhu.aml.service.UserNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class UserNodeController {

    private final UserNodeService userNodeService;

    @Autowired
    public UserNodeController(UserNodeService userNodeService) {
        this.userNodeService = userNodeService;
    }

    @PostMapping("/addNodesAndRelations")
    public ResponseEntity<String> addNodesAndRelations(
            @RequestParam String actionName,
            @RequestParam String subjectName,
            @RequestParam String objectName,
            @RequestParam String sourceName,
            @RequestParam String targetName
    ) {
        boolean success = userNodeService.addNodesAndRelations(actionName, subjectName, objectName, sourceName, targetName);
        if (success) {
            return ResponseEntity.ok("Nodes and relations added successfully!");
        } else {
            return ResponseEntity.badRequest().body("Failed to add nodes and relations. Check if source and target nodes exist.");
        }
    }

    @PutMapping("/updateNodeName")
    public ResponseEntity<String> updateNodeName(
            @RequestParam String name,
            @RequestParam String newName
    ) {
        boolean success = userNodeService.updateNodeName(name, newName);
        if (success) {
            return ResponseEntity.ok("Node name updated successfully!");
        } else {
            return ResponseEntity.badRequest().body("Failed to update node name. Node not found or duplicate node name exists.");
        }
    }

    @DeleteMapping("/deleteNodeAndRelations")
    public ResponseEntity<String> deleteNodeAndRelations(@RequestParam String name) {
        boolean success = userNodeService.deleteNodeAndRelations(name);
        if (success) {
            return ResponseEntity.ok("Node and its relations deleted successfully!");
        } else {
            return ResponseEntity.badRequest().body("Node not found. Nothing to delete.");
        }
    }

    // 接口：查找与指定名称相关的节点和关联数据
    @GetMapping("/getNodesAndLinksByName")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getNodesAndLinksByName(@RequestParam String name) {
        // 假设节点类型为1的为行为节点，节点类型为0的为实体节点
        Map<String, List<Map<String, Object>>> resultData = new HashMap<>();
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> links = new ArrayList<>();

        // 从行为空间中获取与指定名称相关的节点和关联数据
        Map<String, List<Map<String, Object>>> behaviorSpaceResult = Neo4jBehaviorSpaceService.getNodesAndLinksByName(name);
        nodes.addAll(behaviorSpaceResult.get("nodes"));
        links.addAll(behaviorSpaceResult.get("links"));

        // 从实体空间中获取与指定名称相关的节点和关联数据
        Map<String, List<Map<String, Object>>> subjectSpaceResult = Neo4jSubjectSpaceService.getNodesAndLinksByName(name);
        nodes.addAll(subjectSpaceResult.get("nodes"));
        links.addAll(subjectSpaceResult.get("links"));

        resultData.put("nodes", nodes);
        resultData.put("links", links);

        return ResponseEntity.ok(resultData);
    }
}