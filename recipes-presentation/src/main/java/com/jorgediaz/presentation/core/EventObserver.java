package com.jorgediaz.presentation.core;


import android.os.Build;

import androidx.lifecycle.Observer;

import java.util.function.Consumer;

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 * <p>
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
public class EventObserver<T> implements Observer<Event<T>> {
    private final Consumer<T> onEventUnhandledContent;

    public EventObserver(Consumer<T> onEventUnhandledContent) {
        this.onEventUnhandledContent = onEventUnhandledContent;
    }

    @Override
    public void onChanged(Event<T> event) {
        T value = event.getContentIfNotHandled();
        if (value != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            onEventUnhandledContent.accept(value);
        }
    }
}