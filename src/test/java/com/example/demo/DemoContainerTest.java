package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoContainerTest {
    @Autowired
    TestRestTemplate testTemplate;
    @Container
    private static final GenericContainer<?> devApp = new GenericContainer<>("devprod:1.0").withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:2.0").withExposedPorts(8081);

    @BeforeAll
    public static void setUp(){
        devApp.start();
        prodApp.start();
    }

    @Test
    void devTest(){
        Integer devPort = devApp.getMappedPort(8080);

        ResponseEntity<String> entityFromDev = testTemplate.getForEntity("http://localhost:" + devPort + "/profile", String.class);

        Assertions.assertEquals("Current profile is dev", entityFromDev.getBody());
    }

    @Test
    void prodTest(){
        Integer prodPort = prodApp.getMappedPort(8081);

        ResponseEntity<String> entityFormProd = testTemplate.getForEntity("http://localhost:" + prodPort + "/profile", String.class);

        Assertions.assertEquals("Current profile is production", entityFormProd.getBody());
    }
}
