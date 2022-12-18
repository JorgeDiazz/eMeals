package com.jorgediaz.emeals.di.modules;

import android.app.Application;
import android.content.Context;

import com.jorgediaz.presentation.core.AppResources;
import com.jorgediaz.emeals.data.EMealsResources;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class BaseModule {
    @Binds
    public abstract Context bindContext(Application eMealsApp);

    @Binds
    public abstract AppResources bindResources(EMealsResources eMealsResources);
}
