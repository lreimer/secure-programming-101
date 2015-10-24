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
package de.qaware.campus.secpro.web.passwords;

import javax.enterprise.inject.Alternative;

/**
 * A simple utility class to obtain the master password.
 * <p>
 * The master password is stored in a glassfish password alias, and
 * passed in via a standard Java system property.
 * <p>
 * -Dde.qaware.campus.secpro.password=${ALIAS=secpro_password_alias}
 * <p>
 * You need to create the password alias using asadmin create-password-alias
 * command for your Glassfish domain.
 *
 * @author mario-leander.reimer
 */
@Alternative
public final class MasterPassword {
    /**
     * Returns the master password instance.
     */
    public static final MasterPassword INSTANCE = new MasterPassword();

    /**
     * No instantiation.
     */
    private MasterPassword() {
    }

    @Override
    public String toString() {
        return System.getProperty("de.qaware.campus.secpro.password");
    }
}
