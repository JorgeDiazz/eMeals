package com.jorgediaz.emeals.logger;

import com.jorgediaz.presentation.core.Logger;

import javax.inject.Inject;

import timber.log.Timber;

public class EMealsLogger implements Logger {

    @Inject
    public EMealsLogger(Timber.Tree tree) {
        Timber.plant(tree);
    }

    @Override
    public void v(String message, Throwable throwable) {
        Timber.v(throwable, message);
    }

    @Override
    public void d(String message, Throwable throwable) {
        Timber.d(throwable, message);
    }

    @Override
    public void i(String message, Throwable throwable) {
        Timber.i(throwable, message);
    }

    @Override
    public void w(String message, Throwable throwable) {
        Timber.w(throwable, message);
    }

    @Override
    public void e(String message, Throwable throwable) {
        Timber.e(throwable, message);
    }

    @Override
    public void http(String url, String method, String request, String response, Integer statusCode) {
        Timber.d(method + ":" + url + ", " + request + "\n" + response + "\n" + statusCode);
    }
}
