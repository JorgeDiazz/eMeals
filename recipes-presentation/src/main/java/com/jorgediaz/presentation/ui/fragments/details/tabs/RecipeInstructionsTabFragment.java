package com.jorgediaz.presentation.ui.fragments.details.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.databinding.FragmentRecipeInstructionsTabBinding;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;

import java.util.ArrayList;
import java.util.List;


public class RecipeInstructionsTabFragment extends Fragment {

    private static final String RECIPE_UI_MODEL_KEY = "RECIPE_UI_MODEL_KEY";

    private FragmentRecipeInstructionsTabBinding binding;

    private RecipeUiModel recipeUiModel;

    public static RecipeInstructionsTabFragment newInstance(RecipeUiModel recipeUiModel) {
        Bundle args = new Bundle();
        args.putParcelable(RECIPE_UI_MODEL_KEY, recipeUiModel);

        RecipeInstructionsTabFragment fragment = new RecipeInstructionsTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeInstructionsTabBinding.inflate(inflater, container, false);
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
        List<String> recipeInstructions = new ArrayList<>();

        for (int i = 0; i < recipeUiModel.getInstructions().size(); i++) {
            String instruction = recipeUiModel.getInstructions().get(i);

            recipeInstructions.add(getString(R.string.instruction_step, i + 1, instruction));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, recipeInstructions);
        binding.listViewInstructions.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}