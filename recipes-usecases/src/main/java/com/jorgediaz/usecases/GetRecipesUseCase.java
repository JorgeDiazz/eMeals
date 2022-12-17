package com.jorgediaz.usecases;

import com.jorgediaz.data.interfaces.IRecipesRepository;
import com.jorgediaz.domain.Recipe;
import com.jorgediaz.usecases.interfaces.ObservableUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetRecipesUseCase extends ObservableUseCase<Void, List<Recipe>> {

    private final IRecipesRepository recipesRepository;

    @Inject
    public GetRecipesUseCase(IRecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    @Override
    public Observable<List<Recipe>> execute(Void input) {
        return recipesRepository.getRecipes();
    }
}