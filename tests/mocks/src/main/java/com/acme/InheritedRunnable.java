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
package com.acme;

import java.util.logging.Logger;


/**
 *
 */
public class InheritedRunnable extends MockRunnable
{
    private final static String CLASS_NAME = InheritedRunnable.class.getName();
    private final static Logger LOGGER = Logger.getLogger(CLASS_NAME);

    @Override
    public void run()
    {
        super.run();    //Todo change body of overridden methods use File | Settings | File Templates.
    }
}
