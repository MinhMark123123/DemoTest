package m.n.demotest.data.source.local;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import m.n.demotest.data.entity.remote.ConsolidatedWeather;
import m.n.demotest.data.entity.remote.WeatherLocation;
import m.n.demotest.data.entity.remote.WeatherLocationResponse;
import m.n.demotest.data.source.WeatherDataSource;

public class WeatherLocalSource implements WeatherDataSource {
    private final AppDatabase appDatabase;

    @Inject
    public WeatherLocalSource(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public Observable<List<WeatherLocation>> getListLocationFromKey(String key) {
        Log.d("mmm", "getListLocationFromKey");
        return appDatabase.consolidatedWeatherDao().getWeatherLocation(key);
    }

    @Override
    public Observable<List<ConsolidatedWeather>> getWeatherLocationByDate(String woeid, Date date) {
        Log.d("mmm", "getWeatherLocationByDate");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return appDatabase.consolidatedWeatherDao().getConsolidatedWeatherByDate(formatter.format(date));
    }

    @Override
    public Observable<WeatherLocationResponse> getWeatherLocationFromWoeid(String woeid) {
        return null;
    }

    @Override
    public Observable<ConsolidatedWeather> getConsolidatedWeatherById(long id) {
        return appDatabase.consolidatedWeatherDao().getConsolidatedWeatherById(id);
    }

    @Override
    public void saveListLocation(List<WeatherLocation> weatherLocations) {
        Log.d("mmm", "saveListLocation");
        appDatabase.consolidatedWeatherDao().insertAllLocations(weatherLocations.toArray(new WeatherLocation[0]));
    }

    @Override
    public void saveWeathers(List<ConsolidatedWeather> consolidatedWeatherEntity) {
        Log.d("mmm", "saveWeathers " + consolidatedWeatherEntity.size());
        appDatabase.consolidatedWeatherDao().insertAllWeathers(consolidatedWeatherEntity.toArray(new ConsolidatedWeather[0]));
    }

    @Override
    public void saveWeather(ConsolidatedWeather consolidatedWeatherEntity) {
        Log.d("mmm", "saveWeather");
        Completable.complete();
    }
}
