package com.jorgediaz.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.core.EventObserver;
import com.jorgediaz.presentation.databinding.FragmentRecipesBinding;
import com.jorgediaz.presentation.ui.adapters.RecipesAdapter;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;
import com.jorgediaz.presentation.ui.news.RecipesNews;
import com.jorgediaz.presentation.ui.viewmodels.RecipesViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecipesFragment extends Fragment implements RecipesAdapter.OnRecipeClickListener {

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
        initializeToolbar();
    }

    private void initializeToolbar() {
        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.mainToolbar.getToolbar());
        binding.mainToolbar.styleToolbarWithLightTheme();
        binding.mainToolbar.setTitle(getString(R.string.app_name));
        binding.mainToolbar.setBackButton(false);
    }


    private void initializeViewModel() {
        viewModel = new ViewModelProvider(this).get(RecipesViewModel.class);
        viewModel.onViewActive();
    }

    private void initializeObserver() {
        viewModel.liveData.observe(getViewLifecycleOwner(), this::observeRecipesData);
    }

    private void observeRecipesData(List<RecipeUiModel> recipeUiModelList) {
        setUpRecipesRecyclerView(recipeUiModelList);
    }

    private void setUpRecipesRecyclerView(List<RecipeUiModel> recipeUiModelList) {
        RecyclerView recipesRecyclerView = binding.recyclerViewRecipes;
        RecipesAdapter recipesAdapter = new RecipesAdapter(recipeUiModelList, this, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recipesRecyclerView.setLayoutManager(linearLayoutManager);

        recipesRecyclerView.setAdapter(recipesAdapter);
    }

    private void initializeSubscription() {
        viewModel.news.observe(getViewLifecycleOwner(), new EventObserver<>(this::handleNews));
    }

    private void handleNews(RecipesNews recipesNews) {
        if (recipesNews instanceof RecipesNews.ErrorLoadingRecipes) {
            Snackbar.make(binding.mainLayout, ((RecipesNews.ErrorLoadingRecipes) recipesNews).getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecipeClicked(RecipeUiModel recipeUiModel) {
        NavDirections action = RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(recipeUiModel);
        NavDestination destination = Navigation.findNavController(binding.getRoot()).getCurrentDestination();

        if (destination != null && destination.getId() == R.id.recipesFragment) {
            Navigation.findNavController(binding.getRoot()).navigate(action);
        }
    }
}