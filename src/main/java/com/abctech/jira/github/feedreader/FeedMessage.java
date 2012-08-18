package com.abctech.jira.github.feedreader;

import com.abctech.jira.github.MD5Util;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.Period;

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
    public String email;
    public String gravatar;
    public String remoteuser = "";

	public String getText() {
		return text;
	}
	public Date getDate() {
		return date;
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
    public String getEmail() {
        return email;
    }
    public String getGravatar() {
        if (email == null && gravatar == null) return null;
        if (gravatar != null) return gravatar;
        return MD5Util.md5Hex(email);
    }
    public boolean isComment() {
        return id.contains("comment");
    }
    public boolean isError() {
        return id.contains("error");
    }
    public String getRepoUrl() {
        return "https://github.com/" + user + "/" + repo;
    }
    public String getJirauser() {
        return remoteuser;
    }
}