package m.n.demotest.commons;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import m.n.demotest.data.AppError;

abstract public class BaseViewModel extends ViewModel implements LifecycleObserver {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<AppError> appError = new MutableLiveData<>();

    public LiveData<AppError> errorLiveData() {
        return appError;
    }

    protected void postError(AppError error) {
        appError.postValue(error);
    }

    protected CompositeDisposable getDisposables() {
        return disposables;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop() {
        getDisposables().clear();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
    }
}
