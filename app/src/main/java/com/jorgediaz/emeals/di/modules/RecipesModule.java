package com.jorgediaz.emeals.di.modules;

import com.jorgediaz.domain.Recipe;
import com.jorgediaz.domain.qualifiers.GetRecipes;
import com.jorgediaz.usecases.GetRecipesUseCase;
import com.jorgediaz.usecases.interfaces.ObservableUseCase;

import java.util.List;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module(includes = {RecipesRepositoryModule.class})
@InstallIn(SingletonComponent.class)
public abstract class RecipesModule {

    @Binds
    @GetRecipes
    @Singleton
    abstract ObservableUseCase<Void, List<Recipe>> bindsGetRecipesUseCase(GetRecipesUseCase useCase);
}
