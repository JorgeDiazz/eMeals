package com.jorgediaz.presentation.core;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        // no-op by default
    }

    @Override
    public void onNext(@NonNull T t) {
        // no-op by default
    }

    @Override
    public void onError(@NonNull Throwable e) {
        // no-op by default
    }

    @Override
    public void onComplete() {
        // no-op by default
    }
}
