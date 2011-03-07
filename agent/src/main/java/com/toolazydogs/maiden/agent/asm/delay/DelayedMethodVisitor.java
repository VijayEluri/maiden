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
package com.toolazydogs.maiden.agent.asm.delay;

import org.objectweb.asm.MethodVisitor;


/**
 * Implementations of this interface delay visiting of their delegates until
 * the method {@link #flush(org.objectweb.asm.MethodVisitor, boolean)} is
 * called.
 */
public interface DelayedMethodVisitor
{
    void flush(MethodVisitor methodVisitor, boolean mark);
}