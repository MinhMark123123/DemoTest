package m.n.demotest.data.source;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import m.n.demotest.data.entity.remote.ConsolidatedWeather;
import m.n.demotest.data.entity.remote.WeatherLocation;
import m.n.demotest.data.entity.remote.WeatherLocationResponse;
import retrofit2.Retrofit;

public class DefaultWeatherRepository implements WeatherRepository {

    WeatherDataSource localSource;
    WeatherDataSource remoteSource;

    @Inject
    public DefaultWeatherRepository(WeatherDataSource localSource, WeatherDataSource remoteSource) {
        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    @Override
    public Observable<List<WeatherLocation>> getListLocationFromKey(String key) {
        return remoteSource.getListLocationFromKey(key)
                .doAfterNext(items -> {
                    for (WeatherLocation item : items) {
                        item.setKeySearch(key);
                    }
                    localSource.saveListLocation(items);
                })
                .onErrorResumeNext(
                        throwable -> {
                            if (throwable instanceof IOException) {
                                return localSource.getListLocationFromKey(key)
                                        .flatMap(listData -> {
                                                    if (listData == null || listData.isEmpty()) {
                                                        throw new Exception("No network available, please check your WiFi or Data connection");
                                                    }
                                                    return Observable.just(listData);
                                                }
                                        );
                            }
                            return Observable.error(throwable);
                        }
                );
    }

    @Override
    public Observable<List<ConsolidatedWeather>> getWeatherLocationByDate(String woeid, Date date) {
        return remoteSource.getWeatherLocationByDate(woeid, date)
                .doAfterNext(items -> localSource.saveWeathers(items))
                .onErrorResumeNext(
                        throwable -> {
                            if (throwable instanceof IOException) {
                                return localSource.getWeatherLocationByDate(woeid, date)
                                        .flatMap(listData -> {
                                                    if (listData == null || listData.isEmpty()) {
                                                        throw new Exception("No network available, please check your WiFi or Data connection");
                                                    }
                                                    return Observable.just(listData);
                                                }
                                        );
                            }
                            return Observable.error(throwable);
                        }
                );
    }

    @Override
    public Observable<WeatherLocationResponse> getWeatherLocationFromWoeid(String woeid) {
        return remoteSource.getWeatherLocationFromWoeid(woeid);
    }

    @Override
    public Observable<ConsolidatedWeather> getConsolidatedWeatherById(long id) {
        return localSource.getConsolidatedWeatherById(id);
    }

    @Override
    public void saveListLocation(List<WeatherLocation> consolidatedWeatherEntity) {
        /*return*/
        localSource.saveListLocation(consolidatedWeatherEntity);
    }

    @Override
    public void saveWeathers(List<ConsolidatedWeather> consolidatedWeatherEntity) {
        /*return*/
        localSource.saveWeathers(consolidatedWeatherEntity);
    }

    @Override
    public void saveWeather(ConsolidatedWeather consolidatedWeatherEntity) {
        /*return*/
        localSource.saveWeather(consolidatedWeatherEntity);
    }
}
