// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.spring.data.cosmos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.Iterator;

@SpringBootApplication
public class SampleApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(SampleApplication.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReactiveUserRepository reactiveUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    public void run(String... var1) {

        final User testUser1 = new User("testId1", "testFirstName", "testLastName1");
        final User testUser2 = new User("testId2", "testFirstName", "testLastName2");

        logger.info("Using sync repository");

        userRepository.deleteAll();

        logger.info("Saving user : {}", testUser1);
        userRepository.save(testUser1);

        logger.info("Saving user : {}", testUser2);
        userRepository.save(testUser2);

        // to find by Id, please specify partition key value if collection is partitioned
        final User result = userRepository.findByIdAndLastName(testUser1.getId(), testUser1.getLastName());
        logger.info("Found user : {}", result);

        Iterator<User> usersIterator = userRepository.findByFirstName("testFirstName").iterator();

        logger.info("Users by firstName : testFirstName");
        while (usersIterator.hasNext()) {
            logger.info("user is : {}", usersIterator.next());
        }

        logger.info("Using reactive repository");

        Flux<User> users = reactiveUserRepository.findByFirstName("testFirstName");
        users.map(u -> {
            logger.info("user is : {}", u);
            return u;
        }).subscribe();
    }
}
