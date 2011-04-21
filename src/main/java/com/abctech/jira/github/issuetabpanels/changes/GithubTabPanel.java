package com.abctech.jira.github.issuetabpanels.changes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abctech.jira.github.feedreader.RSSFeedParser;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueTabPanel;
import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.security.Permissions;
import com.opensymphony.user.User;

import com.abctech.jira.github.feedreader.Feed;
import com.abctech.jira.github.feedreader.FeedMessage;
import com.abctech.jira.github.feedreader.RSSFeedParser;

public class GithubTabPanel extends AbstractIssueTabPanel {

	private PermissionManager permissionManager;
	
	
	public GithubTabPanel(PermissionManager permissionManager) {
		this.permissionManager = permissionManager;
	}
	
	@Override
	public List<GithubAction> getActions(Issue issue, User remoteUser) {
		// TODO Auto-generated method stub
		String jiraIssueId;
		List<GithubAction> list = new ArrayList<GithubAction>();
		RSSFeedParser parser = new RSSFeedParser("https://github.com/organizations/abctech/ronnachate.private.atom?token=af93e7229c43c2b7490076ea59cadda4");
		Feed feed = parser.readFeed();
		jiraIssueId = issue.getProjectObject().getName() + '-'+issue.getId();
		//Fake IssueId
		jiraIssueId = "ABCT-891";
		list.add(new GithubAction(descriptor, filterFeed(feed, jiraIssueId)));
		return list;
	}

	@Override
	public boolean showPanel(Issue issue, User remoteUser) {
		return true;
	    //return permissionManager.hasPermission(Permissions.VIEW_VERSION_CONTROL, issue, remoteUser);
	}
	
	public Feed filterFeed(Feed liveFeed, String jiraIssueId)
	{
		 Feed relateFeed = new Feed();
		 Iterator itr = liveFeed.getMessages().iterator();
		 while(itr.hasNext()) {
			 FeedMessage fm = (FeedMessage)itr.next();
			 if(fm.getIssueId() != null){
				 if(fm.getIssueId().equalsIgnoreCase(jiraIssueId))
				 relateFeed.getMessages().add(fm);
			 }
		 }
		 
		 return relateFeed;
	}
		
}
