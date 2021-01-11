package m.n.demotest.ui.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import m.n.demotest.commons.BaseViewModel
import m.n.demotest.data.AppError
import m.n.demotest.data.entity.remote.ConsolidatedWeather
import m.n.demotest.domain.usecase.GetWeatherUseCase
import javax.inject.Inject

class DetailViewModel @ViewModelInject constructor(
        var getWeatherUseCase: GetWeatherUseCase,
        //@Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _consolidatedWeather = MutableLiveData<ConsolidatedWeather>()
    val consolidatedWeather: LiveData<ConsolidatedWeather> = _consolidatedWeather
    var id: Long = 0
    override fun onStart() {
        super.onStart()
        disposables.add(getWeatherUseCase.execute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ConsolidatedWeather>() {
                    override fun onNext(t: ConsolidatedWeather?) {
                        _consolidatedWeather.postValue(t)
                    }

                    override fun onError(e: Throwable?) {
                        postError(AppError(0, e?.message))
                    }

                    override fun onComplete() {
                    }

                }))
    }
}