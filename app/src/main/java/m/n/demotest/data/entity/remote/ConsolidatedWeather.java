package m.n.demotest.data.entity.remote;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class ConsolidatedWeather {
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id;

    @SerializedName("visibility")
    @ColumnInfo(name = "visibility")
    private double visibility;

    @SerializedName("created")
    @ColumnInfo(name = "created")
    private String created;

    @SerializedName("applicable_date")
    @ColumnInfo(name = "applicable_date")
    private String applicableDate;

    @SerializedName("wind_direction")
    @ColumnInfo(name = "wind_direction")
    private double windDirection;

    @SerializedName("predictability")
    @ColumnInfo(name = "predictability")
    private int predictability;

    @SerializedName("wind_direction_compass")
    @ColumnInfo(name = "wind_direction_compass")
    private String windDirectionCompass;

    @SerializedName("weather_state_name")
    @ColumnInfo(name = "weather_state_name")
    private String weatherStateName;

    @SerializedName("min_temp")
    @ColumnInfo(name = "min_temp")
    private double minTemp;

    @SerializedName("weather_state_abbr")
    @ColumnInfo(name = "weather_state_abbr")
    private String weatherStateAbbr;

    @SerializedName("the_temp")
    @ColumnInfo(name = "the_temp")
    private double theTemp;

    @SerializedName("humidity")
    @ColumnInfo(name = "humidity")
    private int humidity;

    @SerializedName("wind_speed")
    @ColumnInfo(name = "wind_speed")
    private double windSpeed;

    @SerializedName("max_temp")
    @ColumnInfo(name = "max_temp")
    private double maxTemp;

    @SerializedName("air_pressure")
    @ColumnInfo(name = "air_pressure")
    private float airPressure;

    public void setId(long id) {
        this.id = id;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setApplicableDate(String applicableDate) {
        this.applicableDate = applicableDate;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public void setPredictability(int predictability) {
        this.predictability = predictability;
    }

    public void setWindDirectionCompass(String windDirectionCompass) {
        this.windDirectionCompass = windDirectionCompass;
    }

    public void setWeatherStateName(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public void setWeatherStateAbbr(String weatherStateAbbr) {
        this.weatherStateAbbr = weatherStateAbbr;
    }

    public void setTheTemp(double theTemp) {
        this.theTemp = theTemp;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setAirPressure(float airPressure) {
        this.airPressure = airPressure;
    }

    public double getVisibility() {
        return visibility;
    }

    public String getCreated() {
        return created;
    }

    public String getApplicableDate() {
        return applicableDate;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public int getPredictability() {
        return predictability;
    }

    public String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    public double getTheTemp() {
        return theTemp;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public long getId() {
        return id;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public float getAirPressure() {
        return airPressure;
    }
}