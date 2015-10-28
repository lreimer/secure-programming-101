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
package de.qaware.campus.secpro.web.security;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * A simple security interceptor example. Not fully implemented, just
 * for demonstration purposes.
 *
 * @author mario-leander.reimer
 */
@Interceptor
@Sanitized
public class SanitizedInterceptor implements Serializable {

    @AroundInvoke
    public Object invoke(InvocationContext ctx) throws Exception {
        Sanitized sanitization = getSanitizedAnnotation(ctx.getMethod());

        // apply the sanitization function
        Object[] sanitized = Arrays.stream(ctx.getParameters()).map(sanitization.type()).toArray();
        ctx.setParameters(sanitized);

        return ctx.proceed();
    }

    private Sanitized getSanitizedAnnotation(Method m) {
        Sanitized annotation = m.getAnnotation(Sanitized.class);
        if (annotation != null) {
            return annotation;
        }

        annotation = m.getDeclaringClass().getAnnotation(Sanitized.class);
        if (annotation != null) {
            return annotation;
        }

        throw new IllegalStateException("Unable to find Sanitized annotation.");
    }
}
