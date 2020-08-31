// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

module com.azure.spring.data.cosmos.demo {

    requires transitive com.azure.spring.data.cosmos;
    requires spring.boot;
    requires spring.boot.autoconfigure;

    opens com.azure.spring.data.cosmostutorial to
        com.fasterxml.jackson.databind,
        spring.aop,
        spring.boot,
        spring.beans,
        spring.context,
        spring.core,
        spring.data.commons
        ;
}