package com.jorgediaz.usecases.interfaces;

import io.reactivex.rxjava3.core.Completable;

public abstract class CompletableUseCase<T> {
    public abstract Completable execute(T input);
}
