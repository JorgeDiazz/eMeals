package com.jorgediaz.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.jorgediaz.presentation.core.EventObserver;
import com.jorgediaz.presentation.databinding.FragmentRecipesBinding;
import com.jorgediaz.presentation.ui.viewmodels.RecipesViewModel;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;
import com.jorgediaz.presentation.ui.news.RecipesNews;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecipesFragment extends Fragment {

    private FragmentRecipesBinding binding;
    private RecipesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
        initializeViewModel();
        initializeObserver();
        initializeSubscription();
    }

    private void initializeView() {
    }

    private void initializeViewModel() {
        viewModel = new ViewModelProvider(this).get(RecipesViewModel.class);
        viewModel.onViewActive();
    }

    private void initializeObserver() {
        viewModel.liveData.observe(getViewLifecycleOwner(), this::observeRecipesData);
    }

    private void observeRecipesData(List<RecipeUiModel> recipeUiModelList) {
        System.out.println("observeRecipesData " + recipeUiModelList.size());
    }

    private void initializeSubscription() {
        viewModel.news.observe(getViewLifecycleOwner(), new EventObserver<>(this::handleNews));
    }

    private void handleNews(RecipesNews recipesNews) {
        if (recipesNews instanceof RecipesNews.ErrorLoadingRecipes) {
            Snackbar.make(binding.main, ((RecipesNews.ErrorLoadingRecipes) recipesNews).getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}