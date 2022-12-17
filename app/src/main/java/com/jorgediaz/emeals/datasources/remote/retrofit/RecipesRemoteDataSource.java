package com.jorgediaz.emeals.datasources.remote.retrofit;

import com.jorgediaz.data.interfaces.IRecipesRemoteDataSource;
import com.jorgediaz.domain.Recipe;
import com.jorgediaz.emeals.datasources.remote.RecipeRemote;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class RecipesRemoteDataSource implements IRecipesRemoteDataSource {

    private final RecipesService recipesService;

    @Inject
    public RecipesRemoteDataSource(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @Override
    public Observable<List<Recipe>> fetchRecipes() {
        return recipesService.fetchFirstRecipe()
                .concatWith(recipesService.fetchSecondRecipe())
                .toList()
                .map(this::toRecipe)
                .toObservable();
    }

    private List<Recipe> toRecipe(List<RecipeRemote> recipeRemoteList) {
        List<Recipe> recipeList = new ArrayList<>();

        for (RecipeRemote recipeRemote : recipeRemoteList) {
            recipeList.add(toRecipe(recipeRemote));
        }

        return recipeList;
    }

    private Recipe toRecipe(RecipeRemote recipeRemote) {
        return new Recipe(recipeRemote.getId());
    }
}
