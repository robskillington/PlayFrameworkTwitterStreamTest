package util;

import java.util.*;

import twitter4j.*;

public class TwitterStatusListener implements StatusListener {
	
	private static final int MAX_SIZE = 200;
	private static final int CULL_SIZE = 50;
	
	private ArrayList<Status> tweetQueue;
	
	public TwitterStatusListener(ArrayList<Status> tweetQueue) {
		this.tweetQueue = tweetQueue;
	}
	
	public void onStatus(Status status) {
		if (tweetQueue.size() > MAX_SIZE) {
			for (int i = 0; i < CULL_SIZE; i++) {
				tweetQueue.remove(0);
			}
		}
		tweetQueue.add(status);
	}
	
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
	public void onScrubGeo(long userId, long upToStatusId) {}
	
	public void onException(Exception ex) {
		ex.printStackTrace();
	}	
	
}
