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

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * A interceptor binding annotation for methods to be sanitized.
 *
 * @author mario-leander.reimer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD})
@InterceptorBinding
public @interface Sanitized {
    /**
     * Specific types with their sanitization functions.
     */
    enum Type implements Function<Object, Object> {
        ECMA_SCRIPT {
            @Override
            public Object apply(Object o) {
                if (o instanceof String) {
                    return StringEscapeUtils.ESCAPE_ECMASCRIPT.translate(o.toString());
                }
                return o;
            }
        }, SQL {
            @Override
            public Object apply(Object o) {
                if (o instanceof String) {
                    // TODO: this does not handle the cases of percent (%) or underscore (_) for use in LIKE clauses.
                    return StringUtils.replace(o.toString(), "'", "''");
                }
                return o;
            }
        }
    }

    /**
     * Pass additional information to the interceptor.
     */
    @Nonbinding Type type() default Type.ECMA_SCRIPT;
}
