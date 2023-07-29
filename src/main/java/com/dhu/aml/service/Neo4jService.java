package com.dhu.aml.service;

import com.dhu.aml.dao.UserRepository;
import com.dhu.aml.entity.UserNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class Neo4jService {

    private static UserRepository userRepository = null;

    @Autowired
    public Neo4jService(UserRepository yourNodeRepository) {
        userRepository = yourNodeRepository;
    }

    public static int getEntityCount() {
        return userRepository.getEntityCount();
    }

    public static int getActionCount() {
        return userRepository.getActionCount();
    }

    public static int getHyperActionCount() {
        return userRepository.getHyperActionCount();
    }
}
