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

	public String getText() {
		return text;
	}
	public Date getDate() {
		return date;
	}
    public String getRelativeDate() {
        if (date == null) {
            return "Unknown";
        }
        Instant now = new Instant(new Date().getTime());
        Instant then = new Instant(date.getTime());

        String suf = " ago";
        String relative = "";
        Interval delta;
        if (then.isAfter(now)) {
            suf = " in the future";
            delta = new Interval(now, then);
        } else {
            delta = new Interval(then, now);
        }
        Period red = delta.toPeriod();
        if (red.getYears() > 1) {
            relative = red.getYears() + " years" + suf;
        } else if (red.getMonths() > 1) {
            relative = red.getMonths() + " months" + suf;
        } else if (red.getWeeks() > 1) {
            relative = red.getWeeks() + " weeks" + suf;
        } else if (red.getDays() > 1) {
            relative = red.getDays() + " days" + suf;
        } else if (red.getHours()  > 1) {
            relative = red.getHours() + " hours" + suf;
        } else if (red.getMinutes() > 1) {
            relative = red.getMinutes() + " minutes" + suf;
        } else {
            relative = "seconds" + suf;
        }
        return relative;
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
}