package com.jorgediaz.emeals.di.modules;

import com.jorgediaz.emeals.logger.EMealsDebugTree;
import com.jorgediaz.emeals.logger.EMealsLogger;
import com.jorgediaz.presentation.core.Logger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LoggerModule {
    @Provides
    @Singleton
    public Logger providesLoggerImplementation() {
        EMealsDebugTree tree = new EMealsDebugTree();
        return new EMealsLogger(tree);
    }
}
