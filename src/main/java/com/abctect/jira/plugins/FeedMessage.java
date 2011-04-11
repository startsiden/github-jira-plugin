package com.abctect.jira.plugins;

/*
 * one RSS message
 */
public class FeedMessage {

	
	
	private String id;
	private String published;
	private String updated;
	private String link;
	private String title;
	private String author;
	private String name;
	private String content;
	private String issueid;
    
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPublished() {
		return id;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}   
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIssueId() {
		return issueid;
	}
	public void setIssueId(String issueid) {
		this.issueid = issueid;
	}

}