package com.jorgediaz.usecases.interfaces;

import io.reactivex.rxjava3.core.Single;

public abstract class SingleUseCase<T, R> {
    public abstract Single<R> execute(T input);
}

