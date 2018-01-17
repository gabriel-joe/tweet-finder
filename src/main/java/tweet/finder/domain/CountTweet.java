package tweet.finder.domain;

public class CountTweet {
	
	private int count;
	private String title;
	
	public CountTweet(int count, String title) {
		this.count = count;
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
