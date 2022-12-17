package com.jorgediaz.presentation.core;

import com.jorgediaz.domain.Recipe;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class BaseObserver implements Observer<List<Recipe>> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        // no-op by default
    }

    @Override
    public void onNext(@NonNull List<Recipe> recipes) {
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
