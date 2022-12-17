package com.jorgediaz.presentation.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jorgediaz.domain.Recipe;
import com.jorgediaz.domain.qualifiers.GetRecipes;
import com.jorgediaz.presentation.core.BaseObserver;
import com.jorgediaz.presentation.core.Event;
import com.jorgediaz.presentation.core.Logger;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;
import com.jorgediaz.presentation.ui.news.RecipesNews;
import com.jorgediaz.usecases.interfaces.ObservableUseCase;

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

    @Inject
    public RecipesViewModel(@GetRecipes ObservableUseCase<Void, List<Recipe>> getRecipesUseCase, Logger logger) {
        this.getRecipesUseCase = getRecipesUseCase;
        this.logger = logger;
    }

    public void onViewActive() {
        loadRecipes();
    }

    private void loadRecipes() {
        getRecipesUseCase.execute(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onNext(List<Recipe> recipes) {
                        _liveData.setValue(toRecipeUiModelList(recipes));
                    }

                    @Override
                    public void onError(Throwable e) {
                        logger.e("Error when loading recipes.", e);

                        RecipesNews news = new RecipesNews.ErrorLoadingRecipes(e.getMessage());
                        _news.setValue(new Event<>(news));
                    }
                });
    }

    private List<RecipeUiModel> toRecipeUiModelList(List<Recipe> recipes) {
        List<RecipeUiModel> recipeUiModelList = new ArrayList<>();

        for (Recipe recipe : recipes) {
            recipeUiModelList.add(toRecipeUiModel(recipe));
        }

        return recipeUiModelList;
    }

    private RecipeUiModel toRecipeUiModel(Recipe recipe) {
        return new RecipeUiModel(recipe.getId());
    }

}