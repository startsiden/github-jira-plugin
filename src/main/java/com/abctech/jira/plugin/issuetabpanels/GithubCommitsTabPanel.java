package com.abctech.jira.plugin.issuetabpanels;

import java.util.ArrayList;
import java.util.List;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanel;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanelModuleDescriptor;
import com.opensymphony.user.User;

public class GithubCommitsTabPanel implements IssueTabPanel {

	@Override
	public List<String> getActions(Issue arg0, User arg1) {
		// TODO Auto-generated method stub
		List<String>list = new ArrayList<String>();
		list.add("Hello");
		return list;
	}

	@Override
	public void init(IssueTabPanelModuleDescriptor arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean showPanel(Issue arg0, User arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
