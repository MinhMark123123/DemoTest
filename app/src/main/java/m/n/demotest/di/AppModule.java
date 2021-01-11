package m.n.demotest.di;


import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.lang.annotation.Retention;
import java.math.RoundingMode;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import m.n.demotest.data.source.DefaultWeatherRepository;
import m.n.demotest.data.source.WeatherDataSource;
import m.n.demotest.data.source.WeatherRepository;
import m.n.demotest.data.source.local.AppDatabase;
import m.n.demotest.data.source.local.WeatherLocalSource;
import m.n.demotest.data.source.remote.Api;
import m.n.demotest.data.source.remote.WeatherRemoteDataSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.math.BigDecimal.valueOf;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {
    @Qualifier
    @Retention(RUNTIME)
    private @interface RemoteDataSource {
    }

    @Qualifier
    @Retention(RUNTIME)
    private @interface LocalDataSource {
    }

    @Singleton
    @Provides
    Api provideApi() {
        //setup gson formatter
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
            Log.d("tag", "" + valueOf(src));
            Double value = valueOf(src).setScale(3, RoundingMode.HALF_UP).doubleValue();
            return new JsonPrimitive(value);
        }).create();
        //init retrofit
        return new Retrofit.Builder()
                .baseUrl("https://www.metaweather.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(Api.class);
    }

    @Singleton
    @Provides
    AppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, "app_database").build();
    }

    @Singleton
    @RemoteDataSource
    @Provides
    WeatherDataSource provideRemoteSource(Api api) {
        return new WeatherRemoteDataSource(api);
    }

    @Singleton
    @LocalDataSource
    @Provides
    WeatherDataSource provideLocalSource(AppDatabase appDatabase) {
        return new WeatherLocalSource(appDatabase);
    }


    @Singleton
    @Provides
    WeatherRepository provideWeatherRepository(
            @RemoteDataSource WeatherDataSource remoteSrc,
            @LocalDataSource WeatherDataSource localSrc
    ) {
        return new DefaultWeatherRepository(localSrc, remoteSrc);
    }
}
