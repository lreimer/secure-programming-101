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

package asm.de.qaware.campus.secpro.agent.impl;

import org.objectweb.asm.*;

/**
 * This class is the ASM dump of the original LoginServiceImpl.
 * <p>
 * Use gradlew asmifyOriginal
 */
public class AdminServiceImplDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, "de/qaware/campus/secpro/agent/impl/LoginServiceImpl", null, "java/lang/Object", new String[]{"de/qaware/campus/secpro/agent/LoginService"});

        // this here is the static LOGINS map
        {
            fv = cw.visitField(ACC_FINAL + ACC_STATIC, "LOGINS", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", null);
            fv.visitEnd();
        }
        // this here is the default constructor
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        // this here is the canLogin method
        {
            mv = cw.visitMethod(ACC_PUBLIC, "canLogin", "(Ljava/lang/String;Ljava/lang/String;)Z", null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "de/qaware/campus/secpro/agent/impl/LoginServiceImpl", "LOGINS", "Ljava/util/Map;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
            mv.visitTypeInsn(CHECKCAST, "java/lang/String");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "java/util/Objects", "equals", "(Ljava/lang/Object;Ljava/lang/Object;)Z", false);
            mv.visitInsn(IRETURN);
            mv.visitMaxs(2, 4);
            mv.visitEnd();
        }
        // this here is the static initializer
        {
            mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "com/google/common/collect/Maps", "newHashMap",
                    "()Ljava/util/HashMap;", false);
            mv.visitFieldInsn(PUTSTATIC, "de/qaware/campus/secpro/agent/impl/LoginServiceImpl", "LOGINS", "Ljava/util/Map;");
            mv.visitFieldInsn(GETSTATIC, "de/qaware/campus/secpro/agent/impl/LoginServiceImpl", "LOGINS", "Ljava/util/Map;");
            mv.visitLdcInsn("admin");
            mv.visitLdcInsn("Mellon");
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
            mv.visitInsn(POP);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 0);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}