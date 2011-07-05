package com.abctech.jira.github.feedreader;

/*
 * one RSS message
 */
public class FeedMessage {

	
	
    public String _id;
	public String id;
    public String text;
	public String date;
	public String url;
	public String repo;
	public String user;

	public String getText() {
		return text;
	}
	public String getDate() {
		return date;
	}
	public String getUrl() {
		return url;
	}
	public String getRepo() {
		return repo;
	}
	public String getUser() {
		return user;
	}
}