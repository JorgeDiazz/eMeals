package com.jorgediaz.emeals.datasources.room;

import com.jorgediaz.data.interfaces.IRecipesLocalDataSource;
import com.jorgediaz.domain.NutritionalInformation;
import com.jorgediaz.domain.Recipe;
import com.jorgediaz.domain.SideRecipe;
import com.jorgediaz.emeals.datasources.room.database.RecipesDatabase;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class RecipesLocalDataSource implements IRecipesLocalDataSource {

    private final RecipesDatabase recipesDatabase;
    private final Moshi moshi;

    @Inject
    public RecipesLocalDataSource(RecipesDatabase recipesDatabase, Moshi moshi) {
        this.recipesDatabase = recipesDatabase;
        this.moshi = moshi;
    }

    @Override
    public void insertAll(List<Recipe> recipes) {
        recipesDatabase.getRecipesDao().insertAll(toRecipeRoomList(recipes));
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return recipesDatabase.getRecipesDao().getRecipes().map(this::toRecipeList);
    }

    private List<Recipe> toRecipeList(List<RecipeRoom> recipeRoomList) throws IOException {
        List<Recipe> recipeList = new ArrayList<>();

        for (RecipeRoom recipeRoom : recipeRoomList) {
            recipeList.add(toRecipe(recipeRoom));
        }

        return recipeList;
    }

    private Recipe toRecipe(RecipeRoom recipeRoom) throws IOException {
        Recipe recipe = new Recipe();

        recipe.setId(recipeRoom.id);
        recipe.setPrimaryPictureUrl(recipeRoom.primaryPictureUrl);
        recipe.setPrimaryPictureUrlMedium(recipeRoom.primaryPictureUrlMedium);
        recipe.setRating(recipeRoom.rating);
        recipe.setTime(recipeRoom.time);
        recipe.setServings(recipeRoom.servings);
        recipe.setStyle(recipeRoom.style);
        recipe.setTitle(recipeRoom.title);

        Type listNutritionalInformation = Types.newParameterizedType(List.class, NutritionalInformation.class);
        JsonAdapter<List<NutritionalInformation>> jsonAdapterNutritionalInformation = moshi.adapter(listNutritionalInformation);
        recipe.setNutritionalInformationList(jsonAdapterNutritionalInformation.fromJson(recipeRoom.nutritionalInformation));

        Type listString = Types.newParameterizedType(List.class, String.class);
        JsonAdapter<List<String>> jsonAdapterString = moshi.adapter(listString);
        recipe.setIngredients(jsonAdapterString.fromJson(recipeRoom.ingredients));
        recipe.setInstructions(jsonAdapterString.fromJson(recipeRoom.instructions));


        SideRecipe sideRecipe = new SideRecipe();

        sideRecipe.setServings(recipeRoom.sideServings);
        sideRecipe.setStyle(recipeRoom.sideStyle);
        sideRecipe.setTitle(recipeRoom.sideTitle);
        sideRecipe.setNutritionalInformationList(jsonAdapterNutritionalInformation.fromJson(recipeRoom.sideNutritionalInformation));
        sideRecipe.setIngredients(jsonAdapterString.fromJson(recipeRoom.sideIngredients));
        sideRecipe.setInstructions(jsonAdapterString.fromJson(recipeRoom.sideInstructions));

        recipe.setSideRecipe(sideRecipe);

        return recipe;
    }

    private List<RecipeRoom> toRecipeRoomList(List<Recipe> recipeList) {
        List<RecipeRoom> recipeRoomList = new ArrayList<>();

        for (Recipe recipe : recipeList) {
            recipeRoomList.add(toRecipe(recipe));
        }

        return recipeRoomList;
    }

    private RecipeRoom toRecipe(Recipe recipe) {
        RecipeRoom recipeRoom = new RecipeRoom();

        recipeRoom.id = recipe.getId();
        recipeRoom.primaryPictureUrl = recipe.getPrimaryPictureUrl();
        recipeRoom.primaryPictureUrlMedium = recipe.getPrimaryPictureUrlMedium();
        recipeRoom.rating = recipe.getRating();
        recipeRoom.time = recipe.getTime();
        recipeRoom.servings = recipe.getServings();
        recipeRoom.style = recipe.getStyle();
        recipeRoom.title = recipe.getTitle();

        Type listNutritionalInformation = Types.newParameterizedType(List.class, NutritionalInformation.class);
        JsonAdapter<List<NutritionalInformation>> jsonAdapterNutritionalInformation = moshi.adapter(listNutritionalInformation);
        recipeRoom.nutritionalInformation = jsonAdapterNutritionalInformation.toJson(recipe.getNutritionalInformationList());

        Type listString = Types.newParameterizedType(List.class, String.class);
        JsonAdapter<List<String>> jsonAdapterString = moshi.adapter(listString);
        recipeRoom.ingredients = jsonAdapterString.toJson(recipe.getIngredients());
        recipeRoom.instructions = jsonAdapterString.toJson(recipe.getInstructions());

        recipeRoom.sideServings = recipe.getSideRecipe().getServings();
        recipeRoom.sideStyle = recipe.getSideRecipe().getStyle();
        recipeRoom.sideTitle = recipe.getSideRecipe().getTitle();
        recipeRoom.sideNutritionalInformation = jsonAdapterNutritionalInformation.toJson(recipe.getSideRecipe().getNutritionalInformationUiModelList());
        recipeRoom.sideIngredients = jsonAdapterString.toJson(recipe.getSideRecipe().getIngredients());
        recipeRoom.sideInstructions = jsonAdapterString.toJson(recipe.getSideRecipe().getInstructions());

        return recipeRoom;
    }
}
