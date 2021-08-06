package com.hsbc.test


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(
        classes = HsbcTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class BaseTest extends Specification {

    @Autowired
    protected TestApiClient testApiClient

}
