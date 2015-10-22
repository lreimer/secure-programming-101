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

package de.qaware.campus.secpro.env01;

import java.util.Arrays;

/**
 * A little more secure Login component. Uses char[] to store hardcoded
 * username and password. Still not perfect!
 *
 * @author mario-leander.reimer
 */
public class DefaultDudeLogin implements Login {

    private static final char[] USERNAME = {'T', 'h', 'e', 'D', 'u', 'd', 'e'};
    private static final char[] PASSWORD = {'B', 'i', 'g', 'L', 'e', 'b', 'o', 'w', 's', 'k', 'i'};

    @Override
    public boolean authenticated(String username, char[] password) {
        return Arrays.equals(USERNAME, username.toCharArray()) &&
                Arrays.equals(PASSWORD, password);
    }
}
