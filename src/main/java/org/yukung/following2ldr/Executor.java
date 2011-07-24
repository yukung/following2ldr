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
package org.yukung.following2ldr;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.yukung.following2ldr.controller.Processor;

/**
 * バッチ処理を起動するクラスです。
 *
 * @version $$LastChangedRevision$$ : $$LastChangedDate$$
 * @author yukung $$LastChangedBy$$
 */
public class Executor {
	
	/**
	 * エントリポイント。
	 * @param args 実行パラメータ
	 */
	public static void main(String[] args) {
		// DIコンテナからコマンドライン引数のコンポーネントを取得
		SingletonS2ContainerFactory.setConfigPath(args[0]);
		SingletonS2ContainerFactory.init();
		S2Container container = SingletonS2ContainerFactory.getContainer();
//		S2Container container = S2ContainerFactory.create(args[0]);
//		container.init();
		// バッチプロセッサを取得
		Processor processor = (Processor) container.getComponent("processor");
		// 実行パラメータを取得
		List<String> parameters = new ArrayList<String>();
		for (int i = 1; i < args.length; i++) {
			parameters.add(args[i]);
		}
		// プロセッサ実行
		processor.execute(parameters);
		// 完了メールなど
	}
}
