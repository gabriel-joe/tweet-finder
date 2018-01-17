package tweet.finder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import tweet.finder.constant.Constants;
import twitter4j.TwitterException;

/**
 * 
 * @author gabriel oliveira
 *
 */
@Service
public class LogService {
	
	/**
	 * This method is responsible to load a twitter exception properties
	 * @param modelView
	 * @param te
	 * @return
	 */
	public ModelAndView getModelViewTwitterException(ModelAndView modelView, TwitterException te) {
		
		modelView = getDefaultPropertiesException(modelView);
		
		if(te.getRateLimitStatus() != null && te.getRateLimitStatus().getSecondsUntilReset() > 0) {
			modelView.addObject(Constants.BAR_TEXT, "The twitter API usage limit has been exceeded, please wait " + te.getRateLimitStatus().getSecondsUntilReset() + " seconds to use again.");
		} else {
			modelView.addObject(Constants.BAR_TEXT, "Failed to search tweets " + te.getMessage());
		}
		
		return modelView;
		
	}
	
	/**
	 * This method is responsible to return a default modelview in case of
	 * exceptions
	 * @param modelView
	 * @return
	 */
	public ModelAndView getDefaultPropertiesException(ModelAndView modelView) {
		
		modelView.addObject(Constants.PROGRESS_BAR_TYPE, "progress-bar-danger");
		modelView.addObject(Constants.PROGRESS_TOTAL, 100);
		modelView.addObject(Constants.BAR_STYLE, "100%");
		modelView.addObject(Constants.COUNT_OF_TWEETS, 0);
		
		return modelView;
	}
}
