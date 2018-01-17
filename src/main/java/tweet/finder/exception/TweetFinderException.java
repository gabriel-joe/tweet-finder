package tweet.finder.exception;

public class TweetFinderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2192149871596464715L;

	public TweetFinderException(String message, Throwable cause) {
		super(message, cause);
	}

	public TweetFinderException(String message) {
		this(message, (Throwable) null);
	}	
}
