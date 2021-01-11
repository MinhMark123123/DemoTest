package m.n.demotest.domain.usecase;


import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import m.n.demotest.data.entity.remote.ConsolidatedWeather;
import m.n.demotest.data.source.WeatherRepository;
import m.n.demotest.domain.BaseUseCase;

public class GetWeatherLocationUseCase extends BaseUseCase<List<ConsolidatedWeather>, GetWeatherLocationUseCase.Params> {
    private final WeatherRepository repository;

    @Inject
    public GetWeatherLocationUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<ConsolidatedWeather>> execute(Params params) {
        return repository.getListLocationFromKey(params.keySearch)
                .flatMap(listLocation -> repository.getWeatherLocationByDate(String.valueOf(listLocation.get(0).getWoeid()), params.date));
    }

    public static class Params {
        private final String keySearch;
        private final Date date;

        public Params(String keySearch, Date date) {
            this.keySearch = keySearch;
            this.date = date;
        }

        public String getKeySearch() {
            return keySearch;
        }

        public Date getDate() {
            return date;
        }
    }
}