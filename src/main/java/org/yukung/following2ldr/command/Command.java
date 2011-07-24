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
package org.yukung.following2ldr.command;

import java.util.List;

/**
 * バッチ処理の単位となるコマンドを表します。
 *
 * @version $$LastChangedRevision$$ : $$LastChangedDate$$
 * @author yukung $$LastChangedBy$$
 */
public interface Command {
	
	/**
	 * コマンドの破棄を行います。
	 */
	void destroy();
	
	/**
	 * コマンド名を取得します。
	 * @return コマンド名
	 */
	String getCommandName();
	
	/**
	 * コマンドの初期化を行います。
	 */
	void init();
	
	/**
	 * コマンドを実行します。
	 * @throws Throwable
	 */
	void run() throws Throwable;
	
	/**
	 * コマンド固有のパラメータを設定します。
	 * @param parameters
	 */
	void setParameters(List<String> parameters);
}
