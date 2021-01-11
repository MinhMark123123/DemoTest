package m.n.demotest.domain;


import io.reactivex.rxjava3.core.Observable;

public abstract class BaseUseCase<T , R> {
    abstract public Observable<T> execute(R r);
}
