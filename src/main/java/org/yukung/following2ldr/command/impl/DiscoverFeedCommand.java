/*
 * Copyright 2010-2011 Yusuke Ikeda.(@yukung) <yukung.i@gmail.com>
 * Created on 2011/08/01
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.yukung.following2ldr.command.AbstractCommand;

/**
 * Livedoor Reader Discover Feed API を使って配信フィードのOPMLを取得します。
 *
 * @version $LastChangedRevision$ : $LastChangedDate$
 * @author yukung LastModified : $LastChangedBy$
 */
public class DiscoverFeedCommand extends AbstractCommand {
	
	@Override
	public String getCommandName() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public void run() throws Throwable {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		client.getParams().setParameter("http.connection.timeout", 5000);
		client.getParams().setParameter("http.socket.timeout", 3000);
		
		HttpPost post = new HttpPost("http://rpc.reader.livedoor.com/feed/discover");
		
		FileReader reader = new FileReader("C:\\Users\\ikeda_yusuke\\Documents\\sandbox\\java\\data\\yukung.txt");
		BufferedReader br = new BufferedReader(reader);
		StringBuilder builder = new StringBuilder();
		String line;
		int count = 0;
		while ((line = br.readLine()) != null) {
			builder.append(line);
			count++;
			builder.append("\r\n");
		}
		br.close();
		System.out.println(count);
		builder.deleteCharAt(builder.lastIndexOf("\r\n"));
		System.out.println(builder.toString());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("links", builder.toString()));
		post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity);
		client.getConnectionManager().shutdown();
		BufferedWriter out =
				new BufferedWriter(new FileWriter(
						"C:\\Users\\ikeda_yusuke\\Documents\\sandbox\\java\\data\\yukung.opml"));
		out.write(content);
		out.flush();
		out.close();
	}
}
