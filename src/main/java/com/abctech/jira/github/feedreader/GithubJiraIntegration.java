package com.abctech.jira.github.feedreader;

public class GithubJiraIntegration
{
	public static void main(String[] args) {
		RSSFeedParser parser = new RSSFeedParser("https://github.com/organizations/abctech/ronnachate.private.atom?token=af93e7229c43c2b7490076ea59cadda4");
		Feed feed = parser.readFeed();
		
		for (FeedMessage message : feed.getMessages()) {
			System.out.println("Name:"+message.getName());
			System.out.println("Title:"+message.getTitle());
			System.out.println("Link:"+message.getLink());
			System.out.println("Content:"+message.getContent());
			System.out.println("IssueId:"+message.getIssueId());
			System.out.println("");
		}
		
	}
}
