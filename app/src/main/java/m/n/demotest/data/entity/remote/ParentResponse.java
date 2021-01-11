package m.n.demotest.data.entity.remote;

import com.google.gson.annotations.SerializedName;

public class ParentResponse {

	@SerializedName("latt_long")
	private String lattLong;

	@SerializedName("woeid")
	private int woeid;

	@SerializedName("title")
	private String title;

	@SerializedName("location_type")
	private String locationType;

	public String getLattLong(){
		return lattLong;
	}

	public int getWoeid(){
		return woeid;
	}

	public String getTitle(){
		return title;
	}

	public String getLocationType(){
		return locationType;
	}
}