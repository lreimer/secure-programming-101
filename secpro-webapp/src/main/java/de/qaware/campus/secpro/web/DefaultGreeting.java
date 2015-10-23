package de.qaware.campus.secpro.web;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static java.lang.String.format;

/**
 * The default Hello world greeting implementation. We already use
 * JEE7 / BeanValidation 1.1 method validation here.
 *
 * @author mario-leander.reimer
 */
@ApplicationScoped
public class DefaultGreeting implements Greeting {
    @Override
    @NotNull
    public String getMessage(@Size(min = 3) String name) {
        return format("Hello %s!", name);
    }
}
