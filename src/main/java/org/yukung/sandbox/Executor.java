/*
 * Copyright 2010-2011 Yusuke Ikeda.(@yukung) <yukung.i@gmail.com>
 * Created on 2011/07/18
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
package org.yukung.sandbox;

import twitter4j.IDs;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/**
 * TODO for yukung
 *
 * @version $Id: Executor.java 8 2011-07-24 12:19:39Z yukung $
 * @author yukung
 */
public class Executor {
	
	/**
	 * TODO for yukung
	 *
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String consumerKey = "ILnYlvMg8qRXZ2OFGshEww";
		String consumerSecret = "BZFcLLm0F78UnR9QE87xfw8k7pqnWaXfUHaJPC4kpSQ";
		String accessToken = "14724896-foFyC3IU69mCs19GMe7IBMqJ9QvEOfgn4OcJ6an8A";
		String accessTokenSecret = "rcOESXmLNVZ44KhsR9wUaOuD0bzw9ec8hEJd3ydy7Q";
		
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
		User user = twitter.showUser("yukung");
		long id = user.getId();
		long cursor = -1L;
		IDs ids;
		do {
			ids = twitter.getFriendsIDs(id, cursor);
			long[] iDs2 = ids.getIDs();
			long[] sel = new long[100];
			for (int i = 0; i < 100; i++) {
				sel[i] = iDs2[i];
			}
			ResponseList<User> target = twitter.lookupUsers(sel);
			for (User u : target) {
				System.out.println(u.getURL());
			}
			cursor = ids.getNextCursor();
		} while (ids.hasNext());
	}
}
