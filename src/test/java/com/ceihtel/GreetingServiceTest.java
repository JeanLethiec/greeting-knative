package com.ceihtel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingServiceTest {
    @Test
    void getGreetingOk() {
        assertEquals("Hello Quarkus", new GreetingService("Quarkus").getGreeting());
    }
}
