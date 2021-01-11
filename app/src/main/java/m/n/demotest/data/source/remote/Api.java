package m.n.demotest.data.source.remote;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import m.n.demotest.data.entity.remote.ConsolidatedWeather;
import m.n.demotest.data.entity.remote.WeatherLocation;
import m.n.demotest.data.entity.remote.WeatherLocationResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("/api/location/search")
    Observable<Response<List<WeatherLocation>>> queryLocation(@Query("query") String key);

    @GET("/api/location/{woeid}/")
    Observable<Response<WeatherLocationResponse>> queryWeatherLocation(@Path("woeid") String woeid);

    @GET("/api/location/{woeid}/{date}")
    Observable<Response<List<ConsolidatedWeather>>> queryWeatherByDate(@Path("woeid") String woeid, @Path("date") String date);
}
