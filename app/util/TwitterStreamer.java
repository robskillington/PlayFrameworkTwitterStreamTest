package util;

import java.util.*;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamer extends Thread {
	
	public static final String[] SEARCH_TERMS = new String[] { 
		"#IfIWereU"
	};
	
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	
	private ArrayList<Status> queue = new ArrayList<Status>();
	
	public TwitterStreamer(String consumerKey, String consumerSecret, 
		String accessToken, String accessTokenSecret) {
	
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessToken = accessToken;
		this.accessTokenSecret = this.accessTokenSecret;
	}
	
	public synchronized ArrayList<Status> getLatestStatuses() {
		ArrayList<Status> statuses = new ArrayList<Status>(queue);
		queue.clear();
		return statuses;
	}
	
	public void run () {	
		TwitterStatusListener listener = new TwitterStatusListener(queue);
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumerKey)
		  .setOAuthConsumerSecret(consumerSecret)
		  .setOAuthAccessToken(accessToken)
		  .setOAuthAccessTokenSecret(accessTokenSecret);		
		
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();		
		twitterStream.addListener(listener);
		FilterQuery filter = new FilterQuery();
		filter.track(SEARCH_TERMS);
		twitterStream.filter(filter);
		
		// Let it stream
		while (true) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {}
		}
	}
	
}
