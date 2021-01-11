package m.n.demotest.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import m.n.demotest.data.entity.remote.ConsolidatedWeather;
import m.n.demotest.data.entity.remote.WeatherLocation;

@Dao
public interface ConsolidatedWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllWeathers(ConsolidatedWeather... consolidatedWeatherEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllLocations(WeatherLocation... weatherLocation);

    /*@Query("SELECT * FROM ConsolidatedWeather")
    Observable<List<ConsolidatedWeather>> getAll();*/

    @Query("SELECT * FROM WeatherLocation WHERE key_search IN (:key)")
    Observable<List<WeatherLocation>> getWeatherLocation(String key);

    @Query("SELECT * FROM ConsolidatedWeather WHERE applicable_date IN (:date)")
    Observable<List<ConsolidatedWeather>> getConsolidatedWeatherByDate(String date);

    @Query("SELECT * FROM ConsolidatedWeather WHERE id IN (:id)")
    Observable<ConsolidatedWeather> getConsolidatedWeatherById(long id);

}
