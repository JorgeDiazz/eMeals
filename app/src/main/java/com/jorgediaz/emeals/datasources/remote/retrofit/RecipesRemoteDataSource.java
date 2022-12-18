package com.jorgediaz.emeals.datasources.remote.retrofit;

import com.jorgediaz.data.interfaces.IRecipesRemoteDataSource;
import com.jorgediaz.domain.NutritionalInformation;
import com.jorgediaz.domain.Recipe;
import com.jorgediaz.domain.SideRecipe;
import com.jorgediaz.emeals.datasources.remote.NutritionalInformationRemote;
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
        Recipe recipe = new Recipe();

        recipe.setId(recipeRemote.getId());
        recipe.setPrimaryPictureUrl(recipeRemote.getMain().getPrimaryPictureUrl());
        recipe.setPrimaryPictureUrlMedium(recipeRemote.getMain().getPrimaryPictureUrlMedium());
        recipe.setRating(recipeRemote.getMain().getRating());
        recipe.setTime(recipeRemote.getMain().getCookTime() + recipeRemote.getMain().getPrepTime());
        recipe.setServings(recipeRemote.getMain().getServings());
        recipe.setStyle(recipeRemote.getMain().getStyle());
        recipe.setTitle(recipeRemote.getMain().getTitle());
        recipe.setNutritionalInformationList(toNutritionalInformationList(recipeRemote.getMain().getNutritionalInformation()));
        recipe.setIngredients(new ArrayList<>(recipeRemote.getMain().getIngredients().values()));
        recipe.setInstructions(new ArrayList<>(recipeRemote.getMain().getInstructions().values()));


        SideRecipe sideRecipe = new SideRecipe();

        sideRecipe.setServings(recipeRemote.getSide().getServings());
        sideRecipe.setStyle(recipeRemote.getSide().getStyle());
        sideRecipe.setTitle(recipeRemote.getSide().getTitle());
        sideRecipe.setNutritionalInformationList(toNutritionalInformationList(recipeRemote.getSide().getNutritionalInformation()));
        sideRecipe.setIngredients(new ArrayList<>(recipeRemote.getSide().getIngredients().values()));
        sideRecipe.setInstructions(new ArrayList<>(recipeRemote.getSide().getInstructions().values()));

        recipe.setSideRecipe(sideRecipe);

        return recipe;
    }

    private List<NutritionalInformation> toNutritionalInformationList(List<NutritionalInformationRemote> nutritionalInformationRemoteList) {
        if (nutritionalInformationRemoteList == null) {
            return new ArrayList<>();
        }

        List<NutritionalInformation> nutritionalInformationList = new ArrayList<>();

        for (NutritionalInformationRemote nutritionalInformationRemote : nutritionalInformationRemoteList) {
            nutritionalInformationList.add(toNutritionalInformation(nutritionalInformationRemote));
        }

        return nutritionalInformationList;
    }

    private NutritionalInformation toNutritionalInformation(NutritionalInformationRemote nutritionalInformationRemote) {
        return new NutritionalInformation(nutritionalInformationRemote.getName(), nutritionalInformationRemote.getValue(), nutritionalInformationRemote.getUnit());
    }

}
