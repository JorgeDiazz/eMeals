package com.jorgediaz.emeals.di.modules;


import com.jorgediaz.emeals.BuildConfig;
import com.jorgediaz.emeals.core.interfaces.Logger;
import com.jorgediaz.emeals.core.network.interceptors.ServerInterceptor;
import com.jorgediaz.emeals.di.annotations.BasePath;
import com.jorgediaz.emeals.di.annotations.RetrofitEMeals;
import com.jorgediaz.emeals.di.annotations.RetrofitNullSerializationEnabled;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import kotlin.jvm.JvmSuppressWildcards;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    private static final String FLAVOR_TARGET_INTERNAL = "internal";
    private static final long EXTERNAL_REQUEST_TIMEOUT_IN_SECONDS = 60L;
    private static final long INTERNAL_REQUEST_TIMEOUT_IN_SECONDS = 20L;

    @Provides
    public OkHttpClient.Builder providesOkHttpClientBuilder() {
        long timeout =
                BuildConfig.FLAVOR_target == FLAVOR_TARGET_INTERNAL ? INTERNAL_REQUEST_TIMEOUT_IN_SECONDS : EXTERNAL_REQUEST_TIMEOUT_IN_SECONDS;

        return new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS);
    }

    @Provides
    @Singleton
    public final Interceptor providesLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        HttpLoggingInterceptor.Level level = BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
        logging.setLevel(level);

        return logging;
    }

    @Provides
    @Singleton
    @RetrofitEMeals
    @JvmSuppressWildcards
    @NotNull
    public final OkHttpClient providesOkHttpClient(OkHttpClient.Builder builder, Interceptor loggingInterceptor, Logger logger) {
        ServerInterceptor serverInterceptor = new ServerInterceptor(logger);
        return builder.addInterceptor(serverInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    @BasePath
    public String providesBasePath() {
        return BuildConfig.BASE_URL;
    }

    @Provides
    @Singleton
    @RetrofitNullSerializationEnabled
    public Retrofit providesRetrofitNullSerializationEnabled(
            @RetrofitEMeals OkHttpClient okHttpClient,
            @BasePath String basePath
    ) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(basePath)
                .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
                .build();
    }
}
