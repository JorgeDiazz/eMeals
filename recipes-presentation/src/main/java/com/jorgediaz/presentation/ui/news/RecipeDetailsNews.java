package com.jorgediaz.presentation.ui.news;

public abstract class RecipeDetailsNews {
    private RecipeDetailsNews() {
        // no-op by default
    }

    public static final class RecipeTitleUpdatedSuccessfully extends RecipeDetailsNews {
        private final String newRecipeTitle;

        public RecipeTitleUpdatedSuccessfully(String newRecipeTitle) {
            this.newRecipeTitle = newRecipeTitle;
        }

        public String getNewRecipeTitle() {
            return newRecipeTitle;
        }
    }

    public static final class ErrorUpdatingRecipeTitle extends RecipeDetailsNews {
        private final String message;

        public ErrorUpdatingRecipeTitle(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
