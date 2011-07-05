package com.abctech.jira.github.feedreader;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: andremar
 * Date: 7/5/11
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class JSONFeedParser {
    private static Logger log = Logger.getLogger(JSONFeedParser.class);
    private URL url;
    private static String JSON = "{\"events\":[{\"_id\":\"4e11e6b808f4b7bc1f0094ad\",\"text\":\"ABCT-1072: Add bold on title/summary for SAN Ads\",\"date\":\"2011-07-04T08:21:23.000Z\",\"url\":\"https://github.com/startsiden/multi-search/commit/e12062e20caf45e11783236736102d160e6bfd8e\",\"repo\":\"multi-search\",\"user\":\"startsiden\",\"id\":\"e12062e20caf45e11783236736102d160e6bfd8e\"},{\"_id\":\"4e11e9325442799a2d000003\",\"text\":\"<div class=\\\"details\\\">\\n  \\n  <div class=\\\"message\\\">\\n    Comment \\n    in <a href=\\\"https://github.com/startsiden/multi-search/commit/e12062e20caf45e11783236736102d160e6bfd8e#comments\\\">e12062e</a>:\\n    <blockquote title=\\\"Just testing some comment functionallity, feel free to ignore! But I DO like that you fixed it up so nicely :)\\\">\\n      <p>Just testing some comment functionallity, feel free to ignore! But I DO like that you fixed it up so nicely :)</p>\\n    </blockquote>\\n  </div>\\n</div>\",\"url\":\"https://github.com/startsiden/multi-search/commit/e12062e20caf45e11783236736102d160e6bfd8e#comments\",\"date\":\"2011-07-04T16:19:20.000Z\",\"repo\":\"multi-search\",\"user\":\"startsiden\",\"id\":\"tag:github.com,2008:CommitCommentEvent/1475968538\"}],\"key\":\"ABCT-1072\",\"_id\":\"4e11e6b808f4b7bc1f0094ac\"}";
    public Feed getFeed(String feedurl) {
        try {
            return getFeed(new URL(feedurl));
        } catch (MalformedURLException e) {
            return new Feed();
        }
    }
    public Feed getFeed(URL url) {
        ObjectMapper m = new ObjectMapper();
        Feed feed = new Feed();
        log.info("attempting to read from " + url);
        try {
            feed = m.readValue(url, Feed.class);
        } catch(IOException e) {
             log.error("ERROR Parsing JSON: ", e);
        }
        return feed;
    }
}

