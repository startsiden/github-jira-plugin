package com.abctech.jira.github.issuetabpanels.changes;

import java.text.SimpleDateFormat;
import java.util.*;

import com.abctech.jira.github.feedreader.*;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueAction;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanelModuleDescriptor;
import com.atlassian.jira.util.JiraKeyUtils;
import com.atlassian.plugin.*;
import org.apache.log4j.Logger;
import org.ofbiz.core.util.UtilMisc;
import webwork.action.Action;

import com.abctech.jira.github.feedreader.Feed;

/**
 * One item in the 'Git Commits' tab.
 */
public class GithubAction extends AbstractIssueAction {

	protected final IssueTabPanelModuleDescriptor descriptor;
    private static Logger log = Logger.getLogger(GithubTabPanel.class);

	protected Feed feed;
	
    public GithubAction(IssueTabPanelModuleDescriptor descriptor, Feed feed) {
		super(descriptor);
		this.descriptor = descriptor;
		this.feed = feed;
        log.info("In the mix in action constructor!");
		// TODO Auto-generated constructor stub
	}
    
    public String getHtml(Action webAction) {
        log.info("In the mix in getHTML!");
    	Map params = UtilMisc.toMap("webAction", webAction, "github", this);
        return this.descriptor.getHtml("view", params);
	}
    
    public boolean isDisplayActionAllTab() {
        return false;
    } 
    
	protected void populateVelocityParams(Map params) {
        params.put("github", this);
        params.put("msg", "Test Message");
        log.info("In the mix in populateVelocity!");
    }
	
    public Date getTimePerformed() {
        return null;
    }
    
    public Collection<FeedMessage> getFeed()
    {
      return this.feed.events;
    }
    public boolean hasFeed() {
        if (this.feed.events == null) {
            return false;
        }
        return (this.feed.events.size() > 0);
    }



}
