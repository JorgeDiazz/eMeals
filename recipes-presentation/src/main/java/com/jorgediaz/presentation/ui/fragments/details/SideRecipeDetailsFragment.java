package com.jorgediaz.presentation.ui.fragments.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.google.android.material.tabs.TabLayout;
import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.databinding.FragmentSideRecipeDetailsBinding;
import com.jorgediaz.presentation.ui.adapters.RecipeDetailsViewPagerAdapter;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;
import com.jorgediaz.presentation.ui.model.SideRecipeUiModel;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class SideRecipeDetailsFragment extends DialogFragment {

    private FragmentSideRecipeDetailsBinding binding;

    private RecipeUiModel recipeUiModel;
    private SideRecipeUiModel sideRecipeUiModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSideRecipeDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeProperties();
        initializeView();
    }

    private void initializeProperties() {
        recipeUiModel = SideRecipeDetailsFragmentArgs.fromBundle(requireArguments()).getRecipeUiModel();
        sideRecipeUiModel = recipeUiModel.getSideRecipe();
    }

    private void initializeView() {
        initializeToolbar();
        initializeMainRecipeImage();
        initializeMainRecipeTitle();
        initializeTabLayout();
    }


    private void initializeToolbar() {
        binding.mainToolbar.setTitle(sideRecipeUiModel.getTitle());

        binding.mainToolbar.setNavigationIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back));

        if (binding.mainToolbar.getNavigationIcon() != null) {
            binding.mainToolbar.getNavigationIcon().setTint(ContextCompat.getColor(requireContext(), R.color.white));
        }

        binding.mainToolbar.setNavigationOnClickListener(view -> popFragment());
    }

    private void initializeMainRecipeImage() {
        final ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.setColor(requireContext().getColor(R.color.green_sushi));
        progressBarDrawable.setBackgroundColor(requireContext().getColor(R.color.orange_outrageous));
        progressBarDrawable.setRadius(getResources().getDimensionPixelSize(R.dimen.radius_background_small_200));

        binding.imageViewRecipe.getHierarchy().setProgressBarImage(progressBarDrawable);
        binding.imageViewRecipe.setImageURI(recipeUiModel.getPrimaryPictureUrl());
    }

    private void initializeMainRecipeTitle() {
        binding.textViewMainDishTitle.setText(recipeUiModel.getTitle());
        binding.textViewMainDishStyle.setText(recipeUiModel.getStyle());
    }

    private void initializeTabLayout() {
        FragmentManager manager = requireActivity().getSupportFragmentManager();
        RecipeDetailsViewPagerAdapter pageAdapter = new RecipeDetailsViewPagerAdapter(manager, getLifecycle(), recipeUiModel, true);
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

    private void popFragment() {
        requireActivity().onBackPressed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}