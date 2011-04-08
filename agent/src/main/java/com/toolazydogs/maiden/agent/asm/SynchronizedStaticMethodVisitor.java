/**
 *
 * Copyright 2011 (C) The original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.toolazydogs.maiden.agent.asm;

import java.util.logging.Logger;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


/**
 *
 */
public class SynchronizedStaticMethodVisitor extends DelegateMethodVisitor implements Opcodes
{
    private final static String CLASS_NAME = SynchronizedStaticMethodVisitor.class.getName();
    private final static Logger LOGGER = Logger.getLogger(CLASS_NAME);
    private final String className;
    private int line;

    public SynchronizedStaticMethodVisitor(MethodVisitor visitor, String clazz)
    {
        super(visitor);

        assert clazz != null;
        this.className = "L" + clazz + ";.class";
    }

    @Override
    public void visitCode()
    {
        LOGGER.entering(CLASS_NAME, "visitCode");

        getVisitor().visitCode();

        AsmUtils.push(getVisitor(), line);
        getVisitor().visitLdcInsn(Type.getType(className));
        getVisitor().visitMethodInsn(INVOKESTATIC, "com/toolazydogs/maiden/IronMaiden", "lockObject", "(ILjava/lang/Object;)V");

        LOGGER.exiting(CLASS_NAME, "visitCode");
    }

    @Override
    public void visitInsn(final int opcode)
    {
        LOGGER.entering(CLASS_NAME, "visitInsn", opcode);

        switch (opcode)
        {
            case IRETURN:
            case LRETURN:
            case FRETURN:
            case DRETURN:
            case ARETURN:
            case RETURN:
            case ATHROW:
                AsmUtils.push(getVisitor(), line);
                getVisitor().visitLdcInsn(Type.getType(className));
                getVisitor().visitMethodInsn(INVOKESTATIC, "com/toolazydogs/maiden/IronMaiden", "unlockObject", "(ILjava/lang/Object;)V");
                break;

            default:
        }

        getVisitor().visitInsn(opcode);

        LOGGER.exiting(CLASS_NAME, "visitInsn");
    }

    @Override
    public void visitVarInsn(final int opcode, final int var)
    {
        LOGGER.entering(CLASS_NAME, "visitVarInsn", new Object[]{opcode, var});

        switch (opcode)
        {
            case RET:
                AsmUtils.push(getVisitor(), line);
                getVisitor().visitLdcInsn(Type.getType(className));
                getVisitor().visitMethodInsn(INVOKESTATIC, "com/toolazydogs/maiden/IronMaiden", "unlockObject", "(ILjava/lang/Object;)V");
                break;

            default:
        }

        getVisitor().visitVarInsn(opcode, var);

        LOGGER.exiting(CLASS_NAME, "visitVarInsn");
    }

    @Override
    public void visitLineNumber(int line, Label start)
    {
        this.line = line;

        getVisitor().visitLineNumber(line, start);
    }
}
