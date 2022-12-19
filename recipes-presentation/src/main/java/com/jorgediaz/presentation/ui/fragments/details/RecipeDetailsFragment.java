package com.jorgediaz.presentation.ui.fragments.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.core.EventObserver;
import com.jorgediaz.presentation.databinding.FragmentRecipeDetailsBinding;
import com.jorgediaz.presentation.ui.adapters.RecipeDetailsViewPagerAdapter;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;
import com.jorgediaz.presentation.ui.news.RecipeDetailsNews;
import com.jorgediaz.presentation.ui.viewmodels.RecipeDetailsViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class RecipeDetailsFragment extends DialogFragment implements MenuProvider {

    private FragmentRecipeDetailsBinding binding;
    private RecipeDetailsViewModel viewModel;

    private RecipeUiModel recipeUiModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeProperties();
        initializeView();
        initializeViewModel();
        initializeSubscription();
    }

    private void initializeProperties() {
        recipeUiModel = RecipeDetailsFragmentArgs.fromBundle(requireArguments()).getRecipeUiModel();
    }

    private void initializeView() {
        initializeToolbar();
        initializeRecipeImage();
        initializeTabLayout();
    }

    private void initializeToolbar() {
        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.mainToolbar);
        binding.toolbarLayout.setTitle(recipeUiModel.getTitle() + "\n" + recipeUiModel.getStyle());
        binding.toolbarLayout.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);
        binding.toolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColorNormal);

        binding.mainToolbar.inflateMenu(R.menu.menu_recipe_details_toolbar);

        binding.mainToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_edit_title) {
                onEditTitleActionPressed();
                return true;
            }
            return false;
        });

        binding.mainToolbar.setNavigationIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back));

        if (binding.mainToolbar.getNavigationIcon() != null) {
            binding.mainToolbar.getNavigationIcon().setTint(ContextCompat.getColor(requireContext(), R.color.white));
        }

        binding.mainToolbar.setNavigationOnClickListener(view -> popFragment());
    }

    private void initializeRecipeImage() {
        final ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.setColor(requireContext().getColor(R.color.green_sushi));
        progressBarDrawable.setBackgroundColor(requireContext().getColor(R.color.orange_outrageous));
        progressBarDrawable.setRadius(getResources().getDimensionPixelSize(R.dimen.radius_background_small_200));

        binding.imageViewRecipe.getHierarchy().setProgressBarImage(progressBarDrawable);
        binding.imageViewRecipe.setImageURI(recipeUiModel.getPrimaryPictureUrl());
    }

    private void initializeTabLayout() {
        FragmentManager manager = requireActivity().getSupportFragmentManager();
        RecipeDetailsViewPagerAdapter pageAdapter = new RecipeDetailsViewPagerAdapter(manager, getLifecycle(), recipeUiModel, false);
        binding.viewPagerContent.setAdapter(pageAdapter);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPagerContent.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // no-op by default
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // no-op by default
            }
        });

        binding.viewPagerContent.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                TabLayout.Tab currentTab = binding.tabLayout.getTabAt(position);
                binding.tabLayout.selectTab(currentTab);
            }
        });
    }

    private void initializeViewModel() {
        viewModel = new ViewModelProvider(this).get(RecipeDetailsViewModel.class);
    }

    private void onEditTitleActionPressed() {
        showEditTitleAlertDialog();
    }

    private void showEditTitleAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(getString(R.string.edit_recipe_title));

        View viewInflated = getLayoutInflater().inflate(R.layout.alert_dialog_input_text, (ViewGroup) getView(), false);
        final TextInputEditText input = (TextInputEditText) viewInflated.findViewById(R.id.editText_content);
        input.setText(recipeUiModel.getTitle());
        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            dialog.dismiss();

            String newRecipeTitle = Objects.requireNonNull(input.getText()).toString();
            viewModel.updateRecipeTitle(recipeUiModel.getId(), newRecipeTitle);
        });

        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void popFragment() {
        requireActivity().onBackPressed();
    }

    private void initializeSubscription() {
        viewModel.news.observe(getViewLifecycleOwner(), new EventObserver<>(this::handleNews));
    }

    private void handleNews(RecipeDetailsNews recipeDetailsNews) {
        if (recipeDetailsNews instanceof RecipeDetailsNews.RecipeTitleUpdatedSuccessfully) {
            String newRecipeTitle = ((RecipeDetailsNews.RecipeTitleUpdatedSuccessfully) recipeDetailsNews).getNewRecipeTitle();
            updateRecipeTitle(newRecipeTitle);
        } else if (recipeDetailsNews instanceof RecipeDetailsNews.ErrorUpdatingRecipeTitle) {
            Snackbar.make(binding.mainLayout, ((RecipeDetailsNews.ErrorUpdatingRecipeTitle) recipeDetailsNews).getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    private void updateRecipeTitle(String newRecipeTitle) {
        recipeUiModel.setTitle(newRecipeTitle);
        binding.toolbarLayout.setTitle(newRecipeTitle + "\n" + recipeUiModel.getStyle());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_recipe_details_toolbar, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}