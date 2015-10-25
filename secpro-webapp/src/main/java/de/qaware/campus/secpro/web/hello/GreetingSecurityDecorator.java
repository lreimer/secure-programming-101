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

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.validation.constraints.Size;

/**
 * This decorator takes care of our Greeting security. In here you can
 * implement any required normalization and sanitization.
 *
 * @author mario-leander.reimer
 */
@Decorator
public class GreetingSecurityDecorator implements Greeting {

    @Inject
    @Delegate
    private Greeting greeter;

    @Inject
    private Validator validator;

    @Override
    public String getMessage(@Size(min = 3) String name) {
        // do some security checks, maybe even with the help
        // of the javax.validation.Validator instance for PJOs
        if ("attacker".equalsIgnoreCase(name)) {
            throw new SecurityException("No attackers permitted.");
        }

        // continue and delegate
        return greeter.getMessage(name);
    }
}
