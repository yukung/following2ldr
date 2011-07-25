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
package org.yukung.following2ldr.command;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * コマンドの抽象クラスです。
 *
 * @version $$LastChangedRevision$$ : $$LastChangedDate$$
 * @author yukung LastModified : $$LastChangedBy$$
 */
public abstract class AbstractCommand implements Command {
	
	protected Log log = LogFactory.getLog(this.getClass().getName());
	

	public void destroy() {
		log.info("タスク：【" + getCommandName() + "】を終了します。");
	}
	
	public abstract String getCommandName();
	
	public void init() {
		log.info("タスク：【" + getCommandName() + "】を開始します。");
	}
	
	public abstract void run() throws Throwable;
	
	public void setParameters(List<String> parameters) {
		log.info(parameters.size());
	}
	
}
