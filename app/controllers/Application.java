package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import util.*;

import twitter4j.*;

public class Application extends Controller {

	private static TwitterStreamer twitterStreamer = null;

    public static void index() {
        render();
    }
	
	public synchronized static void stream() {
		if (twitterStreamer == null) {
			twitterStreamer = new TwitterStreamer(
				 "your consumer key",
				 "your consumer secret",
				 "your access token",
				 "your access token secret"
			);
			twitterStreamer.start();
		}
		
		ArrayList<Status> latestStatuses = twitterStreamer.getLatestStatuses();
		
		while (latestStatuses.size() < 1) {
			suspend(100);
			latestStatuses = twitterStreamer.getLatestStatuses();
		}
	
		renderJSON(latestStatuses);
	}

}

