package com.jorgediaz.presentation.ui.fragments.details.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.databinding.FragmentRecipeSummaryTabBinding;
import com.jorgediaz.presentation.databinding.FragmentSideRecipeSummaryTabBinding;
import com.jorgediaz.presentation.ui.model.NutritionalInformationUiModel;
import com.jorgediaz.presentation.ui.model.SideRecipeUiModel;

import java.util.Arrays;
import java.util.List;


public class SideRecipeSummaryTabFragment extends Fragment {

    private static final String SIDE_RECIPE_UI_MODEL_KEY = "SIDE_RECIPE_UI_MODEL_KEY";

    private FragmentSideRecipeSummaryTabBinding binding;

    private SideRecipeUiModel sideRecipeUiModel;

    public static SideRecipeSummaryTabFragment newInstance(SideRecipeUiModel sideRecipeUiModel) {
        Bundle args = new Bundle();
        args.putParcelable(SIDE_RECIPE_UI_MODEL_KEY, sideRecipeUiModel);

        SideRecipeSummaryTabFragment fragment = new SideRecipeSummaryTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSideRecipeSummaryTabBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeProperties();
        initializeView();
    }

    private void initializeProperties() {
        sideRecipeUiModel = requireArguments().getParcelable(SIDE_RECIPE_UI_MODEL_KEY);
    }

    private void initializeView() {
        initializeSummaryTextViews();
        initializeNutritionalInformationView();
    }

    private void initializeSummaryTextViews() {
        binding.textViewRecipeServings.setText(String.valueOf(sideRecipeUiModel.getServings()));
        binding.textViewRecipeTime.setText(getString(R.string.time_default_value));
        binding.textViewRecipeRating.setText(getString(R.string.rating_default_value));
    }

    private void initializeNutritionalInformationView() {
        List<TextView> textViewNutritionalInformationList = Arrays.asList(binding.textViewNutritionalInformation1, binding.textViewNutritionalInformation2, binding.textViewNutritionalInformation3, binding.textViewNutritionalInformation4, binding.textViewNutritionalInformation5, binding.textViewNutritionalInformation6, binding.textViewNutritionalInformation7);
        List<NutritionalInformationUiModel> nutritionalInformationUiModelList = sideRecipeUiModel.getNutritionalInformationList();

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}