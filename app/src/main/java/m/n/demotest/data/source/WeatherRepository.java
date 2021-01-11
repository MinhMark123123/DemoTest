package m.n.demotest.data.source;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import m.n.demotest.data.entity.remote.ConsolidatedWeather;
import m.n.demotest.data.entity.remote.WeatherLocation;
import m.n.demotest.data.entity.remote.WeatherLocationResponse;

public interface WeatherRepository {
    Observable<List<WeatherLocation>> getListLocationFromKey(String key);

    Observable<List<ConsolidatedWeather>> getWeatherLocationByDate(String woeid, Date date);

    Observable<WeatherLocationResponse> getWeatherLocationFromWoeid(String woeid);

    Observable<ConsolidatedWeather> getConsolidatedWeatherById(long id);


    void saveListLocation(List<WeatherLocation> consolidatedWeatherEntity);

    void saveWeathers(List<ConsolidatedWeather> consolidatedWeatherEntity);

    void saveWeather(ConsolidatedWeather consolidatedWeatherEntity);
}
