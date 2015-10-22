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

package de.qaware.campus.secpro.msc03;

/**
 * A very insecure Login component. Uses hardcoded string constants
 * as username and password. The values to check are stored as string
 * fields. This class is highly insecure. Using javap you can get
 * hold of the string constants. Via heap dumps, you can get hold of
 * the other values.
 *
 * @author mario-leander.reimer
 */
public class InsecureLogin {

    private static final String USERNAME = "TheDude";
    private static final String PASSWORD = "BigLebowski";

    private final String username;
    private final String password;

    /**
     * Initialize with username and password to check as strings.
     *
     * @param username the username
     * @param password the password
     */
    public InsecureLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Check if authenticated.
     *
     * @return true if credentials OK, otherwise false
     */
    public boolean authenticated() {
        return USERNAME.equals(username)
                && PASSWORD.equals(password);
    }
}

