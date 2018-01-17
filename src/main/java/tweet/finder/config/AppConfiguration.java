package tweet.finder.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 * @author gabriel oliveira
 *
 */
@Configuration
public class AppConfiguration {

	private Properties prop;
	private Twitter twitter;
	private static final String PROPERTIES_NAME = "application.properties";
	private final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

	/**
	 * Method responsible to load properties file
	 */
	@Bean
	private void loadProperties() {

		if(prop == null) {
			prop = new Properties();

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_NAME);
			if (inputStream != null) {
				try {
					prop.load(inputStream);
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			
		}
		
	}

	/**
	 * Method responsible to return a value of property
	 * @param propertyName
	 * @return
	 */
	public String getProperty(String propertyName) {
		loadProperties();
		return prop.getProperty(propertyName);
	}
	
	/**
	 * Method responsible to return a twitter instance 
	 * @return
	 */
	@Bean
	public Twitter getTwitterInstance() {
		
		if(twitter != null) {
			return twitter;
		}
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey(getProperty("twitter.consumerKey"))
		.setOAuthConsumerSecret(getProperty("twitter.consumerSecret"))
		.setOAuthAccessToken(getProperty("twitter.acessTokenKey"))
		.setOAuthAccessTokenSecret(getProperty("twitter.acessTokenSecret"));
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
		
		return twitter;

	}
}
