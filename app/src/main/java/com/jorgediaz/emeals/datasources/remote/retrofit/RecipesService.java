package com.jorgediaz.emeals.datasources.remote.retrofit;

import com.jorgediaz.emeals.datasources.remote.RecipeRemote;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface RecipesService {

    @GET("v1/recipes/46168/46168_295947.json")
    Single<RecipeRemote> fetchFirstRecipe();

    @GET("v1/recipes/37767/37767_241270.json")
    Single<RecipeRemote> fetchSecondRecipe();
}
