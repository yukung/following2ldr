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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * コマンドの抽象クラスです。
 *
 * @version $$LastChangedRevision$$ : $$LastChangedDate$$
 * @author yukung LastModified : $$LastChangedBy$$
 */
public abstract class AbstractCommand implements Command {
	
	/** 設定ファイルのパス */
	private static final String CONFIG_FILE_PATH = "/config/configuration.properties";
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	/** コマンドラインパラメータ */
	protected List<String> params;
	
	/** アプリケーション設定情報 */
	protected static Properties config;
	

	public void destroy() {
		log.info("タスク：【" + getCommandName() + "】を終了します。");
	}
	
	public abstract String getCommandName();
	
	public void init() {
		log.info("タスク：【" + getCommandName() + "】を開始します。");
		
		if (config == null) {
			config = new Properties();
			InputStream in = getClass().getResourceAsStream(CONFIG_FILE_PATH);
			try {
				if (in == null) {
					throw new FileNotFoundException("設定ファイルが見つかりません。");
				}
				config.load(in);
			} catch (IOException e) {
				log.error(e.getMessage(), e.getCause());
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}
	
	public abstract void run() throws Throwable;
	
	public void setParameters(List<String> parameters) {
		log.info(parameters.size());
		params = parameters;
	}
	
}
