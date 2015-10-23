package de.qaware.campus.secpro.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A Greeting component interface definition.
 *
 * @author mario-leander.reimer
 */
public interface Greeting {
    /**
     * Returns a Greeting for the given name. The actual greeting
     * may depend on implementation.
     *
     * @param name the name, at least 3 characters
     * @return the greeting message
     */
    @NotNull
    String getMessage(@Size(min = 3) String name);
}
