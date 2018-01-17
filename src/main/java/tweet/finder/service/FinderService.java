package tweet.finder.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tweet.finder.config.AppConfiguration;
import tweet.finder.domain.CountTweet;
import tweet.finder.exception.TweetFinderException;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * 
 * @author gabriel oliveira
 *
 */
@Service
public class FinderService {

	private static final Logger logger = LoggerFactory.getLogger(FinderService.class);
	private AppConfiguration appConfiguration = new AppConfiguration();

	/**
	 * This method is responsible to search the twitter metadata
	 * to find count tweets about specific word
	 * @param hashtag
	 * @return
	 * @throws TwitterException 
	 */
	public List<Status> getListTweetsByHashTag(String hashtag, Integer count) throws TwitterException{
		
		List<Status> listStatus = new ArrayList<>();

		Query query = new Query(hashtag); //
		
		GeoLocation location = new GeoLocation(Double.parseDouble(appConfiguration.getProperty("twitter.latitude")),Double.parseDouble(appConfiguration.getProperty("twitter.longitude"))); //latitude, longitude
		query.setGeoCode(location, Double.parseDouble(appConfiguration.getProperty("twitter.radius")), Query.MILES); //location, radius, unit
		query.setResultType(Query.RECENT);
		query.setCount(100);
		QueryResult result; 

		do {
			result = appConfiguration.getTwitterInstance().search(query);
			listStatus.addAll(result.getTweets());
		} while (listStatus.size() < count && (query = result.nextQuery()) != null);
		
		logger.debug("getCountTweetsByHashtag finished with sucess");

		return listStatus;
	}
	
	/**
	 * This method is responsible to granting the integrity of string hashtag
	 * @param hashtag
	 * @return
	 * @throws TweetFinderException 
	 */
	public String removeAdditionalsHashTag(String hashtag) throws TweetFinderException {
		
		if(hashtag == null || hashtag.isEmpty()) {
			throw new TweetFinderException("Please, enter a text to search");
		}
		
		hashtag = hashtag.replaceAll("#", "");
		return "#" + hashtag;

	}
	
	/**
	 * This method is responsible to return a default list options 
	 * @return
	 */
	public List<CountTweet> getListOptions(){
		
		List<CountTweet> listCount = new ArrayList<>();
		CountTweet ct = new CountTweet(100, "100 - 150");
		listCount.add(ct);
		ct = new CountTweet(200, "200 - 250");
		listCount.add(ct);
		ct = new CountTweet(300, "300 - 350");
		listCount.add(ct);
		ct = new CountTweet(400, "400 - 450");
		listCount.add(ct);
		ct = new CountTweet(500, "500 - 550");
		listCount.add(ct);
		ct = new CountTweet(600, "600 - 650");
		listCount.add(ct);
		ct = new CountTweet(700, "700 - 750");
		listCount.add(ct);
		ct = new CountTweet(800, "800 - 850");
		listCount.add(ct);
		ct = new CountTweet(900, "900 - 950");
		listCount.add(ct);
		
		return listCount;
	}
	
	
}