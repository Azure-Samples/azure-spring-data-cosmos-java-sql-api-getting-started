// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.spring.data.cosmostutorial;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends CosmosRepository<User, String> {

    Iterable<User> findByFirstName(String firstName);

    User findByIdAndLastName(String id, String lastName);

    @Query("select c.firstName from c where c.id = @id")
    ArrayList<JsonNode> findFirstNameById(@Param("id") String id);
}
