package com.jorgediaz.presentation.ui.fragments;

import static com.jorgediaz.presentation.ui.FormatUtils.ONE_DECIMAL_FORMAT_PATTERN;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jorgediaz.presentation.databinding.FragmentRecipeSummaryTabBinding;
import com.jorgediaz.presentation.ui.model.NutritionalInformationUiModel;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class RecipeSummaryTabFragment extends Fragment {

    private static final String RECIPE_UI_MODEL_KEY = "RECIPE_UI_MODEL_KEY";

    private FragmentRecipeSummaryTabBinding binding;

    private RecipeUiModel recipeUiModel;

    public static RecipeSummaryTabFragment newInstance(RecipeUiModel recipeUiModel) {
        Bundle args = new Bundle();
        args.putParcelable(RECIPE_UI_MODEL_KEY, recipeUiModel);

        RecipeSummaryTabFragment fragment = new RecipeSummaryTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeSummaryTabBinding.inflate(inflater, container, false);
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
        binding.textViewRecipeServings.setText(String.valueOf(recipeUiModel.getServings()));
        binding.textViewRecipeTime.setText(String.valueOf(recipeUiModel.getTime()));
        binding.textViewRecipeRating.setText(String.format(Locale.ENGLISH, ONE_DECIMAL_FORMAT_PATTERN, recipeUiModel.getRating()));

        initializeNutritionalInformationView();
        initializeSideRecipePreview();
    }

    private void initializeNutritionalInformationView() {
        List<TextView> textViewNutritionalInformationList = Arrays.asList(binding.textViewNutritionalInformation1, binding.textViewNutritionalInformation2, binding.textViewNutritionalInformation3, binding.textViewNutritionalInformation4, binding.textViewNutritionalInformation5, binding.textViewNutritionalInformation6, binding.textViewNutritionalInformation7);
        List<NutritionalInformationUiModel> nutritionalInformationUiModelList = recipeUiModel.getNutritionalInformationList();

        if (nutritionalInformationUiModelList.isEmpty()) {
            binding.flexboxNutritionalInformation.setVisibility(View.GONE);
            binding.textViewNoNutritionalInformationAvailable.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < textViewNutritionalInformationList.size(); i++) {
                TextView textView = textViewNutritionalInformationList.get(i);
                NutritionalInformationUiModel nutritionalInformationUiModel = nutritionalInformationUiModelList.get(i + 1);

                textView.setText(nutritionalInformationUiModel.toString());
            }
        }
    }

    private void initializeSideRecipePreview() {
        binding.textViewRecommendedSideDishTitle.setText(recipeUiModel.getSideRecipe().getTitle());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}