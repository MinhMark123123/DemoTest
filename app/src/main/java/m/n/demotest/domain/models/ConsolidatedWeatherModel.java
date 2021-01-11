package m.n.demotest.domain.models;

import com.google.gson.annotations.SerializedName;

public class ConsolidatedWeatherModel {
    private double visibility;

    private String created;

    private String applicableDate;

    private double windDirection;

    private int predictability;

    private String windDirectionCompass;

    private String weatherStateName;

    private double minTemp;

    private String weatherStateAbbr;

    private double theTemp;

    private int humidity;

    private double windSpeed;

    private long id;

    private double maxTemp;

    private int airPressure;

    public ConsolidatedWeatherModel(double visibility, String created, String applicableDate, double windDirection, int predictability, String windDirectionCompass, String weatherStateName, double minTemp, String weatherStateAbbr, double theTemp, int humidity, double windSpeed, long id, double maxTemp, int airPressure) {
        this.visibility = visibility;
        this.created = created;
        this.applicableDate = applicableDate;
        this.windDirection = windDirection;
        this.predictability = predictability;
        this.windDirectionCompass = windDirectionCompass;
        this.weatherStateName = weatherStateName;
        this.minTemp = minTemp;
        this.weatherStateAbbr = weatherStateAbbr;
        this.theTemp = theTemp;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.id = id;
        this.maxTemp = maxTemp;
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

    public int getAirPressure() {
        return airPressure;
    }
}
