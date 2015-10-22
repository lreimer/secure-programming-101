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

import java.lang.reflect.Field;

/**
 * A secure string wrapper that allows to obfuscate the given string
 * so that even with heap dumps you can not get hold of it.
 *
 * @author mario-leander.reimer
 */
public final class SecureString {

    private final String value;

    private SecureString(String value) {
        this.value = value;
    }

    /**
     * Factory method for SecureString instances.
     *
     * @param value the value
     * @return a SecureString instance
     */
    public static SecureString from(String value) {
        return new SecureString(value);
    }

    /**
     * Obfuscate the string value. After this the string is secure and can not
     * be recovered.
     *
     * @throws SecurityException when the string value can not be obfuscated
     */
    public void obfuscate() throws SecurityException {
        String fake = value.replaceAll(".", "?");

        // this will also overwrite the memory, only seems to work under 32bit
        // Unsafe unsafe = getInstance();
        // unsafe.copyMemory(fake, 0L, (Object) null, toAddress(value), sizeOf(value));

        try {
            Field stringValue = String.class.getDeclaredField("value");
            stringValue.setAccessible(true);
            char[] mem = (char[]) stringValue.get(value);
            for (int i = 0; i < mem.length; i++) {
                mem[i] = '?';
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new SecurityException("Unable to obfuscate SecureString.");
        }
    }

    /**
     * Returns the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
