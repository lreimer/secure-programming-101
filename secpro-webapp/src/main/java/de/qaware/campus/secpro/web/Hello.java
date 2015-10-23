package de.qaware.campus.secpro.web;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 * A simple request scoped named Hello model. Actually the example
 * is taken from the official JavaEE 7 tutorial.
 *
 * @author mario-leander.reimer
 */
@Model
public class Hello {

    @Inject
    private Greeting greeting;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting.getMessage(name);
    }
}
