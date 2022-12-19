package com.jorgediaz.presentation.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jorgediaz.presentation.ui.fragments.details.tabs.RecipeIngredientsTabFragment;
import com.jorgediaz.presentation.ui.fragments.details.tabs.RecipeInstructionsTabFragment;
import com.jorgediaz.presentation.ui.fragments.details.tabs.RecipeSummaryTabFragment;
import com.jorgediaz.presentation.ui.fragments.details.tabs.SideRecipeSummaryTabFragment;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;

public class RecipeDetailsViewPagerAdapter extends FragmentStateAdapter {

    private static final int RECIPE_SUMMARY_TAB_INDEX = 0;
    private static final int RECIPE_INGREDIENTS_TAB_INDEX = 1;
    private static final int RECIPE_INSTRUCTIONS_TAB_INDEX = 2;

    private static final int TOTAL_TABS = 3;

    private final RecipeUiModel recipeUiModel;
    private final boolean sideRecipe;

    public RecipeDetailsViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, RecipeUiModel recipeUiModel, boolean sideRecipe) {
        super(fragmentManager, lifecycle);
        this.recipeUiModel = recipeUiModel;
        this.sideRecipe = sideRecipe;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case RECIPE_SUMMARY_TAB_INDEX:
                return sideRecipe ? SideRecipeSummaryTabFragment.newInstance(recipeUiModel.getSideRecipe()) : RecipeSummaryTabFragment.newInstance(recipeUiModel);
            case RECIPE_INGREDIENTS_TAB_INDEX:
                return RecipeIngredientsTabFragment.newInstance(recipeUiModel);
            case RECIPE_INSTRUCTIONS_TAB_INDEX:
                return RecipeInstructionsTabFragment.newInstance(recipeUiModel);
        }

        return RecipeSummaryTabFragment.newInstance(recipeUiModel);
    }

    @Override
    public int getItemCount() {
        return TOTAL_TABS;
    }
}
