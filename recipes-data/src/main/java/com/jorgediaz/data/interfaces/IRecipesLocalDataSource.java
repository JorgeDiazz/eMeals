package com.jorgediaz.data.interfaces;

import com.jorgediaz.domain.Recipe;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface IRecipesLocalDataSource {

    void insertAll(List<Recipe> recipes);

    Observable<List<Recipe>> getRecipes();

    Single<String> updateRecipeTitle(int recipeId, String newTitle);
}
