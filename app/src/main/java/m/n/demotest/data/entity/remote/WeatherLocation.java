package m.n.demotest.data.entity.remote;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class WeatherLocation {
    @PrimaryKey
    @ColumnInfo(name = "woeid")
    @SerializedName("woeid")
    private int woeid;

    @ColumnInfo(name = "latt_long")
    @SerializedName("latt_long")
    private String lattLong;


    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "location_type")
    @SerializedName("location_type")
    private String locationType;

    @ColumnInfo(name = "key_search")
    @SerializedName("key_search")
    private String keySearch;

    public String getLattLong() {
        return lattLong;
    }

    public int getWoeid() {
        return woeid;
    }

    public String getTitle() {
        return title;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setWoeid(int woeid) {
        this.woeid = woeid;
    }

    public void setLattLong(String lattLong) {
        this.lattLong = lattLong;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }


}