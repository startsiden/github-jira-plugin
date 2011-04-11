package com.abctect.jira.plugins;

import java.util.ArrayList;
import java.util.List;

/*
 * Stores an RSS feed
 */
public class Feed {
	
		  
	final List<FeedMessage> entries = new ArrayList<FeedMessage>();

	public Feed() { }

	public List<FeedMessage> getMessages() {
		return entries;
	}


}
