package tweet.finder.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.StringUtils;

import tweet.finder.exception.TweetFinderException;
import tweet.finder.service.FinderService;
import twitter4j.Status;
import twitter4j.TwitterException;

public class FinderServiceTest {
	
	
	@Test
	public void additionalHashTagTest() {
		
		FinderService service = new FinderService();
		List<String> listTest = new ArrayList<>();
		listTest.add("###teste#");
		listTest.add("teste");
		listTest.add("teste#");
		listTest.add("tes#t#t###########t#");
		listTest.add("");
		for(String s : listTest) {
			
			try {
				s = service.removeAdditionalsHashTag(s);
				int count = StringUtils.countOccurrencesOf(s, "#");
				assertEquals("Hashtag found", 1, count);
			} catch (TweetFinderException e) {
				 assertEquals(e.getMessage(), "Please, enter a text to search");
			}
			
		}
		
		
	}
	
	@Test(expected = TweetFinderException.class)
	public void exceptionTest() throws TweetFinderException {
		FinderService service = new FinderService();
		service.removeAdditionalsHashTag("");
		service.removeAdditionalsHashTag(null);
	}
	
	@Test
	public void tweetListSize(){
		FinderService service = new FinderService();
		try {
			Integer count = 200;
			List<Status> listStatus = service.getListTweetsByHashTag("#neymar", count);
			assertTrue(listStatus.size() <= count);
		} catch (TwitterException e) {
			fail(e.getMessage());
		}
	}
	
}
