package com.jorgediaz.presentation.ui.news;

public abstract class RecipesNews {
    private RecipesNews() {
        // no-op by default
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
