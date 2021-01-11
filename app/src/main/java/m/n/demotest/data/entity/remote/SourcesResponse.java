package m.n.demotest.data.entity.remote;

import com.google.gson.annotations.SerializedName;

public class SourcesResponse {

	@SerializedName("crawl_rate")
	private int crawlRate;

	@SerializedName("title")
	private String title;

	@SerializedName("slug")
	private String slug;

	@SerializedName("url")
	private String url;

	public int getCrawlRate(){
		return crawlRate;
	}

	public String getTitle(){
		return title;
	}

	public String getSlug(){
		return slug;
	}

	public String getUrl(){
		return url;
	}
}