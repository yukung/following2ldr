/*
 * Copyright 2010-2011 Yusuke Ikeda.(@yukung) <yukung.i@gmail.com>
 * Created on 2011/07/25
 *
 * This file is part of following2ldr.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.yukung.following2ldr.command.impl;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;
import org.yukung.following2ldr.command.Command;

/**
 * TODO for yukung
 *
 * @version $LastChangedRevision$ : $LastChangedDate$
 * @author yukung LastModified : $LastChangedBy$
 */
@RunWith(Seasar2.class)
public class ExampleCommandTest {
	
	private Command command;
	

	/**
	 * Test method for {@link org.yukung.following2ldr.command.impl.ExampleCommand#getCommandName()}.
	 */
	public void testGetCommandName() {
		assertEquals("org.yukung.following2ldr.command.impl.ExampleCommand", command.getCommandName());
	}
	
	/**
	 * Test method for {@link org.yukung.following2ldr.command.impl.ExampleCommand#run()}.
	 * @throws Throwable
	 */
	public void testRun() throws Throwable {
		command.run();
	}
	
}
