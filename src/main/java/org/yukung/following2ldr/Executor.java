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
package org.yukung.following2ldr;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;

/**
 * TODO for yukung
 *
 * @version $Id$
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
		Twitter instance = new TwitterFactory().getInstance();
		User user = instance.showUser("yukung");
		long id = user.getId();
		long[] friends = instance.getFriendsIDs(id, -1L).getIDs();
		ResponseList<User> target = instance.lookupUsers(friends); // 認証が必要
		for (User u : target) {
			System.out.println(u.getURL());
		}
	}
}
