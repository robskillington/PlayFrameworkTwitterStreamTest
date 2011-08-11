# Play Framework Twitter Streaming Test

This Play project streams tweets from any specified search keywords in app/util/TwitterStreamer.java SEARCH_TERMS keyword array.  It implements a very simple long polling method implemented in jQuery and takes use of suspend() in the Play Application controller to hold open the connection until new data is available for the client to consume.


