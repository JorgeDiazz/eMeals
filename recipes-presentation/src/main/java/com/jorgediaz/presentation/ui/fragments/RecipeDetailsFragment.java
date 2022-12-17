package com.jorgediaz.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.ui.viewmodels.RecipesViewModel;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class RecipeDetailsFragment extends Fragment {

    private RecipesViewModel mViewModel;

    public static RecipeDetailsFragment newInstance() {
        return new RecipeDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecipesViewModel.class);
    }


}