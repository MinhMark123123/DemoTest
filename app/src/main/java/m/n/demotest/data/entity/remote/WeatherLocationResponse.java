package m.n.demotest.data.entity.remote;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class WeatherLocationResponse {

	@SerializedName("sun_set")
	private String sunSet;

	@SerializedName("parent")
	private ParentResponse parent;

	@SerializedName("sources")
	private List<SourcesResponse> sources;

	@SerializedName("latt_long")
	private String lattLong;

	@SerializedName("timezone")
	private String timezone;

	@SerializedName("timezone_name")
	private String timezoneName;

	@SerializedName("woeid")
	private int woeid;

	@SerializedName("sun_rise")
	private String sunRise;

	@SerializedName("consolidated_weather")
	private List<ConsolidatedWeather> consolidatedWeather;

	@SerializedName("time")
	private String time;

	@SerializedName("title")
	private String title;

	@SerializedName("location_type")
	private String locationType;

	public String getSunSet(){
		return sunSet;
	}

	public ParentResponse getParent(){
		return parent;
	}

	public List<SourcesResponse> getSources(){
		return sources;
	}

	public String getLattLong(){
		return lattLong;
	}

	public String getTimezone(){
		return timezone;
	}

	public String getTimezoneName(){
		return timezoneName;
	}

	public int getWoeid(){
		return woeid;
	}

	public String getSunRise(){
		return sunRise;
	}

	public List<ConsolidatedWeather> getConsolidatedWeather(){
		return consolidatedWeather;
	}

	public String getTime(){
		return time;
	}

	public String getTitle(){
		return title;
	}

	public String getLocationType(){
		return locationType;
	}
}