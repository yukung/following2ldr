/*
 * Copyright 2010-2011 Yusuke Ikeda.(@yukung) <yukung.i@gmail.com>
 * Created on 2011/07/24
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
package org.yukung.following2ldr.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yukung.following2ldr.command.Command;

/**
 * バッチ処理を制御するプロセッサです。
 *
 * @version $$LastChangedRevision$$ : $$LastChangedDate$$
 * @author yukung $$LastChangedBy$$
 */
public class Processor {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/** バッチ処理コマンドのList */
	private List<? extends Command> commands;
	

	/**
	 * 一連のバッチ処理を実行する。
	 *
	 * @param parameters 実行時パラメータ
	 */
	public void execute(List<String> parameters) {
		try {
			for (Command command : commands) {
				command.setParameters(parameters);
				command.init();
				command.run();
				command.destroy();
			}
		} catch (Throwable e) {
			log.fatal(e.getMessage(), e);
			return;
		}
	}
	
	public void setCommands(List<? extends Command> commands) {
		this.commands = commands;
	}
}
