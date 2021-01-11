package m.n.demotest.domain.mapper;

abstract public class BaseMapper<T, R> {
    public abstract T from(R r);
    public abstract R to(T t);
}
