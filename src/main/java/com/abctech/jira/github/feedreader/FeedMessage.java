package com.abctech.jira.github.feedreader;

import java.util.Date;

/*
* one RSS message
*/
public class FeedMessage {

	
	
    public String _id;
	public String id;
    public String text;
	public Date date;
	public String url;
	public String repo;
	public String user;

	public String getText() {
		return text;
	}
	public Date getDate() {
		return date;
	}
    public String getRelativeDate() {
        Long delta = Math.round( ((double) new Date().getTime() - (double) date.getTime()) / (double) 1000);
        // XXX: This is ugly as hell, but who cares, really?
        String suffix = " ago";
        String relative = "";
        if (delta < 0) {
            suffix = " in the future! omg!";
        }
        delta = Math.abs(delta);
        if (delta < 60) {
            relative = delta + " seconds";
        } else if (delta < (60 * 60)) {
            relative = delta / 60 + " minutes";
        } else if (delta < 60 * 60 * 24) {
            relative = delta / (60 * 60) + " hours";
        } else if (delta < (60 * 60 * 24 * 7)) {
            relative = delta / (60 * 60 * 24) + " days";
        } else if (delta < (60 * 60 * 24 * 30)) { // XXX: not really 30 days in a month, but oh well
            relative = delta / (60 * 60 * 24 * 7) + " weeks";
        } else if (delta < (60 * 60 * 24 * 365)) { // XXX: Blablabla leapyear blablabla
            relative = delta / (60 * 60 * 24 * 30) + " months";
        } else {
            relative = delta / (60 * 60 * 24 * 365) + " years";
        }
        return relative + suffix;
    }
    public String getShortSha(int chars) {
        if (id.length() == 0) {
            return "";
        }
        if (chars > id.length()) {
            chars = id.length() - 1;
        }
        return id.substring(0, chars);
    }
    public String getShortSha() {
        return getShortSha(7);
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
    public boolean isComment() {
        return id.contains("Comment");
    }
    public boolean isError() {
        return id.contains("error");
    }
}