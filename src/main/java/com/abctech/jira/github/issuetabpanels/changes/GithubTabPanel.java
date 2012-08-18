package com.abctech.jira.github.issuetabpanels.changes;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import com.abctech.jira.github.feedreader.FeedMessage;
import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.datetime.DateTimeFormatterFactory;
import com.atlassian.jira.datetime.DateTimeStyle;
import com.atlassian.jira.datetime.DateTimeFormatter;
import com.atlassian.jira.util.velocity.VelocityRequestContextFactory;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import org.apache.log4j.Logger;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueTabPanel;
import com.atlassian.jira.security.PermissionManager;

import com.abctech.jira.github.feedreader.Feed;
import com.abctech.jira.github.feedreader.JSONFeedParser;

public class GithubTabPanel extends AbstractIssueTabPanel {

	private PermissionManager permissionManager;
    private final WebResourceManager webResourceManager;
    private final VelocityRequestContextFactory requestContextFactory;

    private final PluginSettingsFactory settingsFactory;

    private final DateTimeFormatter dtFormatter;

    private static Logger log = Logger.getLogger(GithubTabPanel.class);
	
	public GithubTabPanel(PermissionManager permissionManager,
                          WebResourceManager webResourceManager,
                          VelocityRequestContextFactory requestContextFactory,
                          PluginSettingsFactory psf,
                          DateTimeFormatterFactory dtf) {
		this.permissionManager = permissionManager;
        this.webResourceManager = webResourceManager;
        this.requestContextFactory = requestContextFactory;
        this.settingsFactory = psf;
        this.dtFormatter = dtf.formatter().withStyle(DateTimeStyle.COMPLETE).forLoggedInUser();
	}
	
	@Override
	public List getActions(Issue issue, User remoteUser) {
        webResourceManager.requireResource("com.abctech.jira.github.github-jira-plugin:github-resource-js");
		String jiraIssueId;
        Feed issueFeed;
		List<GithubAction> list = new ArrayList<GithubAction>();
		jiraIssueId = issue.getKey();

        if (requestContextFactory.getJiraVelocityRequestContext().getRequestParameter("debug-issue") != null) {
            jiraIssueId = requestContextFactory.getJiraVelocityRequestContext().getRequestParameter("debug-issue");
        }

        log.info("ID: " + jiraIssueId);

        String base = requestContextFactory.getJiraVelocityRequestContext().getCanonicalBaseUrl();
        String protocol = "http";
        if (base.matches("https://")) {
            protocol = "https";
        }
        issueFeed = getFeed(jiraIssueId);
        log.info("issueFeed:" + issueFeed);
        if (issueFeed.events == null) {
            return list;
        }
        for (FeedMessage item : issueFeed.events) {
            list.add(new GithubAction(descriptor, item, protocol, dtFormatter));
        }
        return list;
	}

	@Override
	public boolean showPanel(Issue issue, User remoteUser) {
		return true;
	    //return permissionManager.hasPermission(Permissions.VIEW_VERSION_CONTROL, issue, remoteUser);
	}
	
	private Feed getFeed(String issueId) {
        JSONFeedParser jsonFeedParser;
        jsonFeedParser = new JSONFeedParser();
        return jsonFeedParser.getFeed( ((String)settingsFactory.createGlobalSettings().get("github.ws.url")) +  "issue/" + issueId);
    }
		
}
