package com.abctech.jira.github.issuetabpanels.changes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.abctech.jira.github.feedreader.Feed;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueAction;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanelModuleDescriptor;
import com.atlassian.jira.util.JiraKeyUtils;
import com.atlassian.plugin.*;
import org.ofbiz.core.util.UtilMisc;
import webwork.action.Action;

import com.abctech.jira.github.feedreader.Feed;
import com.abctech.jira.github.feedreader.FeedMessage;
import com.abctech.jira.github.feedreader.RSSFeedParser;

/**
 * One item in the 'Git Commits' tab.
 */
public class GithubAction extends AbstractIssueAction {

	protected final IssueTabPanelModuleDescriptor descriptor;

	protected Feed feed;
	
    public GithubAction(IssueTabPanelModuleDescriptor descriptor, Feed feed) {
		super(descriptor);
		this.descriptor = descriptor;
		this.feed = feed;
		// TODO Auto-generated constructor stub
	}
    
    public String getHtml(Action webAction) {
		// TODO Auto-generated method stub
    	Map params = UtilMisc.toMap("webAction", webAction, "github", this);
        return this.descriptor.getHtml("view", params);
	}
    
    public boolean isDisplayActionAllTab() {
        return false;
    } 
    
	protected void populateVelocityParams(Map params) {
        params.put("github", this);
        params.put("msg", "Test Message");
    }
	
    public Date getTimePerformed() {
        return null;
    }
    
    public List<FeedMessage> getFeed()
    {
      return this.feed.getMessages();
    }



}
