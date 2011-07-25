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
import java.io.InputStreamReader;

import org.yukung.following2ldr.command.AbstractCommand;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * TwitterでフォローしているユーザのURLを取得して，ファイルに出力します。
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
		// TODO Auto-generated method stub
		// Twitterへ認証
		String consumerKey = "i7xTYiywTA65Y4dsAUmBJg";
		String consumerSecret = "WbFY7Wx7mDFSOHwsClOb9wOj9txasXF8p6lwsommtg";
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
		User user = twitter.showUser("yukung");
		System.out.println(user.getId());
		// 取得対象のユーザIDを外部ファイルorコマンドライン引数から取得
		// Twitter APIからふぉろわーのIDを取得
		// ふぉろわーのIDからURLを100件ずつ取得
		// ファイルに改行区切りでURLを保存
		
	}
	
}
