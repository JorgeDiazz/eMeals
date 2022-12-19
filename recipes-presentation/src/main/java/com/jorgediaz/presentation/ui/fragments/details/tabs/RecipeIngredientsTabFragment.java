package com.jorgediaz.presentation.ui.fragments.details.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.jorgediaz.presentation.databinding.FragmentRecipeIngredientsTabBinding;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;
import com.jorgediaz.presentation.ui.viewmodels.RecipeDetailsViewModel;


public class RecipeIngredientsTabFragment extends Fragment {

    private static final String RECIPE_UI_MODEL_KEY = "RECIPE_UI_MODEL_KEY";

    private FragmentRecipeIngredientsTabBinding binding;

    private RecipeUiModel recipeUiModel;

    public static RecipeIngredientsTabFragment newInstance(RecipeUiModel recipeUiModel) {
        Bundle args = new Bundle();
        args.putParcelable(RECIPE_UI_MODEL_KEY, recipeUiModel);

        RecipeIngredientsTabFragment fragment = new RecipeIngredientsTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeIngredientsTabBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeProperties();
        initializeView();
    }

    private void initializeProperties() {
        recipeUiModel = requireArguments().getParcelable(RECIPE_UI_MODEL_KEY);
    }

    private void initializeView() {
        setUpIngredientsListView();
    }

    private void setUpIngredientsListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, recipeUiModel.getIngredients());
        binding.listViewIngredients.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}