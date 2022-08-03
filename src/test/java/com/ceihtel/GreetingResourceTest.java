package com.ceihtel;

import io.quarkus.test.junit.DisabledOnIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

@QuarkusTest
class GreetingResourceTest {
    @InjectMock
    GreetingService greetingService;

    @DisabledOnIntegrationTest
    @Test
    void testHelloEndpoint() {
        when(greetingService.getGreeting()).thenReturn("Salut toi");

        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Salut toi"));

        verify(greetingService, times(1)).getGreeting();
        verifyNoMoreInteractions(greetingService);
    }

}