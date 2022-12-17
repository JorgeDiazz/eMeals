package com.jorgediaz.emeals.datasources.room;

import com.jorgediaz.data.interfaces.IRecipesLocalDataSource;
import com.jorgediaz.domain.Recipe;
import com.jorgediaz.emeals.datasources.room.database.RecipesDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class RecipesLocalDataSource implements IRecipesLocalDataSource {

    private final RecipesDatabase recipesDatabase;

    @Inject
    public RecipesLocalDataSource(RecipesDatabase recipesDatabase) {
        this.recipesDatabase = recipesDatabase;
    }

    @Override
    public void insertAll(List<Recipe> recipes) {
        recipesDatabase.getRecipesDao().insertAll(toRecipeRoomList(recipes));
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return recipesDatabase.getRecipesDao().getRecipes().map(this::toRecipeList);
    }

    private List<Recipe> toRecipeList(List<RecipeRoom> recipeRoomList) {
        List<Recipe> recipeList = new ArrayList<>();

        for (RecipeRoom recipeRoom : recipeRoomList) {
            recipeList.add(toRecipeList(recipeRoom));
        }

        return recipeList;
    }

    private Recipe toRecipeList(RecipeRoom recipeRoom) {
        return new Recipe(recipeRoom.id);
    }

    private List<RecipeRoom> toRecipeRoomList(List<Recipe> recipeList) {
        List<RecipeRoom> recipeRoomList = new ArrayList<>();

        for (Recipe recipe : recipeList) {
            recipeRoomList.add(toRecipeList(recipe));
        }

        return recipeRoomList;
    }

    private RecipeRoom toRecipeList(Recipe recipe) {
        return new RecipeRoom(recipe.getId());
    }
}
