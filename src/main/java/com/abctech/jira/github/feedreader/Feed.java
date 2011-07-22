package com.abctech.jira.github.feedreader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * Stores an JSON feed
 */
public class Feed {
    public Collection<FeedMessage> events; // = new ArrayList<FeedMessage>();
    public String key;
    public String _id;

    public void add(String text) {
        add(text, "unknown");
    }
    public void add(String text, String id) {
        if (events == null) {
            events = new ArrayList<FeedMessage>();
        }
        FeedMessage msg = new FeedMessage();
        msg.text = text;
        msg.id = id;
        events.add(msg);
    }
}
