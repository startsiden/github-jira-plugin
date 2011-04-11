package com.abctect.jira.plugins;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSSFeedParser {
	static final String ID = "id";
	static final String PUBLISHED = "published";
	static final String UPDATED = "updated";
	static final String LINK = "link";
	static final String TITLE = "title";
	static final String AUTHOR = "author";
	static final String NAME = "name";
	static final String EMAIL = "email";
	static final String URI = "uri";
	static final String CONTENT = "content";
	static final String ENTRY = "entry";
	static final String MESSAGE = "message";

    static final String commitUrl = "https://github.com/api/v2/xml/commits/show";
	
	URL url;
	DocumentBuilder builder;
	InputStream in;
	Document doc;

	public RSSFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("null")
	
	public Feed readFeed() {
		Feed feed=null;
		
		try {

			String content   = "";
			String link   = "";
			String commitId   = "";
			String repo   = "";
			
			feed = new Feed();
		
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			in = read();
			doc = builder.parse(in);
				
			NodeList nodes = doc.getElementsByTagName("entry");
			
			//NodeList can't cast to Iterator , use this code for temporary
			for(int i=0;i<nodes.getLength();i++) {
				Element element = (Element)nodes.item(i);
				
				FeedMessage message = new FeedMessage();
				message.setId(getElementValue(element,ID));
				message.setPublished(getElementValue(element,PUBLISHED));
				message.setUpdated(getElementValue(element,UPDATED));				
				message.setTitle(getElementValue(element,TITLE));
				        
				message.setAuthor(getElementValue(element,AUTHOR));
				message.setName(getElementValue(element,NAME));
				content = getElementValue(element,CONTENT);
				message.setContent(content);
				link = getAttributeByTagName(element,LINK,"href");
				message.setLink(link );
				Pattern exp = Pattern.compile("^https://([-\\w\\.]+)(/[\\S+\\.]+)/commit(/[\\S+\\.]+).*#comment");
				Matcher m = exp.matcher(link);
		        if (m.find()) {
		        	// Feed from comment
		        	repo = m.group(2);
		        	commitId = m.group(3);
		        	message.setIssueId(getIssueId(repo, commitId));
		        }
		        else
		        {
		        	//Feed from commit
		        	message.setIssueId(getIssueId(content));		        	
		        }
	
				feed.getMessages().add(message);

			}
		} catch(Exception ex) {
                ex.printStackTrace();
				throw new RuntimeException(ex);
		}
				
		return feed;
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String getCharacterDataFromElement(Element e) {
		try {
			Node child = e.getFirstChild();
			if(child instanceof CharacterData) {
				CharacterData cd = (CharacterData) child;
				return cd.getData();
			}
		}catch(Exception ex) { }
		
		return "";
	} 

	protected String getElementValue(Element parent,String ElementName) {
		
		return getCharacterDataFromElement((Element)parent.getElementsByTagName(ElementName).item(0));

	}
	
	protected String getAttributeByTagName(Element parent,String ElementName, String AttributeName) {

		Element node = (Element)parent.getElementsByTagName(ElementName).item(0);
		return node.getAttributeNode(AttributeName).getNodeValue();
	            
	}
	
	protected String getIssueId(String content) {
		
		Pattern exp = Pattern.compile("<blockquote>([^\"]*)</blockquote>");
		Matcher m = exp.matcher(content);
        if (m.find()) {
            return m.group(1);
        }
        return "";
      
	}
	
	protected String getIssueId(String repo, String commitId) {
		try {
			String commitApiUrl = commitUrl + repo + commitId;
			this.url = new URL(commitApiUrl);
			in = read();
			doc = builder.parse(in);			
		} catch(Exception ex) {
			System.out.println("Not allow to get information from Repo:" + repo + " and commit :"+commitId);
			return null;
		}
		return doc.getElementsByTagName(MESSAGE).item(0).getNodeValue();
	}

}
