package com.jorgediaz.presentation.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jorgediaz.domain.NutritionalInformation;
import com.jorgediaz.domain.Recipe;
import com.jorgediaz.domain.SideRecipe;
import com.jorgediaz.domain.qualifiers.GetRecipes;
import com.jorgediaz.usecases.interfaces.ObservableUseCase;
import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.core.AppResources;
import com.jorgediaz.presentation.core.BaseObserver;
import com.jorgediaz.presentation.core.Event;
import com.jorgediaz.presentation.core.Logger;
import com.jorgediaz.presentation.ui.model.NutritionalInformationUiModel;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;
import com.jorgediaz.presentation.ui.model.SideRecipeUiModel;
import com.jorgediaz.presentation.ui.news.RecipesNews;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class RecipesViewModel extends ViewModel {

    private final MutableLiveData<List<RecipeUiModel>> _liveData = new MutableLiveData<>();
    public final LiveData<List<RecipeUiModel>> liveData = _liveData;

    private final MutableLiveData<Event<RecipesNews>> _news = new MutableLiveData<>();
    public final LiveData<Event<RecipesNews>> news = _news;

    private final ObservableUseCase<Void, List<Recipe>> getRecipesUseCase;
    private final Logger logger;
    private final AppResources appResources;

    @Inject
    public RecipesViewModel(@GetRecipes ObservableUseCase<Void, List<Recipe>> getRecipesUseCase, Logger logger, AppResources appResources) {
        this.getRecipesUseCase = getRecipesUseCase;
        this.logger = logger;
        this.appResources = appResources;
    }

    public void onViewActive() {
        loadRecipes();
    }

    private void loadRecipes() {
        getRecipesUseCase.execute(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::toRecipeUiModelList)
                .subscribe(new BaseObserver<List<RecipeUiModel>>() {
                    @Override
                    public void onNext(List<RecipeUiModel> recipes) {
                        _liveData.setValue(recipes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        logger.e("Error when loading recipes.", e);

                        RecipesNews news = new RecipesNews.ErrorLoadingRecipes(e.getMessage());
                        _news.setValue(new Event<>(news));
                    }
                });
    }

    public void showSideRecipeDetails(RecipeUiModel recipeUiModel) {
        RecipesNews news = new RecipesNews.ShowSideRecipeDetails(recipeUiModel);
        _news.setValue(new Event<>(news));
    }

    private List<RecipeUiModel> toRecipeUiModelList(List<Recipe> recipes) {
        List<RecipeUiModel> recipeUiModelList = new ArrayList<>();

        for (Recipe recipe : recipes) {
            recipeUiModelList.add(toRecipeUiModel(recipe));
        }

        return recipeUiModelList;
    }

    private RecipeUiModel toRecipeUiModel(Recipe recipe) {
        return new RecipeUiModel(
                recipe.getId(),
                recipe.getPrimaryPictureUrl(),
                recipe.getPrimaryPictureUrlMedium(),
                recipe.getRating(),
                appResources.getString(R.string.recipe_minutes_time, recipe.getTime()),
                recipe.getServings() > 0 ? String.valueOf(recipe.getServings()) : appResources.getString(R.string.servings_default_value),
                recipe.getStyle().isEmpty() ? appResources.getString(R.string.style_default_value) : recipe.getStyle(),
                recipe.getTitle(),
                toNutritionalInformationList(recipe.getNutritionalInformationList()),
                recipe.getIngredients(),
                recipe.getInstructions(),
                toSideRecipeUiModel(recipe.getSideRecipe())
        );
    }

    private List<NutritionalInformationUiModel> toNutritionalInformationList(List<NutritionalInformation> nutritionalInformationList) {
        List<NutritionalInformationUiModel> nutritionalInformationUiModel = new ArrayList<>();

        for (NutritionalInformation nutritionalInformation : nutritionalInformationList) {
            nutritionalInformationUiModel.add(toNutritionalInformationUiModel(nutritionalInformation));
        }

        return nutritionalInformationUiModel;
    }

    private NutritionalInformationUiModel toNutritionalInformationUiModel(NutritionalInformation nutritionalInformation) {
        return new NutritionalInformationUiModel(nutritionalInformation.getName(), nutritionalInformation.getValue(), nutritionalInformation.getUnit());
    }

    private SideRecipeUiModel toSideRecipeUiModel(SideRecipe sideRecipe) {
        return new SideRecipeUiModel(
                sideRecipe.getServings(),
                sideRecipe.getStyle(),
                sideRecipe.getTitle(),
                toNutritionalInformationList(sideRecipe.getNutritionalInformationUiModelList()),
                sideRecipe.getIngredients(),
                sideRecipe.getInstructions()
        );
    }
}