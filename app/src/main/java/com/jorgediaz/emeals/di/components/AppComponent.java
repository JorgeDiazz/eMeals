package com.jorgediaz.emeals.di.components;

import android.app.Application;

import com.jorgediaz.emeals.di.modules.ActivityAggregatorModule;
import com.jorgediaz.emeals.di.modules.BaseModule;
import com.jorgediaz.emeals.di.modules.FragmentModule;
import com.jorgediaz.emeals.di.modules.LoggerModule;
import com.jorgediaz.emeals.di.modules.NetworkModule;
import com.jorgediaz.emeals.di.modules.RecipesModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * Represents the modules provided to the app.
 */
@InstallIn(SingletonComponent.class)
@EntryPoint
@Component(modules = {BaseModule.class,
        LoggerModule.class,
        NetworkModule.class,
        ActivityAggregatorModule.class,
        FragmentModule.class,
        RecipesModule.class})
public interface AppComponent {

    @Component.Builder
    public interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
