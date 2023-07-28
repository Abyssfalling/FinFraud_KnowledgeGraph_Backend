package com.dhu.aml.service;

import com.dhu.aml.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Neo4jService {

    private static UserRepository userRepository = null;

    @Autowired
    public Neo4jService(UserRepository userRepository) {
        Neo4jService.userRepository = userRepository;
    }

    public static Long getEntityCount() {
        return userRepository.getEntityCount();
    }

    public static Long getActionCount() {
        return userRepository.getActionCount();
    }
}
