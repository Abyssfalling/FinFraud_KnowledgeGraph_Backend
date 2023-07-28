package com.dhu.aml.service;

import com.dhu.aml.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Neo4jService {

    private static UserRepository UserRepository = null;

    @Autowired
    public Neo4jService(UserRepository yourNodeRepository) {
        UserRepository = yourNodeRepository;
    }

    public static Long getEntityCount() {
        return UserRepository.getEntityCount();
    }

    public static Long getActionCount() {
        return UserRepository.getActionCount();
    }
}
