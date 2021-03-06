package com.abctech.jira.github.issuetabpanels.changes;

import java.text.SimpleDateFormat;
import java.util.*;

import com.abctech.jira.github.MD5Util;
import com.abctech.jira.github.feedreader.*;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueAction;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanelModuleDescriptor;
import com.atlassian.jira.util.JiraKeyUtils;
import com.atlassian.jira.datetime.DateTimeFormatter;
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
    private final DateTimeFormatter dtf;
	protected FeedMessage item;
    protected String protocol;
	
    public GithubAction(IssueTabPanelModuleDescriptor descriptor, FeedMessage item, String protocol, DateTimeFormatter dtf) {
		super(descriptor);
		this.descriptor = descriptor;
        this.protocol = protocol;
		this.item = item;
        this.dtf = dtf;
	}
    
    public String getHtml(Action webAction) {
    	Map params = UtilMisc.toMap("webAction", webAction, "github", this, "entry", item);
        return this.descriptor.getHtml("view", params);
	}
    
    public boolean isDisplayActionAllTab() {
        return true;
    } 
    
	protected void populateVelocityParams(Map params) {
        params.put("github", this);
        params.put("entry", item);
        params.put("msg", "Test Message");
    }
	
    public Date getTimePerformed() {
        return item.getDate();
    }
    public String getFormattedDate() {
        return dtf.format(item.getDate());
    }
    public String getJiraUser() {
        // TODO: MAke this return the full name of the user
        return item.getJirauser();
    }
    public String getGravatarUrl() {
        String start = protocol + "://";
        if (protocol == "https") {
            start = protocol + "://secure.";
        }
        return start + "gravatar.com/avatar/" + item.getGravatar() + "?s=16";
    }

}
