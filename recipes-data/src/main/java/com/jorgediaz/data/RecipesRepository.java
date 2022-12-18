package com.jorgediaz.data;

import com.jorgediaz.data.interfaces.IRecipesLocalDataSource;
import com.jorgediaz.data.interfaces.IRecipesRemoteDataSource;
import com.jorgediaz.data.interfaces.IRecipesRepository;
import com.jorgediaz.domain.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * Represents the repository for recipes.
 */
public class RecipesRepository implements IRecipesRepository {

    private final IRecipesLocalDataSource recipesLocalDataSource;
    private final IRecipesRemoteDataSource recipesRemoteDataSource;

    @Inject
    public RecipesRepository(IRecipesLocalDataSource recipesLocalDataSource, IRecipesRemoteDataSource recipesRemoteDataSource) {
        this.recipesLocalDataSource = recipesLocalDataSource;
        this.recipesRemoteDataSource = recipesRemoteDataSource;
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return recipesLocalDataSource.getRecipes()
                .flatMap(recipes -> {
                    if (recipes.isEmpty()) {
                        return recipesRemoteDataSource.fetchRecipes()
                                .doOnNext(recipesLocalDataSource::insertAll);
                    }

                    return Observable.just(recipes);
                });
    }

    @Override
    public Single<String> updateRecipeTitle(int recipeId, String newTitle) {
        return recipesLocalDataSource.updateRecipeTitle(recipeId, newTitle);
    }
}