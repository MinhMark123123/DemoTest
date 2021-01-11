package m.n.demotest.data.source.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import m.n.demotest.data.entity.remote.ConsolidatedWeather;
import m.n.demotest.data.entity.remote.WeatherLocation;

@Database(entities = {ConsolidatedWeather.class, WeatherLocation.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ConsolidatedWeatherDao consolidatedWeatherDao();
}