package com.dhu.aml.service;

import com.dhu.aml.dao.UserNodeRepository;
import com.dhu.aml.dao.UserRelationRepository;
import com.dhu.aml.entity.UserNode;
import com.dhu.aml.entity.UserRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.dhu.aml.service.Neo4jService.getHyperActionCount;

@Service
public class UserNodeService {

    private final UserNodeRepository userNodeRepository;
    private final UserRelationRepository userRelationRepository;

    @Autowired
    public UserNodeService(UserNodeRepository userNodeRepository, UserRelationRepository userRelationRepository) {
        this.userNodeRepository = userNodeRepository;
        this.userRelationRepository = userRelationRepository;
    }

    public boolean addNodesAndRelations(String actionName, String subjectName, String objectName, String sourceName, String targetName) {
        // Check if Action node exists
        UserNode actionNode = userNodeRepository.findByNameAndCate(actionName, 1);
        if (actionNode == null) {
            actionNode = new UserNode(actionName, 1);
            userNodeRepository.save(actionNode);
        }

        // Check if Subject node exists
        UserNode subjectNode = userNodeRepository.findByNameAndCate(subjectName, 0);
        if (subjectNode == null) {
            subjectNode = new UserNode(subjectName, 0);
            userNodeRepository.save(subjectNode);
        }

        // Check if Object node exists
        UserNode objectNode = userNodeRepository.findByNameAndCate(objectName, 0);
        if (objectNode == null) {
            objectNode = new UserNode(objectName, 0);
            userNodeRepository.save(objectNode);
        }

        // Check if Source and Target nodes exist
        UserNode sourceNode = userNodeRepository.findByName(sourceName);
        UserNode targetNode = userNodeRepository.findByName(targetName);
        if (sourceNode == null || targetNode == null) {
            return false;
        }

        // Create HyperAction nodes
        UserNode hyperActionX1 = new UserNode();
        int X1 = 1 + getHyperActionCount();
        int X2 = 2 + getHyperActionCount();
        hyperActionX1.setName(Integer.toString(X1));
        hyperActionX1.setCate("2");
        userNodeRepository.save(hyperActionX1);

        UserNode hyperActionX2 = new UserNode();
        hyperActionX2.setName(Integer.toString(X2));
        hyperActionX2.setCate("2");
        userNodeRepository.save(hyperActionX2);

        // Create UserRelation links
        UserRelation relation1 = new UserRelation();
        relation1.setSourceNode(sourceNode);
        relation1.setTargetNode(hyperActionX1);
        userRelationRepository.save(relation1);

        UserRelation relation2 = new UserRelation();
        relation2.setSourceNode(hyperActionX1);
        relation2.setTargetNode(sourceNode);
        userRelationRepository.save(relation2);

        UserRelation relation3 = new UserRelation();
        relation3.setSourceNode(sourceNode);
        relation3.setTargetNode(hyperActionX2);
        userRelationRepository.save(relation3);

        UserRelation relation4 = new UserRelation();
        relation4.setSourceNode(hyperActionX2);
        relation4.setTargetNode(targetNode);
        userRelationRepository.save(relation4);

        return true;
    }

    public boolean updateNodeName(String name, String newName) {
        UserNode node = userNodeRepository.findByName(name);
        if (node != null) {
            UserNode duplicateNode = userNodeRepository.findByName(newName);
            if (duplicateNode == null) {
                node.setName(newName);
                userNodeRepository.save(node);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNodeAndRelations(String name) {
        UserNode node = userNodeRepository.findByName(name);
        if (node != null) {
            Long nodeId = (long) node.getId();

            // Delete the node
            userNodeRepository.delete(node);

            // Delete relationships where this node is the source or target
            List<UserRelation> relations = userRelationRepository.findBySourceNodeOrTargetNode(nodeId);
            userRelationRepository.deleteAll(relations);

            return true;
        }
        return false;
    }
}
