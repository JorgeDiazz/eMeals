package com.jorgediaz.presentation.ui.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jorgediaz.domain.qualifiers.UpdateRecipeTitle;
import com.jorgediaz.presentation.core.BaseSingle;
import com.jorgediaz.presentation.core.Event;
import com.jorgediaz.presentation.core.Logger;
import com.jorgediaz.presentation.ui.news.RecipeDetailsNews;
import com.jorgediaz.usecases.interfaces.SingleUseCase;
import com.jorgediaz.usecases.utils.Pair;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class RecipeDetailsViewModel extends ViewModel {

    private final MutableLiveData<Event<RecipeDetailsNews>> _news = new MutableLiveData<>();
    public final LiveData<Event<RecipeDetailsNews>> news = _news;

    private final SingleUseCase<Pair<Integer, String>, String> updateRecipeTitleUseCase;
    private final Logger logger;

    @Inject
    public RecipeDetailsViewModel(@UpdateRecipeTitle SingleUseCase<Pair<Integer, String>, String> getRecipesUseCase, Logger logger) {
        this.updateRecipeTitleUseCase = getRecipesUseCase;
        this.logger = logger;
    }

    public void updateRecipeTitle(int recipeId, String newTitle) {
        updateRecipeTitleUseCase.execute(new Pair<>(recipeId, newTitle))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSingle<String>() {
                    @Override
                    public void onSuccess(String newRecipeTitle) {
                        RecipeDetailsNews news = new RecipeDetailsNews.RecipeTitleUpdatedSuccessfully(newRecipeTitle);
                        _news.setValue(new Event<>(news));

                        super.onSuccess(newRecipeTitle);
                    }

                    @Override
                    public void onError(Throwable e) {
                        logger.e("Error when updating recipe title", e);

                        RecipeDetailsNews news = new RecipeDetailsNews.ErrorUpdatingRecipeTitle(e.getMessage());
                        _news.setValue(new Event<>(news));

                        super.onError(e);
                    }
                });
    }


}