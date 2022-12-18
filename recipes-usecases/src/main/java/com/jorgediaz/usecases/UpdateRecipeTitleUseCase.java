package com.jorgediaz.usecases;

import com.jorgediaz.data.interfaces.IRecipesRepository;
import com.jorgediaz.usecases.interfaces.SingleUseCase;
import com.jorgediaz.usecases.utils.Pair;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class UpdateRecipeTitleUseCase extends SingleUseCase<Pair<Integer, String>, String> {

    private final IRecipesRepository recipesRepository;

    @Inject
    public UpdateRecipeTitleUseCase(IRecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    @Override
    public Single<String> execute(Pair<Integer, String> input) {
        int recipeId = input.getFirst();
        String newTitle = input.getSecond();

        return recipesRepository.updateRecipeTitle(recipeId, newTitle);
    }
}