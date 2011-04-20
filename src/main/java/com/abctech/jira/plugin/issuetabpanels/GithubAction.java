package com.abctech.jira.plugin.issuetabpanels;

import java.util.Date;

import com.atlassian.jira.plugin.issuetabpanel.IssueAction;;

public class GithubAction implements IssueAction {

	@Override
	public String getHtml() {
		// TODO Auto-generated method stub
		return "Hello";
	}

	@Override
	public Date getTimePerformed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDisplayActionAllTab() {
		// TODO Auto-generated method stub
		return true;
	}

}