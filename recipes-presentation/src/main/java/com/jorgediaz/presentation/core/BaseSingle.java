package com.jorgediaz.presentation.core;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class BaseSingle<T> extends DisposableSingleObserver<T> {
    @Override
    public void onSuccess(@NonNull T t) {
        dispose();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        dispose();
    }
}
