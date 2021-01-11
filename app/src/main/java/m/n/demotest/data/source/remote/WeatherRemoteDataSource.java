package m.n.demotest.data.source.remote;

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

public class WeatherRemoteDataSource implements WeatherDataSource {
    private final Api api;

    @Inject
    public WeatherRemoteDataSource(Api api) {
        this.api = api;
    }


    @Override
    public Observable<List<WeatherLocation>> getListLocationFromKey(String key) {
        return api.queryLocation(key).flatMap(listResponse -> Observable.just(listResponse.body()));
    }

    @Override
    public Observable<List<ConsolidatedWeather>> getWeatherLocationByDate(String woeid, Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return api.queryWeatherByDate(woeid, formatter.format(date)).flatMap(listResponse -> Observable.just(listResponse.body()));
    }

    @Override
    public Observable<WeatherLocationResponse> getWeatherLocationFromWoeid(String woeid) {
        return api.queryWeatherLocation(woeid).flatMap(response -> Observable.just(response.body()));
    }

    @Override
    public Observable<ConsolidatedWeather> getConsolidatedWeatherById(long id) {
        return null;
    }

    @Override
    public void saveListLocation(List<WeatherLocation> consolidatedWeatherEntity) {

    }

    @Override
    public void saveWeathers(List<ConsolidatedWeather> consolidatedWeatherEntity) {

    }

    @Override
    public void saveWeather(ConsolidatedWeather consolidatedWeatherEntity) {

    }
}
