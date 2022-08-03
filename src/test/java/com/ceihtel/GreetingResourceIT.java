package com.ceihtel;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusIntegrationTest
class GreetingResourceIT extends GreetingResourceTest {

    // Execute the same tests but in native mode.

    @Test
    void testHelloEndpointNative() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello Haroun"));
    }
}
