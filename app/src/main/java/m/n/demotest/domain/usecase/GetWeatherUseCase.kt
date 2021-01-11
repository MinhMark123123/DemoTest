package m.n.demotest.domain.usecase

import io.reactivex.rxjava3.core.Observable
import m.n.demotest.data.entity.remote.ConsolidatedWeather
import m.n.demotest.data.source.WeatherRepository
import m.n.demotest.domain.BaseUseCase
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(var repository: WeatherRepository) : BaseUseCase<ConsolidatedWeather, Long>() {
    override fun execute(id: Long?): Observable<ConsolidatedWeather> = repository.getConsolidatedWeatherById(id
            ?: 0)
}