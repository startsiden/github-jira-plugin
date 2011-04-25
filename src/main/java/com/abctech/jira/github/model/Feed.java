package com.abctech.jira.github.model;

//import java.util.Date;

public class Feed {
	
    private Long id;
    private Long issueId;
    private String publish;
    private String updated;
    private String link;
    private String title;
    private String author;
    private String name;
    private String email;
    private String uri;
    private String content;
    private String entry;

    public Feed() {}

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public String getPublish() {
		return publish;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getUpdated() {
		return updated;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getEntry() {
		return entry;
	}

}