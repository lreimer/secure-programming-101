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

package de.qaware.campus.secpro.mlr01;

import java.util.Arrays;

/**
 * An obfuscation utility class for char[] and Strings.
 *
 * @author mario-leander.reimer
 */
public final class Obfuscator {
    /**
     * Private utility class constructor.
     */
    private Obfuscator() {
    }

    /**
     * Obfuscate the given char[] and return it. The char[] will be nullified.
     *
     * @param value the char[]
     * @return the value
     */
    public static char[] obfuscate(char[] value) {
        Arrays.fill(value, '\0');
        return value;
    }

    /**
     * Obfuscate the given String and return it. Uses SecureString.
     *
     * @param value the String value
     * @return the value
     */
    public static String obfuscate(String value) {
        SecureString secureString = SecureString.from(value);
        secureString.obfuscate();
        return secureString.getValue();
    }
}
