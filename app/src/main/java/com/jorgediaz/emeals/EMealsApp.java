package com.jorgediaz.emeals;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.VmPolicy;

import androidx.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jorgediaz.emeals.di.components.AppComponent;

import dagger.hilt.EntryPoints;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class EMealsApp extends MultiDexApplication {

    public void onCreate() {
        initializeStrictMode();
        initializeComponent();
        initializeLibraries();
        super.onCreate();
    }

    private void initializeStrictMode() {
        if (BuildConfig.DEBUG) {
            ThreadPolicy.Builder threadPolicy = new ThreadPolicy.Builder();
            StrictMode.setThreadPolicy(
                    threadPolicy
                            .detectAll()
                            .permitDiskReads()
                            .permitDiskWrites()
                            .penaltyLog()
                            .build()
            );


            VmPolicy.Builder vmPolicy = new VmPolicy.Builder();
            StrictMode.setVmPolicy(
                    vmPolicy
                            .detectLeakedSqlLiteObjects()
                            .detectLeakedClosableObjects()
                            .penaltyLog()
                            .build()
            );
        }
    }

    private void initializeComponent() {
        EntryPoints.get(this, AppComponent.class);
    }

    private void initializeLibraries() {
        Fresco.initialize(this);
    }
}
