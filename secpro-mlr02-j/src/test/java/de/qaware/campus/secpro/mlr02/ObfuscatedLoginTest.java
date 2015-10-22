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

package de.qaware.campus.secpro.mlr02;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test case for the ObfuscatedLogin authentication behaviour.
 *
 * @author mario-leander.reimer
 */
public class ObfuscatedLoginTest {

    private static final char[] PASSWORD = {'B', 'i', 'g', 'L', 'e', 'b', 'o', 'w', 's', 'k', 'i'};

    @Test
    public void testAuthenticated() throws Exception {
        ObfuscatedLogin login = new ObfuscatedLogin();
        boolean authenticated = login.authenticated("TheDude", PASSWORD);
        assertThat(authenticated, is(true));
    }

    @Test
    public void testNotAuthenticated() throws Exception {
        ObfuscatedLogin login = new ObfuscatedLogin();
        boolean authenticated = login.authenticated("Unknown", new char[]{'t', 'e', 's', 't'});
        assertThat(authenticated, is(false));
    }

    @Test
    public void testAuthenticatedHash() {
        int hash = "TheDude:BigLebowski".hashCode();
        assertThat(hash, is(-660170103));
    }
}