package com.jorgediaz.emeals.logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import timber.log.Timber;

/**
 * Represents the debug tree used by the app.
 */
public class EMealsDebugTree extends Timber.DebugTree {
    @Nullable
    @Override
    protected String createStackElementTag(@NonNull StackTraceElement element) {
        return getCleanClassName(newStackTraceElement());
    }

    private String getCleanClassName(StackTraceElement element) {
        return String.format("C:%s:%s", element.getClassName(), element.getLineNumber());
    }

    private StackTraceElement newStackTraceElement() {
        StackTraceElement[] elements = (new Throwable()).getStackTrace();
        return elements[9];
    }


}
