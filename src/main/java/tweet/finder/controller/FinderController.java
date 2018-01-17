package tweet.finder.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tweet.finder.constant.Constants;
import tweet.finder.service.FinderService;
import tweet.finder.service.LogService;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * 
 * @author gabriel oliveira
 *
 */
@Controller
public class FinderController {
	
	private static final Logger logger = LoggerFactory.getLogger(FinderController.class);
	
	@Autowired
	private FinderService finderService;
	
	@Autowired
	private LogService logService;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
		model.put(Constants.COUNT_OF_TWEETS, 0);
		model.put(Constants.PROGRESS_TOTAL, 100);
		model.put(Constants.BAR_STYLE, "0%");
		model.put(Constants.BAR_TEXT, "");
		model.put(Constants.LIST_OPTIONS, finderService.getListOptions());
		model.put(Constants.PROGRESS_BAR_TYPE, "progress-bar-success");
		return "index";
	}

	@RequestMapping(value = "/finder", method = RequestMethod.POST)
	public ModelAndView find(@RequestParam("hashtag") String hashtag, @RequestParam("optionValue") String count) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		try {
			
			hashtag = finderService.removeAdditionalsHashTag(hashtag);
			List<Status> listStatus = finderService.getListTweetsByHashTag(hashtag, Integer.parseInt(count));
			model.addObject(Constants.COUNT_OF_TWEETS, listStatus.size());
			model.addObject(Constants.PROGRESS_TOTAL, 100);
			model.addObject(Constants.PROGRESS_BAR_TYPE, "progress-bar-success");
			model.addObject(Constants.BAR_STYLE, "100%");
			model.addObject(Constants.LIST_TWEETS, listStatus);
			model.addObject(Constants.LIST_OPTIONS, finderService.getListOptions());
			model.addObject(Constants.BAR_TEXT, "The count tweets of " + hashtag +  " is " + listStatus.size());
			
		} catch (TwitterException e) {
			logger.error("Failed to search tweets " + e.getMessage());
			model = logService.getModelViewTwitterException(model, e);
			model.addObject(Constants.LIST_OPTIONS, finderService.getListOptions());
		} catch (Exception e) {
			logger.error("Failed to search tweets " + e.getMessage());
			model = logService.getDefaultPropertiesException(model);
			model.addObject(Constants.BAR_TEXT, e.getMessage());
			model.addObject(Constants.LIST_OPTIONS, finderService.getListOptions());
		}
		
		return model;

	}

}