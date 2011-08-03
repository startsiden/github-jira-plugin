package com.abctech.jira.plugins;

import com.abctech.jira.github.feedreader.Feed;
import com.abctech.jira.github.feedreader.FeedMessage;
import com.abctech.jira.github.feedreader.JSONFeedParser;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: andremar
 * Date: 7/5/11
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class JSONFeedParserTest {

    @Test
    public void testParser() {
        JSONFeedParser fp = new JSONFeedParser();
        Feed feed = fp.getFeed("TEST");
        assertTrue("We have events in our collection", (feed.events.size() > 0));
    }
}
