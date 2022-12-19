package com.jorgediaz.presentation.ui.news;

import com.jorgediaz.presentation.ui.model.RecipeUiModel;

public abstract class RecipesNews {
    private RecipesNews() {
        // no-op by default
    }

    public static final class ShowSideRecipeDetails extends RecipesNews {
        private final RecipeUiModel recipeUiModel;

        public ShowSideRecipeDetails(RecipeUiModel newRecipeTitle) {
            this.recipeUiModel = newRecipeTitle;
        }

        public RecipeUiModel getRecipeUiModel() {
            return recipeUiModel;
        }
    }

    public static final class ErrorLoadingRecipes extends RecipesNews {
        private final String message;

        public ErrorLoadingRecipes(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
