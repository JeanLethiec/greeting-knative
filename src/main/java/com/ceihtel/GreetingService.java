package com.ceihtel;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    private final String greeting;

    public GreetingService(@ConfigProperty(name = "greeting.name") String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return "Hello " + greeting;
    }
}
