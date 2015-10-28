/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 QAware GmbH, Munich, Germany
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.qaware.campus.secpro.web.hello;

import de.qaware.campus.secpro.web.security.Sanitized;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static de.qaware.campus.secpro.web.security.Sanitized.Type.ECMA_SCRIPT;

/**
 * A simple Greeting gate keeper component.
 *
 * @author mario-leander.reimer
 */
@SessionScoped
public class GreetingGateKeeper implements Serializable {

    @Inject
    private Greeting greeting;

    private Collection<String> names = new ArrayList<>();

    /**
     * Store the given name.
     *
     * @param name a name
     */
    @Sanitized(type = ECMA_SCRIPT)
    public String getMessage(String name) {
        names.add(name);
        return greeting.getMessage(name);
    }

    /**
     * Returns the list of names.
     *
     * @return all the names
     */
    public Collection<String> getNames() {
        return Collections.unmodifiableCollection(names);
    }
}
