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

import java.io.Console;

/**
 * A simple login shell console application.
 *
 * @author mario-leander.reimer
 */
public final class UnmodifiableLoginApp {
    /**
     * Enter username and password on the console, authenticate and loop until exit.
     *
     * @param args no arguments
     */
    public static void main(String[] args) {
        Console console = System.console();

        CertificateVerifier verifier = new CertificateVerifier();
        verifier.verify(UnmodifiableLoginApp.class);

        String username = console.readLine("Enter username: ");
        char[] password = console.readPassword("Enter password: ");

        Login login = LoginFactory.getInstance();
        boolean authenticated = login.authenticated(username, password);

        if (!authenticated) {
            console.printf("Authentication failure. Exit.");
            System.exit(1);
        }

        String command = null;
        while (!"exit".equalsIgnoreCase(command)) {
            command = console.readLine("$ ");
            switch (command.toLowerCase()) {
                case "hello":
                    console.printf("world\n");
                    break;
                case "ping":
                    console.printf("pong\n");
                    break;
                case "exit":
                    break;
                case "":
                    break;
                default:
                    console.printf("Unknown command.\n");
                    break;
            }
        }
    }
}
