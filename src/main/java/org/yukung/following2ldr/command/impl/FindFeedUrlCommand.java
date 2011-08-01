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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.yukung.following2ldr.Constants;
import org.yukung.following2ldr.command.AbstractCommand;

import twitter4j.IDs;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Twitterでフォローしているユーザの登録されたURLを取得して，ファイルに出力します。
 *
 * @version $$LastChangedRevision$$ : $$LastChangedDate$$
 * @author yukung LastModified : $$LastChangedBy$$
 */
public class FindFeedUrlCommand extends AbstractCommand {
	
	@Override
	public String getCommandName() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public void run() throws Throwable {
		// Twitterへ認証
		long start = System.currentTimeMillis();
		String consumerKey = config.getProperty(Constants.CONSUMER_KEY);
		String consumerSecret = config.getProperty(Constants.CONSUMER_SECRET);
		String userName = params.get(0);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		RequestToken requestToken = twitter.getOAuthRequestToken();
		String authorizationURL = requestToken.getAuthorizationURL();
		System.out.println("→:" + authorizationURL);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter the PIN:");
		String pin = br.readLine();
		AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		twitter.setOAuthAccessToken(accessToken);
		long cursor = -1L;
		IDs friendIDs;
		List<Long> iDsList = new ArrayList<Long>(5000);
		do {
			friendIDs = twitter.getFriendsIDs(userName, cursor);
			long[] iDs = friendIDs.getIDs();
			for (long iD : iDs) {
				iDsList.add(iD);
			}
			cursor = friendIDs.getNextCursor();
		} while (friendIDs.hasNext());
		List<long[]> list = new ArrayList<long[]>();
		int offset = 0;
		long[] tmp = new long[100];
		for (Long id : iDsList) {
			if (offset < 100) {
				tmp[offset] = id;
				offset++;
			} else {
				list.add(tmp);
				offset = 0;
				tmp = new long[100];
			}
		}
		list.add(tmp);
		List<URL> urlList = new ArrayList<URL>();
		for (long[] array : list) {
			ResponseList<User> lookupUsers = twitter.lookupUsers(array);
			for (User user : lookupUsers) {
				log.info("URL:" + user.getURL());
				urlList.add(user.getURL());
			}
		}
		String path = "C:\\Users\\ikeda_yusuke\\Documents\\sandbox\\java\\data\\" + userName + ".txt";
		FileWriter writer = new FileWriter(path);
		BufferedWriter out = new BufferedWriter(writer);
//		PrintWriter pw = new PrintWriter(writer);
		for (URL url : urlList) {
			if (url != null) {
				out.write(url.toString() + "\n");
			}
		}
		out.flush();
		out.close();
		long end = System.currentTimeMillis();
		log.info("処理時間:" + (end - start) + " ms");
		
		// 取得対象のユーザIDを外部ファイルorコマンドライン引数から取得
		// Twitter APIからふぉろわーのIDを取得
		// ふぉろわーのIDからURLを100件ずつ取得
		// ファイルに改行区切りでURLを保存
		
	}
}
