package com.jorgediaz.usecases.interfaces;

import io.reactivex.rxjava3.core.Observable;

public abstract class ObservableUseCase<T, R> {
    public abstract Observable<R> execute(T input);
}
