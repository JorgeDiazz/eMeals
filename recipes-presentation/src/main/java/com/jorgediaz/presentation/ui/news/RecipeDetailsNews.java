package com.jorgediaz.presentation.ui.news;

public abstract class RecipeDetailsNews {
    private RecipeDetailsNews() {
        // no-op by default
    }

    public static final class RecipeTitleUpdatedSuccesfully extends RecipeDetailsNews {
        private final String newRecipeTitle;

        public RecipeTitleUpdatedSuccesfully(String newRecipeTitle) {
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
