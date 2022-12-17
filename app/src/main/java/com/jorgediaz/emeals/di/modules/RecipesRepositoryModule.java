package com.jorgediaz.emeals.di.modules;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jorgediaz.data.RecipesRepository;
import com.jorgediaz.data.interfaces.IRecipesLocalDataSource;
import com.jorgediaz.data.interfaces.IRecipesRemoteDataSource;
import com.jorgediaz.data.interfaces.IRecipesRepository;
import com.jorgediaz.emeals.datasources.remote.retrofit.RecipesRemoteDataSource;
import com.jorgediaz.emeals.datasources.remote.retrofit.RecipesService;
import com.jorgediaz.emeals.datasources.room.RecipesLocalDataSource;
import com.jorgediaz.emeals.datasources.room.database.RecipesDatabase;
import com.jorgediaz.emeals.di.annotations.RetrofitNullSerializationEnabled;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class RecipesRepositoryModule {

    @Provides
    @Singleton
    RecipesService providesRecipesService(@RetrofitNullSerializationEnabled Retrofit retrofit) {
        return retrofit.create(RecipesService.class);
    }

    @Provides
    @Singleton
    IRecipesRepository providesRecipesRepository(IRecipesLocalDataSource recipesLocalDataSource, IRecipesRemoteDataSource recipesRemoteDataSource) {
        return new RecipesRepository(recipesLocalDataSource, recipesRemoteDataSource);
    }

    @Provides
    @Singleton
    RoomDatabase providesRecipesRoomDatabase(Context context) {
        return Room.databaseBuilder(context, RecipesDatabase.class, "recipes-database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    IRecipesLocalDataSource providesRecipesLocalDataSource(RoomDatabase database) {
        return new RecipesLocalDataSource((RecipesDatabase) database);
    }

    @Provides
    @Singleton
    IRecipesRemoteDataSource providesRecipesRemoteDataSource(RecipesRemoteDataSource recipesRemoteDataSource) {
        return recipesRemoteDataSource;
    }
}
